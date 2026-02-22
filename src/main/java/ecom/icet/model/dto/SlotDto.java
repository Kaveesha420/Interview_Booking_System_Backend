package ecom.icet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotDto {
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
}
