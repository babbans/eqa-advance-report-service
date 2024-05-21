package com.eqa.eqaadvancereportservice.config;

import com.eqa.eqaadvancereportservice.dto.UserPrivileges;
import com.eqa.eqaadvancereportservice.exception.UnauthorizedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    private final IdentityService identityService;

    public AuthorizationAspect(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Pointcut("execution(* com.eqa.eqaadvancereportservice.repository.AnnualProgramReportSettingRepository.save(..)) || " +
            "execution(* com.eqa.eqaadvancereportservice.repository.AnnualProgramReportSettingRepository.saveAll(..)) || " +
            "execution(* com.eqa.eqaadvancereportservice.repository.AnnualProgramReportSettingRepository.deleteWithIds(..))")
    public void annualProgramReportSettingMethods() {}

    @Pointcut("execution(* com.eqa.eqaadvancereportservice.repository.AnnualProgramReportTaskDetailRepository.save(..)) || " +
            "execution(* com.eqa.eqaadvancereportservice.repository.AnnualProgramReportTaskDetailRepository.saveAll(..)) || " +
            "execution(* com.eqa.eqaadvancereportservice.repository.AnnualProgramReportTaskDetailRepository.deleteWithIds(..))")
    public void annualProgramReportTaskDetailMethods() {}
    @Before("annualProgramReportSettingMethods()")
    public void checkAuthorizationForSettings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            String username = ((User) authentication.getPrincipal()).getUsername();
            String apiKey = (String) authentication.getCredentials();
            UserPrivileges privileges = identityService.getUserPrivileges(username, apiKey);
            if (privileges == null || (!privileges.isSuperUser() && !privileges.isStaff())) {
                throw new UnauthorizedException("You are unauthorized to access this endpoint.");
            }
        } else {
            throw new UnauthorizedException("You are unauthorized to access this endpoint.");
        }

    }
    @Before("annualProgramReportTaskDetailMethods()")
    public void checkAuthorizationForTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            String username = ((User) authentication.getPrincipal()).getUsername();
            String apiKey = (String) authentication.getCredentials();

            UserPrivileges privileges = identityService.getUserPrivileges(username, apiKey);
            if (privileges == null || (!privileges.isDepartmentCoordinator() && !privileges.isSuperUser() && !privileges.isStaff())) {
                throw new UnauthorizedException("You are unauthorized to access this endpoint.");
            }
        } else {
            throw new UnauthorizedException("You are unauthorized to access this endpoint.");
        }

    }
}
