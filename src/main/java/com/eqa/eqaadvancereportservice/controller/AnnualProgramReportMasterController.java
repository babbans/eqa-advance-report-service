package com.eqa.eqaadvancereportservice.controller;

import com.eqa.eqaadvancereportservice.constants.CommonConstants;
import com.eqa.eqaadvancereportservice.dto.ReportDTO;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
import com.eqa.eqaadvancereportservice.exception.CustomException;
import com.eqa.eqaadvancereportservice.service.AnnualProgramReportDataService;
import com.eqa.eqaadvancereportservice.service.AnnualProgramReportMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConstants.API_BASE_PATH + CommonConstants.REPORTS)
@Validated
@Slf4j
public class AnnualProgramReportMasterController {

    @Autowired
    private AnnualProgramReportMasterService reportMasterService;

    @GetMapping("all")
    public ResponseEntity<ResponseObject> getAllReports() {
        log.info("getAllReports() : Start");
        return reportMasterService.findAll();
    }
    @GetMapping
    public ResponseEntity<ResponseObject> findByGivenCriteria(
            @RequestParam String collegeId,
            @RequestParam String departmentId,
            @RequestParam String programId,
            @RequestParam Integer academicYear) {
        log.info("findByGivenCriteria() : Start");
        return reportMasterService.findByGivenCriteria(collegeId, departmentId, programId, academicYear);
    }
}
