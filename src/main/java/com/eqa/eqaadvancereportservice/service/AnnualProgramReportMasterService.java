package com.eqa.eqaadvancereportservice.service;

import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportSettingConstant;
import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportTaskConstant;
import com.eqa.eqaadvancereportservice.dto.AnnualProgramReportTaskDetailDTO;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
import com.eqa.eqaadvancereportservice.entity.AnnualProgramReportSetting;
import com.eqa.eqaadvancereportservice.entity.AnnualProgramReportTaskDetail;
import com.eqa.eqaadvancereportservice.entity.ReportMaster;
import com.eqa.eqaadvancereportservice.exception.CustomException;
import com.eqa.eqaadvancereportservice.exception.UnauthorizedException;
import com.eqa.eqaadvancereportservice.repository.AnnualProgramReportTaskDetailRepository;
import com.eqa.eqaadvancereportservice.repository.ReportMasterRepository;
import com.eqa.eqaadvancereportservice.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AnnualProgramReportMasterService {

    @Autowired
    private ReportMasterRepository reportMasterRepository;

    public ResponseEntity<ResponseObject> findAll() throws CustomException {
        try {
            List<ReportMaster> reportMasterList = reportMasterRepository.findAll();
            log.info("AnnualProgramReports fetched successfully from DB");
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportTaskConstant.APR_REPORTS_LIST_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportTaskConstant.APR_REPORTS_LIST_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), reportMasterList,
                    String.valueOf(AnnualProgramReportTaskConstant.APR_REPORTS_LIST_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportTaskConstant.APR_REPORTS_LIST_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            log.error("Error while fetching AnnualProgramReports list {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportTaskConstant.APR_REPORTS_LIST_FAILED);
        }
    }
    public ResponseEntity<ResponseObject> findByGivenCriteria(String collegeId, String departmentId, String programId, Integer academicYear) throws CustomException {
        Optional<ReportMaster> reportMasterOptional = reportMasterRepository.findByCollegeIdAndDepartmentIdAndProgramIdAndAcademicYear(
                collegeId, departmentId, programId, academicYear);
        if(!reportMasterOptional.isPresent()){
            throw new CustomException(AnnualProgramReportTaskConstant.APR_REPORT_NOT_FOUND);
        }
        try {
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportTaskConstant.APR_REPORT_GET_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportTaskConstant.APR_REPORT_GET_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), reportMasterOptional.get(),
                    String.valueOf(AnnualProgramReportTaskConstant.APR_REPORT_GET_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportTaskConstant.APR_REPORT_GET_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            log.error("Error while fetching AnnualProgramReport {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportTaskConstant.APR_REPORT_NOT_FOUND);
        }
    }

}
