package com.eqa.eqaaprsummaryservice.repository;

import com.eqa.eqaaprsummaryservice.entity.AnnualProgramReportSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AnnualProgramReportSettingRepository extends JpaRepository<AnnualProgramReportSetting, Long> {
    @Query("update #{#entityName} e set e.active=false where e.id in(?1)")
    @Modifying
    @Transactional
    public void softDelete(List<Long> ids);
}
