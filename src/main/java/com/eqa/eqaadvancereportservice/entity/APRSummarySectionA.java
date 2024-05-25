package com.eqa.eqaadvancereportservice.entity;

import com.eqa.eqaadvancereportservice.config.AuditEntityListener;
import com.eqa.eqaadvancereportservice.dto.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "annual_program_report_section_a")
@EntityListeners(AuditEntityListener.class)
@Data
public class APRSummarySectionA implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT AUTO_INCREMENT COMMENT 'Primary key'")
    private Long id;

    @Column(name = "reportId", length = 100, nullable = false, columnDefinition = "VARCHAR(100) COMMENT 'Foreign key referencing report'")
    private String reportId;

    @Column(name = "nsep", columnDefinition = "INTEGER COMMENT 'Number of students enrolled in the program'")
    private Integer nsep;

    @Column(name = "nswsp", columnDefinition = "INTEGER COMMENT 'Number of students who started the program (in reporting year)'")
    private Integer nswsp;

    @Column(name = "nswcp", columnDefinition = "INTEGER COMMENT 'Number of students who completed the program'")
    private Integer nswcp;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "creation_datetime")
    private LocalDateTime creationDatetime;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "update_datetime")
    private LocalDateTime updateDatetime;

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public void setCreationDatetime(LocalDateTime creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    @Override
    public void setUpdateDatetime(LocalDateTime updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
