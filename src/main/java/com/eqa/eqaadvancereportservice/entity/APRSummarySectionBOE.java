package com.eqa.eqaadvancereportservice.entity;

import com.eqa.eqaadvancereportservice.config.AuditEntityListener;
import com.eqa.eqaadvancereportservice.dto.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "annual_program_report_section_boe")
@EntityListeners(AuditEntityListener.class)
public class APRSummarySectionBOE implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT AUTO_INCREMENT COMMENT 'Primary key'")
    private Long id;

    @Column(name = "report_id", length = 100, nullable = false, columnDefinition = "VARCHAR(100) COMMENT 'Foreign key referencing report'")
    private String reportId;

    @Column(name = "evaluation_method", columnDefinition = "TEXT COMMENT 'Evaluation method'")
    private String evaluationMethod;

    @Column(name = "date", columnDefinition = "DATE COMMENT 'Date of evaluation'")
    private LocalDate date;

    @Column(name = "no_of_participants", columnDefinition = "INTEGER COMMENT 'Number of participants'")
    private Integer noOfParticipants;

    @Column(name = "ser", length = 2000, columnDefinition = "TEXT(2000) COMMENT 'Summary of Evaluator Review'")
    private String ser;

    @Column(name = "program_response", length = 2000, columnDefinition = "TEXT(2000) COMMENT 'Program response'")
    private String programResponse;

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
