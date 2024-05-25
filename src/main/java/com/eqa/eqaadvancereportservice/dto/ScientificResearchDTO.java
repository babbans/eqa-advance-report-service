package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

@Data
public class ScientificResearchDTO {
    private Integer publishedScientificResearch;
    private Integer currentResearchProjects;
    private Integer conferencesOrganizedByProgram;
    private Integer seminarsHeldByProgram;
    private Integer conferencesAttendees;
    private Integer seminarsAttendees;
    private String discussion;

}