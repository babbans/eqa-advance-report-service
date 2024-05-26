package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionC_KPI_MASTER;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRSummarySectionCKPIMasterRepository extends JpaRepository<APRSummarySectionC_KPI_MASTER, Long> {
    void deleteByReportId(String reportId);
}
