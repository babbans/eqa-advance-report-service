package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionBSEPQ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APRSummarySectionBSEPQRepository extends JpaRepository<APRSummarySectionBSEPQ, Long> {
    List<APRSummarySectionBSEPQ> findByReportId(String reportId);

    void deleteByReportId(String reportId);
}
