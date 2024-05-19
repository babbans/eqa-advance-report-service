package com.eqa.eqaaprsummaryservice.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseObject {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD-MM-YYYY HH:MM:SS")
	private LocalDateTime timestamp;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String transactionId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String status;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String code;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String error;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object obj;
	
}
