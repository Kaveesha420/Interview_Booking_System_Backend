package ecom.icet.service.impl;

import ecom.icet.model.dto.BookingRequestDto;
import ecom.icet.model.dto.BookingResponseDto;
import ecom.icet.model.entity.Booking;
import ecom.icet.model.entity.InterviewSlot;
import ecom.icet.model.entity.User;
import ecom.icet.repository.BookingRepository;
import ecom.icet.repository.UserRepository;
import ecom.icet.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor


public class BookingServiceImpl implements BookingService {
    private final UserRepository userRepo;
    private final BookingRepository bookingRepo;
    private final SlotService slotService;
    private final UserService userService;
    private final FileService fileService;
    private final AuditService auditService;


    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto request, MultipartFile cvFile) {

        InterviewSlot slot = slotService.getSlotEntity(request.getSlotId());
        if (!slot.getStatus().equals("AVAILABLE")) throw new RuntimeException("Slot unavailable");

        String cvUrl = fileService.storeFile(cvFile);

        User candidate = userRepo.findById(request.getCandidateId()).orElseThrow();
        candidate.setCvUrl(cvUrl);
        User interviewer = userService.getLeastBusyInterviewer();

        Booking booking = new Booking();
        booking.setCandidate(candidate);
        booking.setInterviewer(interviewer);
        booking.setSlot(slot);
        booking.setBookedAt(LocalDateTime.now());
        booking.setStatus("CONFIRMED");

        bookingRepo.save(booking);

        slotService.updateSlotStatus(slot.getId(), "BOOKED");
        interviewer.setInterviewCount(interviewer.getInterviewCount() + 1);
        auditService.log("BOOKING_CREATED", "Candidate " + candidate.getName() + " booked with " + interviewer.getName());

        return mapToResponse(booking);
    }

    private BookingResponseDto mapToResponse(Booking b) {
        BookingResponseDto res = new BookingResponseDto();
        res.setBookingId(b.getId());
        res.setCandidateName(b.getCandidate().getName());
        res.setInterviewerName(b.getInterviewer().getName());
        res.setInterviewTime(b.getSlot().getStartTime());
        res.setStatus(b.getStatus());
        return res;
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepo.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus("CANCELLED");

        InterviewSlot slot = booking.getSlot();
        slotService.updateSlotStatus(slot.getId(), "AVAILABLE");

        User interviewer = booking.getInterviewer();
        if (interviewer.getInterviewCount() > 0) {
            interviewer.setInterviewCount(interviewer.getInterviewCount() - 1);
        }

        bookingRepo.save(booking);


        auditService.log("BOOKING_CANCELLED", "Booking ID " + bookingId + " was cancelled.");
    }
}