package ecom.icet.service.impl;

import ecom.icet.model.entity.AuditLog;
import ecom.icet.repository.AuditRepository;
import ecom.icet.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepo;

    @Override
    public void log(String action, String details) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.setDetails(details);
        auditLog.setTimestamp(LocalDateTime.now());
        auditLog.setPerformedBy("SYSTEM_USER");

        auditRepo.save(auditLog);
    }
}
