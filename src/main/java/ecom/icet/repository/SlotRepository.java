package ecom.icet.repository;

import ecom.icet.model.entity.InterviewSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<InterviewSlot,String> {
    List<InterviewSlot> findByStatus(String status);
    boolean existsByStartTimeAndEndTime(LocalDateTime current, LocalDateTime localDateTime);
    @Query("SELECT s.id FROM InterviewSlot s ORDER BY s.id DESC LIMIT 1")
    String findLastId();
}
