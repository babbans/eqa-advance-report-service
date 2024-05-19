package com.eqa.eqaaprsummaryservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class IdentityService {

    @Value("${identity.service.url}")
    private String identityServiceUrl;

    private final RestTemplate restTemplate;

    public IdentityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isUserAuthorized(String username) {
        try {
            String url = identityServiceUrl + username;
            return true;
//            UserPrivilegesResponse response = restTemplate.getForObject(url, UserPrivilegesResponse.class);
//            return response != null && response.getObject().getUser().isSuperUser()
//                || response.getObject().getUser().isStaff()
//                || response.getObject().getUser().isDepartmentCoordinator();
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    @Data
    public static class UserPrivilegesResponse {
        private String status;
        private String message;
        private UserObject object;
        @Data
        public static class UserObject {
            private User user;

            @Data
            public static class User {
                private boolean superUser;
                private boolean staff;
                private boolean departmentCoordinator;

                // Getters and setters

                public boolean isSuperUser() {
                    return superUser;
                }

                public boolean isStaff() {
                    return staff;
                }

                public boolean isDepartmentCoordinator() {
                    return departmentCoordinator;
                }
            }
        }
    }
}
