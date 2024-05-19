package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

import java.util.List;
@Data
public class AnnualProgramReportTaskDetailDTO {
    private String programId;
    private String departmentId;
    private String collegeId;
    private Integer academicYear;
    private List<TaskDTO> tasks;
    @Data
    public static class TaskDTO {
        private String responsible;
        private Long sectionId;
        private boolean active;
    }
}
