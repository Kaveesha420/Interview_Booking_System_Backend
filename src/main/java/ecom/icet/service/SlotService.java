package ecom.icet.service;


import ecom.icet.model.dto.SlotDto;
import ecom.icet.model.dto.SlotGenerationDto;
import ecom.icet.model.entity.InterviewSlot;

import java.util.List;

public interface SlotService {
    void generateBulkSlots(SlotGenerationDto request);
    List<SlotDto> getAvailableSlots();
    void updateSlotStatus(String slotId, String status);
    InterviewSlot getSlotEntity(String id);

    // 5. Slot එකක් මකා දැමීම (Admin CRUD)
    void deleteSlot(String id);
}
