package com.eqa.eqaadvancereportservice.constants;

import org.springframework.http.HttpStatus;

public enum AnnualProgramReportDataConstant {

	TECHNICAL_ERROR("EQA-APR-DATA.0000", "Technical error occurred!", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_DATA_UNAUTHORIZED_ACCESS("EQA-APR-TASK.0003", "You are unAuthorized to access this endpoint.",
			HttpStatus.FORBIDDEN),
	APR_DATA_CREATION_FAILED("EQA-APR-DATA.0001", "Error occurred while saving or updating report data.",
			HttpStatus.INTERNAL_SERVER_ERROR),

	APR_DATA_DELETION_FAILED("EQA-APR-DATA.0003", "Error occurred while deleting Annual Program Report data.",
			HttpStatus.INTERNAL_SERVER_ERROR),
	APR_DATA_NOT_FOUND("EQA-APR-DATA.0004", "Annual Program Report Data not found with given id", HttpStatus.NOT_FOUND),

	APR_DATA_BAD_REQUEST_ERROR("EQA-APR-DATA.0005","Invalid input!",HttpStatus.BAD_REQUEST),

	APR_DATA_CREATE_SUCCESS("EQA-APR-DATA.2000", "Annual Program Report Data has been saved/updated  successfully.", HttpStatus.CREATED),

	APR_DATA_DELETE_SUCCESS("EQA-APR-DATA.2003", "Annual Program Report Data has been deleted successfully.", HttpStatus.OK),

	APR_DATA_GET_FAILED("EQA-APR-DATA.2006", "Error while fetching Annual Program Report Data.", HttpStatus.OK),
	APR_DATA_GET_SUCCESS("EQA-APR-DATA.2007", "Annual Program Report Data fetched successfully.", HttpStatus.OK);

	private String businessCode;

	private String businessMsg;

	private HttpStatus httpStatus;

	private AnnualProgramReportDataConstant(String businessCode, String businessMsg, HttpStatus httpStatus) {
		this.businessCode = businessCode;
		this.businessMsg = businessMsg;
		this.httpStatus = httpStatus;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getBusinessMsg() {
		return businessMsg;
	}

	public void setBusinessMsg(String businessMsg) {
		this.businessMsg = businessMsg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
