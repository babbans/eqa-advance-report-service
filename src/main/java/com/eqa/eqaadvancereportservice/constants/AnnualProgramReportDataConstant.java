package com.eqa.eqaadvancereportservice.constants;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

public enum AnnualProgramReportDataConstant {

	TECHNICAL_ERROR_DATA("EQA-APR-DATA.0000", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_DATA_UNAUTHORIZED_ACCESS("EQA-APR-DATA.0003", HttpStatus.FORBIDDEN),
	APR_DATA_CREATION_FAILED("EQA-APR-DATA.0001", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_DATA_DELETION_FAILED("EQA-APR-DATA.0003", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_DATA_NOT_FOUND("EQA-APR-DATA.0004", HttpStatus.NOT_FOUND),
	APR_DATA_BAD_REQUEST_ERROR("EQA-APR-DATA.0005", HttpStatus.BAD_REQUEST),
	APR_DATA_CREATE_SUCCESS("EQA-APR-DATA.2000", HttpStatus.CREATED),
	APR_DATA_DELETE_SUCCESS("EQA-APR-DATA.2003", HttpStatus.OK),
	APR_DATA_GET_FAILED("EQA-APR-DATA.2006", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_DATA_GET_SUCCESS("EQA-APR-DATA.2007", HttpStatus.OK);

	private String businessCode;
	private HttpStatus httpStatus;

	AnnualProgramReportDataConstant(String businessCode, HttpStatus httpStatus) {
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
