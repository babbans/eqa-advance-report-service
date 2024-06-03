package com.eqa.eqaadvancereportservice.constants;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

public enum AnnualProgramReportTaskConstant {

	APR_TASK_UNAUTHORIZED_ACCESS("EQA-APR-TASK.4003", HttpStatus.FORBIDDEN),

	TECHNICAL_ERROR("EQA-APR-TASK.0000", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_CREATION_FAILED("EQA-APR-TASK.0001", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_UPDATE_FAILED("EQA-APR-TASK.0002", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_DELETION_FAILED("EQA-APR-TASK.0003", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_NOT_FOUND("EQA-APR-TASK.0004", HttpStatus.NOT_FOUND),
	APR_TASK_BAD_REQUEST_ERROR("EQA-APR-TASK.0005", HttpStatus.BAD_REQUEST),
	APR_TASK_CREATE_SUCCESS("EQA-APR-TASK.0006", HttpStatus.CREATED),
	APR_TASK_UPDATE_SUCCESS("EQA-APR-TASK.0007", HttpStatus.OK),
	APR_TASK_DELETE_SUCCESS("EQA-APR-TASK.0008", HttpStatus.OK),
	APR_TASK_LIST_SUCCESS("EQA-APR-TASK.0009", HttpStatus.OK),
	APR_TASK_LIST_FAILED("EQA-APR-TASK.1001", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_GET_FAILED("EQA-APR-TASK.1002", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_GET_SUCCESS("EQA-APR-TASK.1003", HttpStatus.OK),
	APR_REPORTS_LIST_SUCCESS("EQA-APR-REPORT.0001", HttpStatus.OK),
	APR_REPORTS_LIST_FAILED("EQA-APR-REPORT.0002", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_REPORT_NOT_FOUND("EQA-APR-REPORT.0003", HttpStatus.NOT_FOUND),
	APR_REPORT_GET_FAILED("EQA-APR-REPORT.0004", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_REPORT_GET_SUCCESS("EQA-APR-REPORT.0005", HttpStatus.OK);

	private String businessCode;
	private HttpStatus httpStatus;

	AnnualProgramReportTaskConstant(String businessCode, HttpStatus httpStatus) {
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

