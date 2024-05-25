package com.eqa.eqaadvancereportservice.entity;

import com.eqa.eqaadvancereportservice.config.AuditEntityListener;
import com.eqa.eqaadvancereportservice.dto.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "annual_program_report_master")
@Data
@EntityListeners(AuditEntityListener.class)
public class ReportMaster implements Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "report_id")
    private String reportId;

    @Column(name = "program_id")
    private String programId;

    @Column(name = "department_id", length = 100)
    private String departmentId;

    @Column(name = "college_id", length = 100)
    private String collegeId;

    @Column(name = "academic_year")
    private Integer academicYear;

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
}
