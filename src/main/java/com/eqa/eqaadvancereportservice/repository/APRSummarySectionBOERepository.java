package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.dto.OtherEvaluationDTO;
import com.eqa.eqaadvancereportservice.entity.APRSummarySectionBOE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APRSummarySectionBOERepository extends JpaRepository<APRSummarySectionBOE, Long> {
    List<APRSummarySectionBOE> findByReportId(String reportId);

    void deleteByReportId(String reportId);
}
