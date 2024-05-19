package com.eqa.eqaaprsummaryservice.constants;

import org.springframework.http.HttpStatus;

public enum AnnualProgramReportSettingConstant {

	TECHNICAL_ERROR("EQA-APR-SETTING.0000", "Technical error occurred!", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_UNAUTHORIZED_ACCESS("EQA-APR-TASK.0003", "You are unAuthorized to access this endpoint.",
			HttpStatus.FORBIDDEN),
	APR_SETTING_CREATION_FAILED("EQA-APR-SETTING.0001", "Error occurred while saving Annual Report Section(s).",
			HttpStatus.INTERNAL_SERVER_ERROR),
	APR_SETTING_UPDATE_FAILED("EQA-APR-SETTING.0002", "Error occurred while updating Annual Report Section(s).",
			HttpStatus.INTERNAL_SERVER_ERROR),
	APR_SETTING_DELETION_FAILED("EQA-APR-SETTING.0003", "Error occurred while deleting Annual Report Section(s).",
			HttpStatus.INTERNAL_SERVER_ERROR),
	APR_SETTING_NOT_FOUND("EQA-APR-SETTING.0004", "Annual Report Section not found with given id", HttpStatus.NOT_FOUND),

	APR_SETTING_BAD_REQUEST_ERROR("EQA-APR-SETTING.0005","Invalid input!",HttpStatus.BAD_REQUEST),

	APR_SETTING_CREATE_SUCCESS("EQA-APR-SETTING.2000", "Annual Report Section(s) has been saved successfully.", HttpStatus.CREATED),

	APR_SETTING_UPDATE_SUCCESS("EQA-APR-SETTING.2001", "Annual Report Section has been updated successfully.", HttpStatus.OK),
	APR_SETTING_DELETE_SUCCESS("EQA-APR-SETTING.2003", "Annual Report Section(s) has been deleted successfully.", HttpStatus.OK),
	APR_SETTING_LIST_SUCCESS("EQA-APR-SETTING.2004", "Annual Report Section(s) list fetched successfully.", HttpStatus.OK),
	APR_SETTING_LIST_FAILED("EQA-APR-SETTING.2005", "Error while fetching Annual Report Section(s) list.", HttpStatus.OK),

	APR_SETTING_GET_FAILED("EQA-APR-SETTING.2006", "Error while fetching Annual Report Section(s).", HttpStatus.OK),
	APR_SETTING_GET_SUCCESS("EQA-APR-SETTING.2007", "Annual Report Section(s) fetched successfully.", HttpStatus.OK);

	private String businessCode;

	private String businessMsg;

	private HttpStatus httpStatus;

	private AnnualProgramReportSettingConstant(String businessCode, String businessMsg, HttpStatus httpStatus) {
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
