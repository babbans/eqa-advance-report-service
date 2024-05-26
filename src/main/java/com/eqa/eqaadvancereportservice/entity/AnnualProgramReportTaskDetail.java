package com.eqa.eqaadvancereportservice.entity;
import com.eqa.eqaadvancereportservice.config.AuditEntityListener;
import com.eqa.eqaadvancereportservice.dto.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "annual_program_report_task_detail")
@EntityListeners(AuditEntityListener.class)
public class AnnualProgramReportTaskDetail implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "program_id")
    private String programId;

    @Column(name = "department_id", length = 100)
    private String departmentId;

    @Column(name = "college_id", length = 100)
    private String collegeId;

    @Column(name = "academic_year")
    private Integer academicYear;

    @Column(name = "responsible", length = 100)
    private String responsible;

    @Column(name = "section_id", nullable = false)
    private Long section;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private ReportMaster reportMaster;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "creation_datetime")
    private LocalDateTime creationDatetime;

    @Column(name = "update_by", length = 100)
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

    public String getReportId() {
        return reportMaster != null ? reportMaster.getReportId() : null;
    }
}

