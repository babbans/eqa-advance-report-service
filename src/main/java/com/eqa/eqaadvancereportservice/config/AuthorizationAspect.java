package com.eqa.eqaadvancereportservice.config;

import com.eqa.eqaadvancereportservice.exception.UnauthorizedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    private final IdentityService identityService;

    public AuthorizationAspect(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Pointcut("execution(* com.eqa.eqaaprsummaryservice.repository.*.save(..)) || " +
              "execution(* com.eqa.eqaaprsummaryservice.repository.*.delete(..)) || " +
              "execution(* com.eqa.eqaaprsummaryservice.repository.*.softDelete(..)) || " +
              "execution(* com.eqa.eqaaprsummaryservice.repository.*.update(..))")
    public void repositoryMethods() {}

    @Before("repositoryMethods()")
    public void checkAuthorization() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!identityService.isUserAuthorized(username)) {
            throw new UnauthorizedException("You are unAuthorized to access this endpoint.");
        }
    }
}
