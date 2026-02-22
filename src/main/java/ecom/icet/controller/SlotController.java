package ecom.icet.controller;

import ecom.icet.model.dto.SlotDto;
import ecom.icet.model.dto.SlotGenerationDto;
import ecom.icet.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@RequiredArgsConstructor
public class SlotController {

    private final SlotService slotService;

    // Candidate ට පේන AVAILABLE slots ටික
    @GetMapping("/available")
    public List<SlotDto> getAvailable() {
        return slotService.getAvailableSlots();
    }

    // Admin ට slots bulk එකට create කිරීමට
    @PostMapping("/generate")
    public ResponseEntity<String> generate(@RequestBody SlotGenerationDto dto) {
        slotService.generateBulkSlots(dto);
        return ResponseEntity.ok("Slots generated successfully!");
    }
}
