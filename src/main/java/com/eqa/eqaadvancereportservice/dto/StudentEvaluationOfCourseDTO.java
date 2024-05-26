package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

@Data
public class StudentEvaluationOfCourseDTO {
    private Long id;
    private String courseCode;
    private Integer noOfStudentEvaluatedCourse;
    private Double percentageOfParticipant;
    private String evaluationResult;
    private String recommendation;
}