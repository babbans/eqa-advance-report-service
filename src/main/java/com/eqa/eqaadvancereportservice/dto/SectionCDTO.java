package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

@Data
public class SectionCDTO {
    private Long id;
    private Integer kpiId;
    private Double actualValue;
    private Double internalBenchmark;
    private String analysis;
    private Double newTarget;
    private String comments;

    // Getters and Setters
}