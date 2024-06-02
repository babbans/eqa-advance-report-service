package com.eqa.eqaadvancereportservice.constants;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

public enum AnnualProgramReportSettingConstant {

	TECHNICAL_ERROR_SETTING("EQA-APR-SETTING.0000", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_SETTING_CREATION_FAILED("EQA-APR-SETTING.0001", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_SETTING_UPDATE_FAILED("EQA-APR-SETTING.0002", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_SETTING_DELETION_FAILED("EQA-APR-SETTING.0003", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_SETTING_NOT_FOUND("EQA-APR-SETTING.0004", HttpStatus.NOT_FOUND),
	APR_SETTING_BAD_REQUEST_ERROR("EQA-APR-SETTING.0005", HttpStatus.BAD_REQUEST),
	APR_SETTING_CREATE_SUCCESS("EQA-APR-SETTING.2000", HttpStatus.CREATED),
	APR_SETTING_UPDATE_SUCCESS("EQA-APR-SETTING.2001", HttpStatus.OK),
	APR_SETTING_DELETE_SUCCESS("EQA-APR-SETTING.2003", HttpStatus.OK),
	APR_SETTING_LIST_SUCCESS("EQA-APR-SETTING.2004", HttpStatus.OK),
	APR_SETTING_LIST_FAILED("EQA-APR-SETTING.2005", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_SETTING_GET_FAILED("EQA-APR-SETTING.2006", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_SETTING_GET_SUCCESS("EQA-APR-SETTING.2007", HttpStatus.OK),

	// EQA-APR-KPI Messages
	APR_KPI_CREATION_FAILED("EQA-APR-KPI.0001", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_KPI_UPDATE_FAILED("EQA-APR-KPI.0002", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_KPI_DELETION_FAILED("EQA-APR-KPI.0003", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_KPI_NOT_FOUND("EQA-APR-KPI.0004", HttpStatus.NOT_FOUND),
	APR_KPI_BAD_REQUEST_ERROR("EQA-APR-KPI.0005", HttpStatus.BAD_REQUEST),
	APR_KPI_CREATE_SUCCESS("EQA-APR-KPI.2000", HttpStatus.CREATED),
	APR_KPI_UPDATE_SUCCESS("EQA-APR-KPI.2001", HttpStatus.OK),
	APR_KPI_DELETE_SUCCESS("EQA-APR-KPI.2003", HttpStatus.OK),
	APR_KPI_LIST_SUCCESS("EQA-APR-KPI.2004", HttpStatus.OK),
	APR_KPI_LIST_FAILED("EQA-APR-KPI.2005", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_KPI_GET_FAILED("EQA-APR-KPI.2006", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_KPI_GET_SUCCESS("EQA-APR-KPI.2007", HttpStatus.OK);

	private String businessCode;
	private HttpStatus httpStatus;

	AnnualProgramReportSettingConstant(String businessCode, HttpStatus httpStatus) {
		this.businessCode = businessCode;
		this.httpStatus = httpStatus;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getBusinessMsg(MessageSource messageSource) {
		return messageSource.getMessage(businessCode, null, LocaleContextHolder.getLocale());
	}

}
