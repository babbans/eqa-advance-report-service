package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.APRSummarySectionC_KPI_MASTER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface APRSummarySectionCKPIMasterRepository extends JpaRepository<APRSummarySectionC_KPI_MASTER, Long> {
    @Query("delete from #{#entityName} e where e.id in(?1)")
    @Modifying
    @Transactional
    public void deleteWithIds(List<Long> ids);

    void deleteByReportId(String reportId);
    List<APRSummarySectionC_KPI_MASTER> findByDegree(String degree);
}
