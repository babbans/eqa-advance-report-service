package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionBCP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APRSummarySectionBCPRepository extends JpaRepository<APRSummarySectionBCP, Long> {
    List<APRSummarySectionBCP> findByReportId(String reportId);
    // Custom query methods can be defined here if needed
}
