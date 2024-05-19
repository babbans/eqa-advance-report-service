package com.eqa.eqaaprsummaryservice.service;

import com.eqa.eqaaprsummaryservice.entity.AnnualProgramReportTaskDetail;
import com.eqa.eqaaprsummaryservice.repository.AnnualProgramReportTaskDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnualProgramReportTaskDetailService {

    @Autowired
    private AnnualProgramReportTaskDetailRepository taskDetailRepository;

    public List<AnnualProgramReportTaskDetail> findAll() {
        return taskDetailRepository.findAll();
    }

    public Optional<AnnualProgramReportTaskDetail> findById(Long id) {
        return taskDetailRepository.findById(id);
    }

    public AnnualProgramReportTaskDetail save(AnnualProgramReportTaskDetail taskDetail) {
        return taskDetailRepository.save(taskDetail);
    }

    public void deleteById(Long id) {
        taskDetailRepository.deleteById(id);
    }
}
