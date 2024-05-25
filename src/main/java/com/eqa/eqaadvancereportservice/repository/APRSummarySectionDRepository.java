package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRSummarySectionDRepository extends JpaRepository<APRSummarySectionD, Long> {
    APRSummarySectionD findByReportId(String reportId);
}
