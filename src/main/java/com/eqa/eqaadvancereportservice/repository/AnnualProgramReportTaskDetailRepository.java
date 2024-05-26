package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.AnnualProgramReportTaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AnnualProgramReportTaskDetailRepository extends JpaRepository<AnnualProgramReportTaskDetail, Long> {
    @Query("delete from #{#entityName} e where e.id in(?1)")
    @Modifying
    @Transactional
    public void deleteWithIds(List<Long> ids);

    List<AnnualProgramReportTaskDetail> findByReportMaster_ReportId(String reportId);
}
