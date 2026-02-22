package ecom.icet.service;

public interface AuditService {
    void log(String action, String details);
}
