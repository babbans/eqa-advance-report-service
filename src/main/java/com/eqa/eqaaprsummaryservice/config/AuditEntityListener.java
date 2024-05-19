package com.eqa.eqaaprsummaryservice.config;

import com.eqa.eqaaprsummaryservice.dto.Auditable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

public class AuditEntityListener {

    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
            auditable.setCreatedBy(currentUser);
            auditable.setCreationDatetime(LocalDateTime.now());
            auditable.setUpdatedBy(currentUser);
            auditable.setUpdateDatetime(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
            auditable.setUpdatedBy(currentUser);
            auditable.setUpdateDatetime(LocalDateTime.now());
        }
    }
}
