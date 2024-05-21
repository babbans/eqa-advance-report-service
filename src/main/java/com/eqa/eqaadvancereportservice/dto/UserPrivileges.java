package com.eqa.eqaadvancereportservice.dto;

import lombok.Data;

@Data
public class UserPrivileges {
    private String userId;
    private String userName;
    private boolean departmentCoordinator;
    private boolean superUser;
    private boolean staff;
}
