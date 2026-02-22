package ecom.icet.repository;

import ecom.icet.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking,String> {
    @Query("SELECT b.id FROM Booking b ORDER BY b.id DESC LIMIT 1")
    String findLastId();
}
