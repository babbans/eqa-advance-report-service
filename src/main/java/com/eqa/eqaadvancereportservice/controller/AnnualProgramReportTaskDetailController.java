package com.eqa.eqaadvancereportservice.controller;

import com.eqa.eqaadvancereportservice.constants.CommonConstants;
import com.eqa.eqaadvancereportservice.dto.AnnualProgramReportTaskDetailDTO;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
import com.eqa.eqaadvancereportservice.entity.AnnualProgramReportTaskDetail;
import com.eqa.eqaadvancereportservice.exception.CustomException;
import com.eqa.eqaadvancereportservice.service.AnnualProgramReportTaskDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommonConstants.API_BASE_PATH + CommonConstants.TASK_DETAIL)
@Slf4j
@Validated
public class AnnualProgramReportTaskDetailController {

    @Autowired
    private AnnualProgramReportTaskDetailService taskDetailService;
    
    @GetMapping
    public ResponseEntity<ResponseObject> getAllTaskDetails() {
        log.info("getAllTaskDetail() : Start");
        return taskDetailService.findAll();
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createTaskDetail(@Validated @RequestBody AnnualProgramReportTaskDetailDTO dto) {
        log.info("createTaskDetail() : Start");
        log.info("TaskDetail data {}", dto);
        return taskDetailService.save(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateTaskDetail(@Validated @RequestBody AnnualProgramReportTaskDetail taskDetail,
                                                        @PathVariable("id") long id) throws CustomException {
        log.info("updateTaskDetail() : Start");
        log.info("TaskDetail id {} and Data {}", id, taskDetail);
        return taskDetailService.updateTaskDetail(taskDetail, id);
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> deleteTaskDetail(@RequestParam List<Long> ids)
            throws CustomException {
        log.info("deleteTaskDetail() : Start, ids are {}", ids);
        return taskDetailService.deleteTaskDetail(ids);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getTaskDetailById(@PathVariable Long id) throws CustomException{
        log.info("getTaskDetailById() : Start, id is {}", id);
        return taskDetailService.findById(id);
    }
}
