package ecom.icet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewSlot {
    @Id
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
}