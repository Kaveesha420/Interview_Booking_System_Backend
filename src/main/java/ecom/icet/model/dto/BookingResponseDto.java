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
public class BookingResponseDto {
    private String bookingId;
    private String candidateName;
    private String interviewerName;
    private LocalDateTime interviewTime;
    private String status;
}
