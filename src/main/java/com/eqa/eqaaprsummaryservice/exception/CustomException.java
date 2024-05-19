package com.eqa.eqaaprsummaryservice.exception;

import com.eqa.eqaaprsummaryservice.constants.AnnualProgramReportSettingConstant;
import com.eqa.eqaaprsummaryservice.constants.AnnualProgramReportTaskConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	private AnnualProgramReportSettingConstant settingConstant;
	
	private AnnualProgramReportTaskConstant taskConstant;

	public CustomException(AnnualProgramReportSettingConstant settingConstant) {
		super(settingConstant.getBusinessMsg());
		this.settingConstant = settingConstant;
	}
	
	public CustomException(AnnualProgramReportTaskConstant taskConstant) {
		super(taskConstant.getBusinessMsg());
		this.taskConstant = taskConstant;
	}

	public CustomException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message) {
		super(message);
	}

	public CustomException(Throwable cause) {
		super(cause);
	}
	
	
}
