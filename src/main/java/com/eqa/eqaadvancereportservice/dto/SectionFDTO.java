package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

@Data
public class SectionFDTO {
    private String councilCommitteeId;
    private Integer referenceNo;
    private String approvalDate;
    private byte[] departmentStamp;

    // Getters and Setters
}