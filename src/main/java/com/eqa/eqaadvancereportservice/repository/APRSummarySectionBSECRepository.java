package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionBSEC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APRSummarySectionBSECRepository extends JpaRepository<APRSummarySectionBSEC, Long> {
    List<APRSummarySectionBSEC> findByReportId(String reportId);

    void deleteByReportId(String reportId);
}
