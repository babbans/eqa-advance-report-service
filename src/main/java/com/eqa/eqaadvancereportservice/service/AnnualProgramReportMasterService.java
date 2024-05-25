package com.eqa.eqaadvancereportservice.service;

import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportTaskConstant;
import com.eqa.eqaadvancereportservice.dto.AnnualProgramReportTaskDetailDTO;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

}
