package com.eqa.eqaadvancereportservice.config;

import com.eqa.eqaadvancereportservice.dto.IdentityServiceResponse;
import com.eqa.eqaadvancereportservice.dto.UserPrivileges;
import com.eqa.eqaadvancereportservice.exception.UnauthorizedException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class IdentityService {

    @Value("${identity.service.url}")
    private String identityServiceUrl;

    private final RestTemplate restTemplate;

    public IdentityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserPrivileges getUserPrivileges(String username, String apiKey) {
        try {
            String url = identityServiceUrl + username;
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", apiKey);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<IdentityServiceResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, IdentityServiceResponse.class);
            log.info("Response from from privilege api is {}", response);
            HttpStatusCode statusCode = response.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                IdentityServiceResponse responseBody = response.getBody();
                if(responseBody != null && "success".equalsIgnoreCase(responseBody.getStatus())){
                    UserPrivileges user = responseBody.getObj();
                    log.info("User is {}", user);
                    return user;
                }  else {
                    log.info("Didn't received success status from privilege api while statusCode is 200 {}", responseBody);
                }
            } else {
                log.info("Didn't received success status from privilege api {}", response);
            }
            return null;
        } catch (HttpClientErrorException e) {
            log.error("Error occurred while checking user privilege");
            return null;
        }
    }

}
