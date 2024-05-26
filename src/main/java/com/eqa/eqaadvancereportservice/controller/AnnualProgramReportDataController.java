package com.eqa.eqaadvancereportservice.controller;

import com.eqa.eqaadvancereportservice.constants.CommonConstants;
import com.eqa.eqaadvancereportservice.dto.ReportDTO;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
import com.eqa.eqaadvancereportservice.exception.CustomException;
import com.eqa.eqaadvancereportservice.service.AnnualProgramReportDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommonConstants.API_BASE_PATH + CommonConstants.REPORT_DATA)
@Validated
@Slf4j
public class AnnualProgramReportDataController {

    @Autowired
    private AnnualProgramReportDataService reportDataService;

    @PostMapping
    public ResponseEntity<ResponseObject> createData(@Validated @RequestBody ReportDTO dto) {
        log.info("createData() : Start");
        log.info("Data is {}", dto);
        return reportDataService.saveOrUpdateReport(dto);
    }
    @GetMapping("{reportId}")
    public ResponseEntity<ResponseObject> getReportDataById(@PathVariable String reportId) throws CustomException{
        log.info("getDataById() : Start, id is {}", reportId);
        return reportDataService.getReportById(reportId);
    }
    @DeleteMapping("/{reportId}")
    public ResponseEntity<ResponseObject> deleteReportData(@PathVariable String reportId) {
        log.info("deleteReportData() : Start, id is {}", reportId);
        return reportDataService.deleteReportById(reportId);
    }
}
