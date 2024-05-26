package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSectionF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRSectionFRepository extends JpaRepository<APRSectionF, Long> {
    APRSectionF findByReportId(String reportId);

    void deleteByReportId(String reportId);
}
