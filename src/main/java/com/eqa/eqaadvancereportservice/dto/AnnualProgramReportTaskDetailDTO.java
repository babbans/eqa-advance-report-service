package com.eqa.eqaadvancereportservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class AnnualProgramReportTaskDetailDTO {
    private String reportId;
    private String programId;
    private String departmentId;
    private String collegeId;
    private Integer academicYear;
    private List<TaskDTO> tasks;
    @Data
    public static class TaskDTO {
        private Long id;
        private String responsible;
        private Long sectionId;
        private boolean active;
        private String createdBy;
        private LocalDateTime creationDatetime;
        private String updatedBy;
        private LocalDateTime updateDatetime;
    }
}
