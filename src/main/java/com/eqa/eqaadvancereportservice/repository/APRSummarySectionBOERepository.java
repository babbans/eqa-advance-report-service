package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionBOE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRSummarySectionBOERepository extends JpaRepository<APRSummarySectionBOE, Long> {
    APRSummarySectionBOE findByReportId(String reportId);
}
