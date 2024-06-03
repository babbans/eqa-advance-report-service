package com.eqa.eqaadvancereportservice.exception;

import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportSettingConstant;
import com.eqa.eqaadvancereportservice.dto.AnnualprogramReportErrorResponse;
import com.eqa.eqaadvancereportservice.util.CommonUtils;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionAdviceHandler {

	private static String STATUS = "Failed";
	@Autowired
	private MessageSource messageSource;
	@ExceptionHandler({ CustomException.class })
	public ResponseEntity<?> handleDirectoryException(CustomException exception) {
		List<String> errorMsgList = new ArrayList<>();
		int statusCode = -1;
		String businessMsg = "";
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if (Objects.nonNull(exception.getSettingConstant())) {
			errorMsgList.add(exception.getSettingConstant().getBusinessMsg(messageSource));
			statusCode = exception.getSettingConstant().getHttpStatus().value();
			businessMsg = exception.getSettingConstant().getBusinessMsg(messageSource);
			httpStatus = exception.getSettingConstant().getHttpStatus();
			STATUS = exception.getSettingConstant().getHttpStatus().name();
		} else if (Objects.nonNull(exception.getTaskConstant())) {
			errorMsgList.add(exception.getTaskConstant().getBusinessMsg(messageSource));
			statusCode = exception.getTaskConstant().getHttpStatus().value();
			businessMsg = exception.getTaskConstant().getBusinessMsg(messageSource);
			httpStatus = exception.getTaskConstant().getHttpStatus();
			STATUS = exception.getTaskConstant().getHttpStatus().name();
		}  else if (Objects.nonNull(exception.getDataConstant())) {
			errorMsgList.add(exception.getDataConstant().getBusinessMsg(messageSource));
			statusCode = exception.getDataConstant().getHttpStatus().value();
			businessMsg = exception.getDataConstant().getBusinessMsg(messageSource);
			httpStatus = exception.getDataConstant().getHttpStatus();
			STATUS = exception.getDataConstant().getHttpStatus().name();
		}
		return CommonUtils.buildResponseEntity(errorMsgList, STATUS, String.valueOf(Math.round(Math.random() * 100)),
				null, statusCode + "", businessMsg, new HttpHeaders(), httpStatus);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<AnnualprogramReportErrorResponse> handleGeneralException(HttpStatusCodeException exception) {
		AnnualprogramReportErrorResponse directoryErrorResponse = new AnnualprogramReportErrorResponse(exception.getStatusText(),
				exception.getStatusCode().value() + "");
		return new ResponseEntity<>(directoryErrorResponse, exception.getStatusCode());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
		return CommonUtils.buildResponseEntity(Arrays.asList(ex.getMessage()), STATUS,
				String.valueOf(Math.round(Math.random() * 100)), null, HttpStatus.BAD_REQUEST.value() + "",
				ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<?> handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
		List<String> errorMsgList = new ArrayList<>();
		ex.getAllErrors().forEach(error -> {
			errorMsgList.add(error.getDefaultMessage());
		});
		return CommonUtils.buildResponseEntity(errorMsgList, STATUS, String.valueOf(Math.round(Math.random() * 100)),
				null, HttpStatus.BAD_REQUEST.value() + "", ex.getReason(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> handleMultipartException(HttpMediaTypeNotSupportedException ex) {
		return CommonUtils.buildResponseEntity(Arrays.asList(ex.getDetailMessageCode()), STATUS,
				String.valueOf(Math.round(Math.random() * 100)), null, ex.getBody().getStatus() + "",
				ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.valueOf(ex.getStatusCode().value()));

	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<?> handleBindException(BindException ex) {
		List<String> errorMsgList = new ArrayList<>();
		ex.getFieldErrors().forEach(error -> {
			errorMsgList.add(error.getDefaultMessage());
		});

		return CommonUtils.buildResponseEntity(errorMsgList,
				String.valueOf(AnnualProgramReportSettingConstant.APR_SETTING_BAD_REQUEST_ERROR.getHttpStatus().getReasonPhrase()),
				String.valueOf(Math.round(Math.random() * 100)), null,
				AnnualProgramReportSettingConstant.APR_SETTING_BAD_REQUEST_ERROR.getHttpStatus().value() + "",
				AnnualProgramReportSettingConstant.APR_SETTING_BAD_REQUEST_ERROR.getBusinessMsg(messageSource), new HttpHeaders(),
				AnnualProgramReportSettingConstant.APR_SETTING_BAD_REQUEST_ERROR.getHttpStatus());
	}

}
