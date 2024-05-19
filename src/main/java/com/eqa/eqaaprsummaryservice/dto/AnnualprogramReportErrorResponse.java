package com.eqa.eqaaprsummaryservice.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnualprogramReportErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private String code;

}
