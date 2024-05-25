package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class OtherEvaluationDTO {
    private String evaluationMethod;
    private String date;
    private Integer noOfParticipants;
    private String summaryOfEvaluatorReview;
    private String programResponse;
    private String evaluationType;

}