package ecom.icet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking{
    @Id
    private String id;

    @ManyToOne
    private User candidate;

    @ManyToOne
    private User interviewer;

    @OneToOne
    private InterviewSlot slot;

    private LocalDateTime bookedAt;
    private String status;
}