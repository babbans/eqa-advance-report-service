package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class IdentityServiceResponse {
    private String timestamp;
    private String transactionId;
    private String status;
    private String code;
    private List<String> message;
    private UserPrivileges obj;

}
