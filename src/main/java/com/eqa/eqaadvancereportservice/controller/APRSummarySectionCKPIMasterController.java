package com.eqa.eqaadvancereportservice.controller;

import com.eqa.eqaadvancereportservice.constants.CommonConstants;
import com.eqa.eqaadvancereportservice.dto.ResponseObject;
import com.eqa.eqaadvancereportservice.entity.APRSummarySectionC_KPI_MASTER;
import com.eqa.eqaadvancereportservice.exception.CustomException;
import com.eqa.eqaadvancereportservice.service.APRSummarySectionCKPIMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommonConstants.API_BASE_PATH + CommonConstants.KPI)
@Validated
@Slf4j
public class APRSummarySectionCKPIMasterController {

    @Autowired
    private APRSummarySectionCKPIMasterService kpiService;

    @GetMapping
    public ResponseEntity<ResponseObject> getAllKpi() {
        log.info("getAllKpi() : Start");
        return kpiService.findAll();
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createKpi(@Validated @RequestBody List<APRSummarySectionC_KPI_MASTER> data) {
        log.info("createKpi() : Start");
        log.info("Kpi data {}", data);
        return kpiService.save(data);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateKpi(@Validated @RequestBody APRSummarySectionC_KPI_MASTER data,
                                                        @PathVariable("id") long id) throws CustomException {
        log.info("updateKpi() : Start");
        log.info("Kpi id {} and Data {}", id, data);
        return kpiService.updateKpi(data, id);
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> deleteKpi(@RequestParam List<Long> ids)
            throws CustomException {
        log.info("deleteKpi() : Start, ids are {}", ids);
        return kpiService.deleteKpi(ids);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getKpiById(@PathVariable Long id) throws CustomException{
        log.info("getKpiById() : Start, id is {}", id);
        return kpiService.findById(id);
    }

    @GetMapping("/degree/{degree}")
    public ResponseEntity<ResponseObject> getKpisByDegree(@PathVariable String degree) {
        log.info("getKpisByDegree() : Start, degree is {}", degree);
        return kpiService.getKpisByDegree(degree);
    }
}
