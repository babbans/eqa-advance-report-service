package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRSummarySectionARepository extends JpaRepository<APRSummarySectionA, Long> {
    APRSummarySectionA findByReportId(String reportId);
}
