package com.eqa.eqaaprsummaryservice.dto;

import java.time.LocalDateTime;

public interface Auditable {
    void setCreatedBy(String createdBy);
    void setUpdatedBy(String updatedBy);
    void setCreationDatetime(LocalDateTime creationDatetime);
    void setUpdateDatetime(LocalDateTime updateDatetime);
}
