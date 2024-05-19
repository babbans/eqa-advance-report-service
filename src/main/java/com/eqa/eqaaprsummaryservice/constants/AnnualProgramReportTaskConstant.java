package com.eqa.eqaaprsummaryservice.constants;

import org.springframework.http.HttpStatus;

public enum AnnualProgramReportTaskConstant {

	TECHNICAL_ERROR("EQA-APR-TASK.0000", "Technical error occurred!", HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_CREATION_FAILED("EQA-APR-TASK.0001", "Error occurred while saving Annual Program Report's Task(s).",
			HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_UPDATE_FAILED("EQA-APR-TASK.0002", "Error occurred while updating Annual Program Report's Task.",
			HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_DELETION_FAILED("EQA-APR-TASK.0003", "Error occurred while deleting Annual Program Report's Task.",
			HttpStatus.INTERNAL_SERVER_ERROR),
	APR_TASK_NOT_FOUND("EQA-APR-TASK.0004", "Annual Program Report's Task not found with given id", HttpStatus.NOT_FOUND),

	APR_TASK_BAD_REQUEST_ERROR("EQA-APR-TASK.0005","Invalid input!",HttpStatus.BAD_REQUEST),

	APR_TASK_CREATE_SUCCESS("EQA-APR-TASK.2000", "Annual Program Report's Task(s) has/have been assigned successfully.", HttpStatus.CREATED),

	APR_TASK_UPDATE_SUCCESS("EQA-APR-TASK.2001", "Annual Program Report's Task updated successfully.", HttpStatus.OK),
	APR_TASK_DELETE_SUCCESS("EQA-APR-TASK.2003", "Annual Program Report's Task deleted successfully.", HttpStatus.OK),
	APR_TASK_LIST_SUCCESS("EQA-APR-TASK.2004", "Annual Program Report's Task list fetched successfully.", HttpStatus.OK),
	APR_TASK_LIST_FAILED("EQA-APR-TASK.2005", "Error while fetching Annual Program Report's Task list.", HttpStatus.OK),

	APR_TASK_GET_FAILED("EQA-APR-TASK.2006", "Error while fetching Annual Program Report's Task.", HttpStatus.OK),
	APR_TASK_GET_SUCCESS("EQA-APR-TASK.2007", "Annual Program Report's Task fetched successfully.", HttpStatus.OK);


	private String businessCode;

	private String businessMsg;

	private HttpStatus httpStatus;

	private AnnualProgramReportTaskConstant(String businessCode, String businessMsg, HttpStatus httpStatus) {
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
