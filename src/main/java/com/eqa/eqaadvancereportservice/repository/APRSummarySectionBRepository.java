package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRSummarySectionBRepository extends JpaRepository<APRSummarySectionB, Long> {
    APRSummarySectionB findByReportId(String reportId);

    void deleteByReportId(String reportId);
}
