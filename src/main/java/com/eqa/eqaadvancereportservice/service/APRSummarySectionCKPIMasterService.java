package com.eqa.eqaadvancereportservice.service;

import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportSettingConstant;
import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportTaskConstant;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
import com.eqa.eqaadvancereportservice.entity.APRSummarySectionC_KPI_MASTER;
import com.eqa.eqaadvancereportservice.entity.AnnualProgramReportSetting;
import com.eqa.eqaadvancereportservice.exception.CustomException;
import com.eqa.eqaadvancereportservice.exception.UnauthorizedException;
import com.eqa.eqaadvancereportservice.repository.APRSummarySectionCKPIMasterRepository;
import com.eqa.eqaadvancereportservice.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class APRSummarySectionCKPIMasterService {
    @Autowired
    private APRSummarySectionCKPIMasterRepository kpiMasterRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<ResponseObject> findAll() throws CustomException {
        try {
            List<APRSummarySectionC_KPI_MASTER> kpiList = kpiMasterRepository.findAll();;
            log.info("AnnualProgramReportKpi list fetched successfully from DB");
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportSettingConstant.APR_KPI_LIST_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportSettingConstant.APR_KPI_LIST_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), kpiList,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_KPI_LIST_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportSettingConstant.APR_KPI_LIST_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            log.error("Error while fetching AnnualProgramReportKpi list {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_KPI_LIST_FAILED);
        }
    }
    public ResponseEntity<ResponseObject> save(List<APRSummarySectionC_KPI_MASTER> data) {
        try {
            List<APRSummarySectionC_KPI_MASTER> savedKpi = kpiMasterRepository.saveAll(data);
            log.info("AnnualProgramReportKpi saved successfully");
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportSettingConstant.APR_KPI_CREATE_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportSettingConstant.APR_KPI_CREATE_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), savedKpi,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_KPI_CREATE_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportSettingConstant.APR_KPI_CREATE_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            if(ex instanceof UnauthorizedException){
                throw new CustomException(AnnualProgramReportTaskConstant.APR_TASK_UNAUTHORIZED_ACCESS);
            }
            log.error("Error while saving AnnualProgramReportSetting {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_KPI_CREATION_FAILED);
        }
    }
    public ResponseEntity<ResponseObject> updateKpi(APRSummarySectionC_KPI_MASTER data, long id)  throws CustomException {
        APRSummarySectionC_KPI_MASTER existingKpi = getExistingKpi(id);
        try {
            modelMapper.map(data, existingKpi);
            APRSummarySectionC_KPI_MASTER updatedSetting = kpiMasterRepository.save(existingKpi);
            log.info("Report Kpi updated successfully");
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportSettingConstant.APR_KPI_UPDATE_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportSettingConstant.APR_KPI_UPDATE_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), updatedSetting,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_KPI_UPDATE_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportSettingConstant.APR_KPI_UPDATE_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            if(ex instanceof UnauthorizedException){
                throw new CustomException(AnnualProgramReportTaskConstant.APR_TASK_UNAUTHORIZED_ACCESS);
            }
            log.error("Error while updating Kpi {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_KPI_UPDATE_FAILED);
        }
    }

    public ResponseEntity<ResponseObject> findById(Long id) throws CustomException {
        APRSummarySectionC_KPI_MASTER existingKpi = getExistingKpi(id);
        try {
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportSettingConstant.APR_KPI_GET_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportSettingConstant.APR_KPI_GET_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), existingKpi,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_KPI_GET_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportSettingConstant.APR_KPI_GET_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            log.error("Error while fetching AnnualProgramReportKpi {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_KPI_GET_FAILED);
        }
    }

    public ResponseEntity<ResponseObject> deleteKpi(List<Long> ids) throws CustomException {
        try {
            kpiMasterRepository.deleteWithIds(ids);
            log.info("AnnualProgramReportKpi deleted with ids {}", ids);
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportSettingConstant.APR_KPI_DELETE_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportSettingConstant.APR_KPI_DELETE_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), null,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_KPI_DELETE_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportSettingConstant.APR_KPI_DELETE_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            if(ex instanceof UnauthorizedException){
                throw new CustomException(AnnualProgramReportTaskConstant.APR_TASK_UNAUTHORIZED_ACCESS);
            }
            log.error("Error while deleting AnnualProgramReportKpi {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_KPI_DELETION_FAILED);
        }
    }

    public ResponseEntity<ResponseObject> getKpisByDegree(String degree) {
        List<APRSummarySectionC_KPI_MASTER> kpiList = kpiMasterRepository.findByDegree(degree);
        try {
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportSettingConstant.APR_KPI_GET_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportSettingConstant.APR_KPI_GET_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), kpiList,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_KPI_GET_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportSettingConstant.APR_KPI_GET_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            log.error("Error while fetching AnnualProgramReportKpi {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_KPI_GET_FAILED);
        }
    }
    private APRSummarySectionC_KPI_MASTER getExistingKpi(Long id) {
        return kpiMasterRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("AnnualProgramReportKpi not found with id {}", id);
                    return new CustomException(AnnualProgramReportSettingConstant.APR_KPI_NOT_FOUND);
                });
    }
}
