package com.eqa.eqaaprsummaryservice.controller;

import com.eqa.eqaaprsummaryservice.constants.CommonConstants;
import com.eqa.eqaaprsummaryservice.entity.AnnualProgramReportTaskDetail;
import com.eqa.eqaaprsummaryservice.service.AnnualProgramReportTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommonConstants.API_BASE_PATH + CommonConstants.TASK_DETAIL)
public class AnnualProgramReportTaskDetailController {

    @Autowired
    private AnnualProgramReportTaskDetailService service;

    @GetMapping
    public List<AnnualProgramReportTaskDetail> getAllTaskDetails() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnualProgramReportTaskDetail> getTaskDetailById(@PathVariable Long id) {
        return service.findById(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AnnualProgramReportTaskDetail createTaskDetail(@RequestBody AnnualProgramReportTaskDetail taskDetail) {
        return service.save(taskDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnualProgramReportTaskDetail> updateTaskDetail(@PathVariable Long id, @RequestBody AnnualProgramReportTaskDetail taskDetail) {
        return service.findById(id)
                      .map(existingTaskDetail -> {
                          taskDetail.setId(existingTaskDetail.getId());
                          return ResponseEntity.ok(service.save(taskDetail));
                      })
                      .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskDetail(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
