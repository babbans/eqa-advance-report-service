package com.eqa.eqaadvancereportservice.dto;

import java.time.LocalDateTime;

public interface Auditable {
    void setCreatedBy(String createdBy);
    void setUpdatedBy(String updatedBy);
    void setCreationDatetime(LocalDateTime creationDatetime);
    void setUpdateDatetime(LocalDateTime updateDatetime);
}
