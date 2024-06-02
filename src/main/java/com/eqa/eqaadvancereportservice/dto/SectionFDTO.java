package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

@Data
public class SectionFDTO {
    private Long id;
    private String councilCommitteeId;
    private Integer referenceNo;
    private String approvalDate;
    private String departmentStamp;

    // Getters and Setters
}