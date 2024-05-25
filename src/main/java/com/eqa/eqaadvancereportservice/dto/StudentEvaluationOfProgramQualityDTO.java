package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

@Data
public class StudentEvaluationOfProgramQualityDTO {
    private String evaluationDate;
    private Integer noOfParticipants;
    private String studentFeedback;
    private String programResponse;
    private String feedbackType;

    // Getters and Setters
}