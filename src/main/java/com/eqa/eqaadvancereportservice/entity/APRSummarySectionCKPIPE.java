package com.eqa.eqaadvancereportservice.entity;

import com.eqa.eqaadvancereportservice.config.AuditEntityListener;
import com.eqa.eqaadvancereportservice.dto.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "annual_program_report_section_akpipe")
@EntityListeners(AuditEntityListener.class)
public class APRSummarySectionCKPIPE implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT AUTO_INCREMENT COMMENT 'Primary key'")
    private Long id;

    @Column(name = "report_id", length = 100, nullable = false, columnDefinition = "VARCHAR(100) COMMENT 'Foreign key referencing report'")
    private String reportId;

    @Column(name = "kpi_id", columnDefinition = "INTEGER COMMENT 'KPI identifier'")
    private Integer kpiId;

    @Column(name = "actual_value", columnDefinition = "DOUBLE COMMENT 'Actual value of KPI'")
    private Double actualValue;

    @Column(name = "internal_benchmark", columnDefinition = "DOUBLE COMMENT 'Internal benchmark value'")
    private Double internalBenchmark;

    @Column(name = "analysis", length = 2000, columnDefinition = "TEXT(2000) COMMENT 'Analysis of KPI performance'")
    private String analysis;

    @Column(name = "new_target", columnDefinition = "DOUBLE COMMENT 'New target value for KPI'")
    private Double newTarget;

    @Column(name = "comments", length = 2000, columnDefinition = "TEXT(2000) COMMENT 'Optional comments'")
    private String comments;

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
       
