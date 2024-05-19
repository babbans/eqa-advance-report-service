package com.eqa.eqaaprsummaryservice.repository;

import com.eqa.eqaaprsummaryservice.entity.AnnualProgramReportTaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualProgramReportTaskDetailRepository extends JpaRepository<AnnualProgramReportTaskDetail, Long> {
}
