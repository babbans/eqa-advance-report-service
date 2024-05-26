package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

@Data
public class SectionDDTO {
    private Long id;
    private String teaching;
    private String assessment;
    private String guidanceCounseling;
    private String learningResources;
    private String faculty;
    private String researchActivities;
    private String other;

    // Getters and Setters
}