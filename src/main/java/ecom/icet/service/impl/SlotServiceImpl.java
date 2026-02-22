package ecom.icet.service.impl;

import ecom.icet.model.dto.SlotDto;
import ecom.icet.model.dto.SlotGenerationDto;
import ecom.icet.model.entity.InterviewSlot;
import ecom.icet.repository.SlotRepository;
import ecom.icet.service.SlotService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepo;

    // 1. Bulk Slot Generation (Admin ට අවශ්‍ය දවස සහ වෙලාව අනුව slots සෑදීම)
    @Override
    @Transactional
    public void generateBulkSlots(SlotGenerationDto request) {
        // පටන් ගන්නා වෙලාව සහ දවස එකතු කර LocalDateTime එකක් සෑදීම
        LocalDateTime current = LocalDateTime.of(request.getDate(), request.getStartTime());
        LocalDateTime endLimit = LocalDateTime.of(request.getDate(), request.getEndTime());

        while (current.plusMinutes(request.getDurationMinutes()).isBefore(endLimit) ||
                current.plusMinutes(request.getDurationMinutes()).isEqual(endLimit)) {

            // එකම දවසේ එකම වෙලාවට දැනටමත් slot එකක් තියෙනවද කියා බැලීම (Duplicate වළක්වන්න)
            boolean exists = slotRepo.existsByStartTimeAndEndTime(current, current.plusMinutes(request.getDurationMinutes()));

            if (!exists) {
                InterviewSlot slot = new InterviewSlot();
                slot.setStartTime(current);
                slot.setEndTime(current.plusMinutes(request.getDurationMinutes()));
                slot.setStatus("AVAILABLE");
                slotRepo.save(slot);
            }

            // මීළඟ slot එකේ ආරම්භක වෙලාව සකස් කිරීම
            current = current.plusMinutes(request.getDurationMinutes());
        }
    }

    // 2. දැනට පද්ධතියේ ඇති සියලුම ලබාගත හැකි (AVAILABLE) slots ලබා ගැනීම
    @Override
    public List<SlotDto> getAvailableSlots() {
        return slotRepo.findByStatus("AVAILABLE").stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 3. Slot එකක status එක වෙනස් කිරීම (Internal use for Booking/Cancellation)
    @Override
    public void updateSlotStatus(String slotId, String status) {
        InterviewSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        slot.setStatus(status);
        slotRepo.save(slot);
    }

    // 4. Slot Entity එක ලබා ගැනීම (BookingService එකට අවශ්‍ය වේ)
    @Override
    public InterviewSlot getSlotEntity(String id) {
        return slotRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
    }

    // 5. Slot එකක් මකා දැමීම (Admin CRUD)
    @Override
    public void deleteSlot(String id) {
        slotRepo.deleteById(id);
    }

    // Helper Method: Entity -> DTO
    private SlotDto mapToDto(InterviewSlot slot) {
        SlotDto dto = new SlotDto();
        dto.setId(slot.getId());
        dto.setStartTime(slot.getStartTime());
        dto.setEndTime(slot.getEndTime());
        dto.setStatus(slot.getStatus());
        return dto;
    }
}