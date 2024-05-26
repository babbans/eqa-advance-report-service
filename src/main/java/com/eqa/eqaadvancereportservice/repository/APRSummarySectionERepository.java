package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APRSummarySectionERepository extends JpaRepository<APRSummarySectionE, Long> {
    List<APRSummarySectionE> findByReportId(String reportId);

    void deleteByReportId(String reportId);
}
