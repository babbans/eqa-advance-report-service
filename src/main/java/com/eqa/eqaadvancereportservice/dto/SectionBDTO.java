package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class SectionBDTO {
    private Long id;
    private String strength;
    private String challenges;
    private List<StudentEvaluationOfCourseDTO> studentEvaluationOfCourse = new ArrayList<>();;
    private StudentEvaluationOfProgramQualityDTO studentEvaluationOfProgramQuality;
    private ScientificResearchDTO scientificResearch;
    private List<CommunityPartnershipDTO> communityPartnership = new ArrayList<>();;
    private OtherEvaluationDTO otherEvaluation;

    // Getters and Setters
}