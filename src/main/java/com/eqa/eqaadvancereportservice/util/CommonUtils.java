package com.eqa.eqaadvancereportservice.util;

import com.ams.core.common.utilities.IdGenerator;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CommonUtils {
	private CommonUtils() {
	}

	public static ResponseEntity<ResponseObject> buildResponseEntity(List<String> message, String status, String id,
																	 Object obj, String code, String error, HttpHeaders headers, HttpStatus httpStatus) {
		ResponseObject responseObject = new ResponseObject();
		responseObject.setMessage(message);
		responseObject.setTransactionId(IdGenerator.generateTransactionId());
		responseObject.setStatus(status);
		responseObject.setObj(obj);
		responseObject.setError(error);
		responseObject.setCode(code);
		responseObject.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(responseObject, headers, httpStatus);
	}

}
