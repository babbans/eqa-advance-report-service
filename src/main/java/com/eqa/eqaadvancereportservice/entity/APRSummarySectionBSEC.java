package com.eqa.eqaadvancereportservice.entity;

import com.eqa.eqaadvancereportservice.config.AuditEntityListener;
import com.eqa.eqaadvancereportservice.dto.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "annual_program_report_section_bsce")
@EntityListeners(AuditEntityListener.class)
public class APRSummarySectionBSEC implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT AUTO_INCREMENT COMMENT 'Primary key'")
    private Long id;

    @Column(name = "report_id", length = 100, nullable = false, columnDefinition = "VARCHAR(100) COMMENT 'Foreign key referencing report'")
    private String reportId;

    @Column(name = "course_code", length = 100, nullable = false, columnDefinition = "VARCHAR(100) COMMENT 'Course code'")
    private String courseCode;

    @Column(name = "nswec", columnDefinition = "INTEGER COMMENT 'Number of Students Who Evaluated the Course'")
    private Integer nswec;

    @Column(name = "pp", columnDefinition = "DOUBLE COMMENT 'Percentage of Participants'")
    private Double pp;

    @Column(name = "er", columnDefinition = "TEXT COMMENT 'Evaluation Results'")
    private String er;

    @Column(name = "dr", columnDefinition = "TEXT COMMENT 'Developmental Recommendations'")
    private String dr;

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
