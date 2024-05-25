package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionBSEPQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRSummarySectionBSEPQRepository extends JpaRepository<APRSummarySectionBSEPQ, Long> {
    APRSummarySectionBSEPQ findByReportId(String reportId);
}
