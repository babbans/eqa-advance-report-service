package com.eqa.eqaadvancereportservice.repository;

import com.eqa.eqaadvancereportservice.entity.ReportMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportMasterRepository extends JpaRepository<ReportMaster, String> {
    Optional<ReportMaster> findByCollegeIdAndDepartmentIdAndProgramIdAndAcademicYear(
            String collegeId, String departmentId, String programId, Integer academicYear);
}
