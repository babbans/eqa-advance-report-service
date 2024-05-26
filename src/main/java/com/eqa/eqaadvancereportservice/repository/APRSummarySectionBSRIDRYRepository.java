package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionBSRIDRY;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRSummarySectionBSRIDRYRepository extends JpaRepository<APRSummarySectionBSRIDRY, Long> {
    APRSummarySectionBSRIDRY findByReportId(String reportId);

    void deleteByReportId(String reportId);
}
