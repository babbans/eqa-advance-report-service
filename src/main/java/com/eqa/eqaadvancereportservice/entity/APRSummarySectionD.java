package com.eqa.eqaadvancereportservice.entity;


import com.eqa.eqaadvancereportservice.config.AuditEntityListener;
import com.eqa.eqaadvancereportservice.dto.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "annual_program_report_section_d")
@EntityListeners(AuditEntityListener.class)
public class APRSummarySectionD implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT AUTO_INCREMENT COMMENT 'Primary key'")
    private Long id;

    @Column(name = "report_id", length = 100, nullable = false, columnDefinition = "VARCHAR(100) COMMENT 'Foreign key referencing report'")
    private String reportId;

    @Column(name = "teaching", length = 2000, columnDefinition = "TEXT(2000) COMMENT 'Challenges in teaching'")
    private String teaching;

    @Column(name = "assessment", length = 2000, columnDefinition = "TEXT(2000) COMMENT 'Challenges in assessment'")
    private String assessment;

    @Column(name = "guidance_counseling", length = 2000, columnDefinition = "TEXT(2000) COMMENT 'Challenges in guidance and counseling'")
    private String guidanceCounseling;

    @Column(name = "learning_resources", length = 2000, columnDefinition = "TEXT(2000) COMMENT 'Challenges in learning resources'")
    private String learningResources;

    @Column(name = "faculty", length = 2000)
    private String faculty;

    @Column(name = "research _activities", length = 2000)
    private String researchActivities;

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
