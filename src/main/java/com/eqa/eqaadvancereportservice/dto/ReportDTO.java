package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ReportDTO {
    private String reportId;
    private SectionADTO sectionA;
    private SectionBDTO sectionB;
    private List<SectionCDTO> sectionC = new ArrayList<>();;
    private SectionDDTO sectionD;
    private List<SectionEDTO> sectionE = new ArrayList<>();;
    private SectionFDTO sectionF;

}



















