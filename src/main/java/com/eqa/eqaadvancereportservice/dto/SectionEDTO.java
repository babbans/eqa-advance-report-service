package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

@Data
public class SectionEDTO {
    private Integer developmentPlanId;
    private String priorityOfImprovement;
    private String action;
    private String actionResponsibility;

    // Getters and Setters
}