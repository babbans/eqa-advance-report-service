package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionCKPIPE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APRSummarySectionCKPIPERepository extends JpaRepository<APRSummarySectionCKPIPE, Long> {
    List<APRSummarySectionCKPIPE> findByReportId(String reportId);

    void deleteByReportId(String reportId);
}
