package ecom.icet.service;

import ecom.icet.model.dto.BookingRequestDto;
import ecom.icet.model.dto.BookingResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookingService {
    BookingResponseDto createBooking(BookingRequestDto request, MultipartFile cvFile);
    List<BookingResponseDto> getAllBookings();
    void cancelBooking(String bookingId);
}
