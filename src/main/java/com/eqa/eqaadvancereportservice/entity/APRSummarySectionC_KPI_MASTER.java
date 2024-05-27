package com.eqa.eqaadvancereportservice.entity;

import com.eqa.eqaadvancereportservice.config.AuditEntityListener;
import com.eqa.eqaadvancereportservice.dto.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "annual_program_report_section_c_kpi_master")
@EntityListeners(AuditEntityListener.class)
public class APRSummarySectionC_KPI_MASTER implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT AUTO_INCREMENT COMMENT 'Primary key'")
    private Long id;

    @Column(name = "report_id", length = 100, nullable = false, columnDefinition = "VARCHAR(100) COMMENT 'Foreign key referencing report'")
    private String reportId;

    @Column(name = "kpi_id", columnDefinition = "INTEGER COMMENT 'KPI identifier'")
    private Integer kpiId;

    @Column(name = "kpi_description", length = 300, columnDefinition = "TEXT(300) COMMENT 'Description of KPI'")
    private String kpiDescription;

    @Column(name = "targeted_value", columnDefinition = "DOUBLE COMMENT 'Targeted value for KPI'")
    private Double targetedValue;

    @Column(name = "degree", columnDefinition = "VARCHAR(100) COMMENT 'Degree b or m'")
    private String degree;

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
