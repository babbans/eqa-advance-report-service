package com.eqa.eqaadvancereportservice.service;

import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportSettingConstant;
import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportTaskConstant;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
import com.eqa.eqaadvancereportservice.entity.AnnualProgramReportSetting;
import com.eqa.eqaadvancereportservice.exception.CustomException;
import com.eqa.eqaadvancereportservice.exception.UnauthorizedException;
import com.eqa.eqaadvancereportservice.repository.AnnualProgramReportSettingRepository;
import com.eqa.eqaadvancereportservice.util.CommonUtils;
import com.eqa.eqaadvancereportservice.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class AnnualProgramReportSettingService {

    @Autowired
    private AnnualProgramReportSettingRepository settingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageUtil messageUtil;

    public ResponseEntity<ResponseObject> findAll() throws CustomException {
        try {
            List<AnnualProgramReportSetting> settings = settingRepository.findAll();
            log.info("AnnualProgramReportSetting list fetched successfully from DB");

            String localizedMessage = messageUtil.getLocalizedMessage(
                    AnnualProgramReportSettingConstant.APR_SETTING_LIST_SUCCESS.getBusinessCode()
            );

            return CommonUtils.buildResponseEntity(
                    Arrays.asList(localizedMessage),
                    AnnualProgramReportSettingConstant.APR_SETTING_LIST_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)),
                    settings,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_SETTING_LIST_SUCCESS.getHttpStatus().value()),
                    null,
                    new HttpHeaders(),
                    AnnualProgramReportSettingConstant.APR_SETTING_LIST_SUCCESS.getHttpStatus()
            );
        } catch (Exception ex) {
            log.error("Error while fetching AnnualProgramReportSetting list {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_SETTING_LIST_FAILED);
        }
    }

    public ResponseEntity<ResponseObject> save(List<AnnualProgramReportSetting> settings) {
        try {
            List<AnnualProgramReportSetting> savedSettings = settingRepository.saveAll(settings);
            log.info("AnnualProgramReportSetting saved successfully");

            String localizedMessage = messageUtil.getLocalizedMessage(
                    AnnualProgramReportSettingConstant.APR_SETTING_CREATE_SUCCESS.getBusinessCode()
            );

            return CommonUtils.buildResponseEntity(
                    Arrays.asList(localizedMessage),
                    AnnualProgramReportSettingConstant.APR_SETTING_CREATE_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)),
                    savedSettings,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_SETTING_CREATE_SUCCESS.getHttpStatus().value()),
                    null,
                    new HttpHeaders(),
                    AnnualProgramReportSettingConstant.APR_SETTING_CREATE_SUCCESS.getHttpStatus()
            );
        } catch (Exception ex) {
            if (ex instanceof UnauthorizedException) {
                throw new CustomException(AnnualProgramReportTaskConstant.APR_TASK_UNAUTHORIZED_ACCESS);
            }
            log.error("Error while saving AnnualProgramReportSetting {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_SETTING_CREATION_FAILED);
        }
    }

    public ResponseEntity<ResponseObject> updateSetting(AnnualProgramReportSetting setting, long id) throws CustomException {
        AnnualProgramReportSetting existingSetting = getExistingSetting(id);
        try {
            modelMapper.map(setting, existingSetting);
            AnnualProgramReportSetting updatedSetting = settingRepository.save(existingSetting);
            log.info("Report Setting updated successfully");

            String localizedMessage = messageUtil.getLocalizedMessage(
                    AnnualProgramReportSettingConstant.APR_SETTING_UPDATE_SUCCESS.getBusinessCode()
            );

            return CommonUtils.buildResponseEntity(
                    Arrays.asList(localizedMessage),
                    AnnualProgramReportSettingConstant.APR_SETTING_UPDATE_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)),
                    updatedSetting,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_SETTING_UPDATE_SUCCESS.getHttpStatus().value()),
                    null,
                    new HttpHeaders(),
                    AnnualProgramReportSettingConstant.APR_SETTING_UPDATE_SUCCESS.getHttpStatus()
            );
        } catch (Exception ex) {
            if (ex instanceof UnauthorizedException) {
                throw new CustomException(AnnualProgramReportTaskConstant.APR_TASK_UNAUTHORIZED_ACCESS);
            }
            log.error("Error while updating Setting {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_SETTING_UPDATE_FAILED);
        }
    }

    public ResponseEntity<ResponseObject> findById(Long id) throws CustomException {
        AnnualProgramReportSetting existingReportSetting = getExistingSetting(id);
        try {
            String localizedMessage = messageUtil.getLocalizedMessage(
                    AnnualProgramReportSettingConstant.APR_SETTING_GET_SUCCESS.getBusinessCode()
            );

            return CommonUtils.buildResponseEntity(
                    Arrays.asList(localizedMessage),
                    AnnualProgramReportSettingConstant.APR_SETTING_GET_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)),
                    existingReportSetting,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_SETTING_GET_SUCCESS.getHttpStatus().value()),
                    null,
                    new HttpHeaders(),
                    AnnualProgramReportSettingConstant.APR_SETTING_GET_SUCCESS.getHttpStatus()
            );
        } catch (Exception ex) {
            log.error("Error while fetching AnnualProgramReportSetting {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_SETTING_GET_FAILED);
        }
    }

    public ResponseEntity<ResponseObject> deleteSetting(List<Long> ids) throws CustomException {
        try {
            settingRepository.deleteWithIds(ids);
            log.info("AnnualProgramReportSetting deleted with ids {}", ids);

            String localizedMessage = messageUtil.getLocalizedMessage(
                    AnnualProgramReportSettingConstant.APR_SETTING_DELETE_SUCCESS.getBusinessCode()
            );

            return CommonUtils.buildResponseEntity(
                    Arrays.asList(localizedMessage),
                    AnnualProgramReportSettingConstant.APR_SETTING_DELETE_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)),
                    null,
                    String.valueOf(AnnualProgramReportSettingConstant.APR_SETTING_DELETE_SUCCESS.getHttpStatus().value()),
                    null,
                    new HttpHeaders(),
                    AnnualProgramReportSettingConstant.APR_SETTING_DELETE_SUCCESS.getHttpStatus()
            );
        } catch (Exception ex) {
            if (ex instanceof UnauthorizedException) {
                throw new CustomException(AnnualProgramReportTaskConstant.APR_TASK_UNAUTHORIZED_ACCESS);
            }
            log.error("Error while deleting AnnualProgramReportSetting {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportSettingConstant.APR_SETTING_DELETION_FAILED);
        }
    }

    private AnnualProgramReportSetting getExistingSetting(Long id) {
        return settingRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("AnnualProgramReportSetting not found with id {}", id);
                    return new CustomException(AnnualProgramReportSettingConstant.APR_SETTING_NOT_FOUND);
                });
    }
}
