package com.eqa.eqaadvancereportservice.service;

import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportTaskConstant;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
import com.eqa.eqaadvancereportservice.entity.ReportMaster;
import com.eqa.eqaadvancereportservice.exception.CustomException;
import com.eqa.eqaadvancereportservice.repository.ReportMasterRepository;
import com.eqa.eqaadvancereportservice.util.CommonUtils;
import com.eqa.eqaadvancereportservice.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AnnualProgramReportMasterService {

    @Autowired
    private ReportMasterRepository reportMasterRepository;

    @Autowired
    private MessageUtil messageUtil;

    public ResponseEntity<ResponseObject> findAll() throws CustomException {
        try {
            List<ReportMaster> reportMasterList = reportMasterRepository.findAll();
            log.info("AnnualProgramReports fetched successfully from DB");

            // Fetch the localized message using MessageUtil
            String localizedMessage = messageUtil.getLocalizedMessage(
                    AnnualProgramReportTaskConstant.APR_REPORTS_LIST_SUCCESS.getBusinessCode()
            );

            return CommonUtils.buildResponseEntity(
                    Arrays.asList(localizedMessage),
                    AnnualProgramReportTaskConstant.APR_REPORTS_LIST_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)),
                    reportMasterList,
                    String.valueOf(AnnualProgramReportTaskConstant.APR_REPORTS_LIST_SUCCESS.getHttpStatus().value()),
                    null,
                    new HttpHeaders(),
                    AnnualProgramReportTaskConstant.APR_REPORTS_LIST_SUCCESS.getHttpStatus()
            );
        } catch (Exception ex) {
            log.error("Error while fetching AnnualProgramReports list {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportTaskConstant.APR_REPORTS_LIST_FAILED);
        }
    }

    public ResponseEntity<ResponseObject> findByGivenCriteria(String collegeId, String departmentId, String programId, Integer academicYear) throws CustomException {
        Optional<ReportMaster> reportMasterOptional = reportMasterRepository.findByCollegeIdAndDepartmentIdAndProgramIdAndAcademicYear(
                collegeId, departmentId, programId, academicYear
        );

        if (!reportMasterOptional.isPresent()) {
            throw new CustomException(AnnualProgramReportTaskConstant.APR_REPORT_NOT_FOUND);
        }

        try {
            // Fetch the localized message using MessageUtil
            String localizedMessage = messageUtil.getLocalizedMessage(
                    AnnualProgramReportTaskConstant.APR_REPORT_GET_SUCCESS.getBusinessCode()
            );

            return CommonUtils.buildResponseEntity(
                    Arrays.asList(localizedMessage),
                    AnnualProgramReportTaskConstant.APR_REPORT_GET_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)),
                    reportMasterOptional.get(),
                    String.valueOf(AnnualProgramReportTaskConstant.APR_REPORT_GET_SUCCESS.getHttpStatus().value()),
                    null,
                    new HttpHeaders(),
                    AnnualProgramReportTaskConstant.APR_REPORT_GET_SUCCESS.getHttpStatus()
            );
        } catch (Exception ex) {
            log.error("Error while fetching AnnualProgramReport {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportTaskConstant.APR_REPORT_NOT_FOUND);
        }
    }
}

