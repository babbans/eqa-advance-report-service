package com.eqa.eqaadvancereportservice.entity;

import com.eqa.eqaadvancereportservice.config.AuditEntityListener;
import com.eqa.eqaadvancereportservice.dto.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "annual_program_report_section_bsridry")
@EntityListeners(AuditEntityListener.class)
public class APRSummarySectionBSRIDRY implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT AUTO_INCREMENT COMMENT 'Primary key'")
    private Long id;

    @Column(name = "report_id", length = 100, nullable = false, columnDefinition = "VARCHAR(100) COMMENT 'Foreign key referencing report'")
    private String reportId;

    @Column(name = "psr", columnDefinition = "INTEGER COMMENT 'Published scientific research'")
    private Integer psr;

    @Column(name = "crp", columnDefinition = "INTEGER COMMENT 'Current research projects'")
    private Integer crp;

    @Column(name = "cop", columnDefinition = "INTEGER COMMENT 'Conferences organized by the program'")
    private Integer cop;

    @Column(name = "shp", columnDefinition = "INTEGER COMMENT 'Seminars held by the program'")
    private Integer shp;

    @Column(name = "ca", columnDefinition = "INTEGER COMMENT 'Conferences attendees'")
    private Integer ca;

    @Column(name = "sa", columnDefinition = "INTEGER COMMENT 'Seminars attendees'")
    private Integer sa;

    @Column(name = "discussion", length = 2000, columnDefinition = "TEXT(2000) COMMENT 'Discussion'")
    private String discussion;

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
