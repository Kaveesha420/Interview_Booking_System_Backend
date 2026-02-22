package ecom.icet.repository;

import ecom.icet.model.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditLog,String> {
}
