package ecom.icet.repository;

import ecom.icet.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    @Query("SELECT u FROM User u WHERE u.role = 'INTERVIEWER' ORDER BY u.interviewCount ASC")
    List<User> findAvailableInterviewers(Pageable pageable);
    Optional<User> findTopByRoleOrderByInterviewCountAsc(String role);
    List<User> findAllByRole(String role);
    @Query("SELECT u.id FROM User u ORDER BY u.id DESC LIMIT 1")
    String findLastId();
}
