package com.eqa.eqaadvancereportservice.service;

import com.eqa.eqaadvancereportservice.constants.AnnualProgramReportDataConstant;
import com.eqa.eqaadvancereportservice.dto.*;
import com.eqa.eqaadvancereportservice.entity.*;
import com.eqa.eqaadvancereportservice.exception.CustomException;
import com.eqa.eqaadvancereportservice.exception.UnauthorizedException;
import com.eqa.eqaadvancereportservice.repository.*;
import com.eqa.eqaadvancereportservice.util.CommonUtils;
import com.eqa.eqaadvancereportservice.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AnnualProgramReportDataService {

    @Autowired
    private APRSummarySectionARepository sectionARepository;

    @Autowired
    private APRSummarySectionBSECRepository sectionBSECRepository;

    @Autowired
    private APRSummarySectionBSEPQRepository sectionBSEPQRepository;

    @Autowired
    private APRSummarySectionBSRIDRYRepository sectionBSRIDRYRepository;

    @Autowired
    private APRSummarySectionBCPRepository sectionBCPRepository;

    @Autowired
    private APRSummarySectionBOERepository sectionBOERepository;

    @Autowired
    private APRSummarySectionCKPIMasterRepository sectionCKPIMasterRepository;

    @Autowired
    private APRSummarySectionCKPIPERepository sectionCKPIPERepository;

    @Autowired
    private APRSummarySectionDRepository sectionDRepository;

    @Autowired
    private APRSummarySectionERepository sectionERepository;

    @Autowired
    private APRSectionFRepository sectionFRepository;

    @Autowired
    private APRSummarySectionBRepository sectionBRepository;

    @Transactional
    public ResponseEntity<ResponseObject> saveOrUpdateReport(ReportDTO reportDTO) {
        try {
            log.info("Starting to save or update report with ID: {}", reportDTO.getReportId());

            if (reportDTO.getSectionA() != null) {
                log.info("Saving section A");
                APRSummarySectionA sectionA = new APRSummarySectionA();
                sectionA.setId(reportDTO.getSectionA().getId());
                sectionA.setReportId(reportDTO.getReportId());
                sectionA.setNsep(reportDTO.getSectionA().getNsep());
                sectionA.setNswsp(reportDTO.getSectionA().getNswsp());
                sectionA.setNswcp(reportDTO.getSectionA().getNswcp());
                sectionARepository.save(sectionA);
            }

            if (reportDTO.getSectionB() != null) {
                log.info("Saving section B");
                // Save or update the section B summary
                APRSummarySectionB sectionBSummary = new APRSummarySectionB();
                sectionBSummary.setId(reportDTO.getSectionB().getId());
                sectionBSummary.setReportId(reportDTO.getReportId());
                sectionBSummary.setStrength(reportDTO.getSectionB().getStrength());
                sectionBSummary.setChallenges(reportDTO.getSectionB().getChallenges());
                sectionBRepository.save(sectionBSummary);

                if (reportDTO.getSectionB().getStudentEvaluationOfCourse() != null) {
                    for (StudentEvaluationOfCourseDTO secDTO : reportDTO.getSectionB().getStudentEvaluationOfCourse()) {
                        log.info("Saving student evaluation of course for course code: {}", secDTO.getCourseCode());
                        APRSummarySectionBSEC sectionBSEC = new APRSummarySectionBSEC();
                        sectionBSEC.setId(secDTO.getId());
                        sectionBSEC.setReportId(reportDTO.getReportId());
                        sectionBSEC.setCourseCode(secDTO.getCourseCode());
                        sectionBSEC.setNswec(secDTO.getNoOfStudentEvaluatedCourse());
                        sectionBSEC.setPp(secDTO.getPercentageOfParticipant());
                        sectionBSEC.setEr(secDTO.getEvaluationResult());
                        sectionBSEC.setDr(secDTO.getRecommendation());
                        sectionBSECRepository.save(sectionBSEC);
                    }
                }

                if (reportDTO.getSectionB().getStudentEvaluationOfProgramQuality() != null) {
                    log.info("Saving student evaluation of program quality");
                    APRSummarySectionBSEPQ sectionBSEPQ = new APRSummarySectionBSEPQ();
                    sectionBSEPQ.setId(reportDTO.getSectionB().getStudentEvaluationOfProgramQuality().getId());
                    sectionBSEPQ.setReportId(reportDTO.getReportId());
                    sectionBSEPQ.setEvaluationDate(DateUtil.parseDate(reportDTO.getSectionB().getStudentEvaluationOfProgramQuality().getEvaluationDate()));
                    sectionBSEPQ.setNoOfParticipants(reportDTO.getSectionB().getStudentEvaluationOfProgramQuality().getNoOfParticipants());
                    sectionBSEPQ.setStudentFeedback(reportDTO.getSectionB().getStudentEvaluationOfProgramQuality().getStudentFeedback());
                    sectionBSEPQ.setProgramResponse(reportDTO.getSectionB().getStudentEvaluationOfProgramQuality().getProgramResponse());
                    sectionBSEPQRepository.save(sectionBSEPQ);
                }

                if (reportDTO.getSectionB().getScientificResearch() != null) {
                    log.info("Saving scientific research");
                    APRSummarySectionBSRIDRY sectionBSRIDRY = new APRSummarySectionBSRIDRY();
                    sectionBSRIDRY.setId(reportDTO.getSectionB().getScientificResearch().getId());
                    sectionBSRIDRY.setReportId(reportDTO.getReportId());
                    sectionBSRIDRY.setPsr(reportDTO.getSectionB().getScientificResearch().getPublishedScientificResearch());
                    sectionBSRIDRY.setCrp(reportDTO.getSectionB().getScientificResearch().getCurrentResearchProjects());
                    sectionBSRIDRY.setCop(reportDTO.getSectionB().getScientificResearch().getConferencesOrganizedByProgram());
                    sectionBSRIDRY.setShp(reportDTO.getSectionB().getScientificResearch().getSeminarsHeldByProgram());
                    sectionBSRIDRY.setCa(reportDTO.getSectionB().getScientificResearch().getConferencesAttendees());
                    sectionBSRIDRY.setSa(reportDTO.getSectionB().getScientificResearch().getSeminarsAttendees());
                    sectionBSRIDRY.setDiscussion(reportDTO.getSectionB().getScientificResearch().getDiscussion());
                    sectionBSRIDRYRepository.save(sectionBSRIDRY);
                }

                if (reportDTO.getSectionB().getCommunityPartnership() != null) {
                    log.info("Saving community partnership");
                    boolean firstRecord = true;
                    for (CommunityPartnershipDTO cpDTO : reportDTO.getSectionB().getCommunityPartnership()) {
                        APRSummarySectionBCP sectionBCP = new APRSummarySectionBCP();
                        sectionBCP.setId(cpDTO.getId());
                        sectionBCP.setReportId(reportDTO.getReportId());
                        sectionBCP.setActivityImplemented(cpDTO.getActivity());
                        sectionBCP.setDescription(cpDTO.getDescription());
                        if (firstRecord) {
                            sectionBCP.setComments(cpDTO.getComment());
                            firstRecord = false;
                        }
                        sectionBCPRepository.save(sectionBCP);
                    }
                }

                if (reportDTO.getSectionB().getOtherEvaluation() != null) {
                    log.info("Saving other evaluation");
                    APRSummarySectionBOE sectionBOE = new APRSummarySectionBOE();
                    sectionBOE.setReportId(reportDTO.getReportId());
                    sectionBOE.setId(reportDTO.getSectionB().getOtherEvaluation().getId());
                    sectionBOE.setEvaluationMethod(reportDTO.getSectionB().getOtherEvaluation().getEvaluationMethod());
                    sectionBOE.setDate(DateUtil.parseDate(reportDTO.getSectionB().getOtherEvaluation().getDate()));
                    sectionBOE.setNoOfParticipants(reportDTO.getSectionB().getOtherEvaluation().getNoOfParticipants());
                    sectionBOE.setSer(reportDTO.getSectionB().getOtherEvaluation().getSummaryOfEvaluatorReview());
                    sectionBOE.setProgramResponse(reportDTO.getSectionB().getOtherEvaluation().getProgramResponse());
                    sectionBOERepository.save(sectionBOE);
                }
            }

            if (reportDTO.getSectionC() != null) {
                log.info("Saving section C");
                for (SectionCDTO sectionCDTO : reportDTO.getSectionC()) {
                    APRSummarySectionCKPIPE sectionCKPIPE = new APRSummarySectionCKPIPE();
                    sectionCKPIPE.setId(sectionCDTO.getId());
                    sectionCKPIPE.setReportId(reportDTO.getReportId());
                    sectionCKPIPE.setKpiId(sectionCDTO.getKpiId());
                    sectionCKPIPE.setActualValue(sectionCDTO.getActualValue());
                    sectionCKPIPE.setInternalBenchmark(sectionCDTO.getInternalBenchmark());
                    sectionCKPIPE.setAnalysis(sectionCDTO.getAnalysis());
                    sectionCKPIPE.setNewTarget(sectionCDTO.getNewTarget());
                    sectionCKPIPE.setComments(sectionCDTO.getComments());
                    sectionCKPIPERepository.save(sectionCKPIPE);
                }
            }

            if (reportDTO.getSectionD() != null) {
                log.info("Saving section D");
                APRSummarySectionD sectionD = new APRSummarySectionD();
                sectionD.setId(reportDTO.getSectionD().getId());
                sectionD.setReportId(reportDTO.getReportId());
                sectionD.setTeaching(reportDTO.getSectionD().getTeaching());
                sectionD.setAssessment(reportDTO.getSectionD().getAssessment());
                sectionD.setGuidanceCounseling(reportDTO.getSectionD().getGuidanceCounseling());
                sectionD.setLearningResources(reportDTO.getSectionD().getLearningResources());
                sectionD.setFaculty(reportDTO.getSectionD().getFaculty());
                sectionD.setResearchActivities(reportDTO.getSectionD().getResearchActivities());
                sectionDRepository.save(sectionD);
            }

            if (reportDTO.getSectionE() != null) {
                log.info("Saving section E");
                for (SectionEDTO sectionEDTO : reportDTO.getSectionE()) {
                    APRSummarySectionE sectionE = new APRSummarySectionE();
                    sectionE.setId(sectionEDTO.getId());
                    sectionE.setReportId(reportDTO.getReportId());
                    sectionE.setDpId(sectionEDTO.getDevelopmentPlanId());
                    sectionE.setPi(sectionEDTO.getPriorityOfImprovement());
                    sectionE.setAction(sectionEDTO.getAction());
                    sectionE.setActionResponsibility(sectionEDTO.getActionResponsibility());
                    sectionERepository.save(sectionE);
                }
            }

            if (reportDTO.getSectionF() != null) {
                log.info("Saving section F");
                APRSectionF sectionF = new APRSectionF();
                sectionF.setId(reportDTO.getSectionF().getId());
                sectionF.setReportId(reportDTO.getReportId());
                sectionF.setCouncilCommittee(reportDTO.getSectionF().getCouncilCommitteeId());
                sectionF.setReferenceNo(reportDTO.getSectionF().getReferenceNo());
                sectionF.setApprovalDate(DateUtil.parseDate(reportDTO.getSectionF().getApprovalDate()));
                byte[] departmentStamp = Base64.getDecoder().decode(reportDTO.getSectionF().getDepartmentStamp());
                sectionF.setDepartmentStamp(departmentStamp);
                sectionFRepository.save(sectionF);
            }
            log.info("Completed saving or updating report data with ID: {}", reportDTO.getReportId());
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportDataConstant.APR_DATA_CREATE_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportDataConstant.APR_DATA_CREATE_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), null,
                    String.valueOf(AnnualProgramReportDataConstant.APR_DATA_CREATE_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportDataConstant.APR_DATA_CREATE_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            if(ex instanceof UnauthorizedException){
                throw new CustomException(AnnualProgramReportDataConstant.APR_DATA_UNAUTHORIZED_ACCESS);
            }
            log.error("Error while saving or updating report data {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportDataConstant.APR_DATA_CREATION_FAILED);
        }
    }
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseObject> getReportById(String reportId) throws CustomException {
        try {
            log.info("Fetching report with ID: {}", reportId);
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setReportId(reportId);

            APRSummarySectionA sectionA = sectionARepository.findByReportId(reportId);
            if (sectionA != null) {
                log.info("Found section A for report ID: {}", reportId);
                SectionADTO sectionADTO = new SectionADTO();
                sectionADTO.setId(sectionA.getId());
                sectionADTO.setNsep(sectionA.getNsep());
                sectionADTO.setNswsp(sectionA.getNswsp());
                sectionADTO.setNswcp(sectionA.getNswcp());
                reportDTO.setSectionA(sectionADTO);
            }

            APRSummarySectionB sectionBSummary = sectionBRepository.findByReportId(reportId);
            if (sectionBSummary != null) {
                SectionBDTO sectionBDTO = new SectionBDTO();
                sectionBDTO.setId(sectionBSummary.getId());
                sectionBDTO.setStrength(sectionBSummary.getStrength());
                sectionBDTO.setChallenges(sectionBSummary.getChallenges());

                List<StudentEvaluationOfCourseDTO> secDTOs = sectionBSECRepository.findByReportId(reportId).stream().map(sec -> {
                    StudentEvaluationOfCourseDTO secDTO = new StudentEvaluationOfCourseDTO();
                    secDTO.setId(sec.getId());
                    secDTO.setCourseCode(sec.getCourseCode());
                    secDTO.setNoOfStudentEvaluatedCourse(sec.getNswec());
                    secDTO.setPercentageOfParticipant(sec.getPp());
                    secDTO.setEvaluationResult(sec.getEr());
                    secDTO.setRecommendation(sec.getDr());
                    return secDTO;
                }).collect(Collectors.toList());
                sectionBDTO.setStudentEvaluationOfCourse(secDTOs);

                APRSummarySectionBSEPQ sectionBSEPQ = sectionBSEPQRepository.findByReportId(reportId);
                if (sectionBSEPQ != null) {
                    StudentEvaluationOfProgramQualityDTO sepqDTO = new StudentEvaluationOfProgramQualityDTO();
                    sepqDTO.setId(sectionBSEPQ.getId());
                    sepqDTO.setEvaluationDate(DateUtil.formatDate(sectionBSEPQ.getEvaluationDate()));
                    sepqDTO.setNoOfParticipants(sectionBSEPQ.getNoOfParticipants());
                    sepqDTO.setStudentFeedback(sectionBSEPQ.getStudentFeedback());
                    sepqDTO.setProgramResponse(sectionBSEPQ.getProgramResponse());
                    sectionBDTO.setStudentEvaluationOfProgramQuality(sepqDTO);
                }

                APRSummarySectionBSRIDRY sectionBSRIDRY = sectionBSRIDRYRepository.findByReportId(reportId);
                if (sectionBSRIDRY != null) {
                    ScientificResearchDTO srDTO = new ScientificResearchDTO();
                    srDTO.setId(sectionBSRIDRY.getId());
                    srDTO.setPublishedScientificResearch(sectionBSRIDRY.getPsr());
                    srDTO.setCurrentResearchProjects(sectionBSRIDRY.getCrp());
                    srDTO.setConferencesOrganizedByProgram(sectionBSRIDRY.getCop());
                    srDTO.setSeminarsHeldByProgram(sectionBSRIDRY.getShp());
                    srDTO.setConferencesAttendees(sectionBSRIDRY.getCa());
                    srDTO.setSeminarsAttendees(sectionBSRIDRY.getSa());
                    srDTO.setDiscussion(sectionBSRIDRY.getDiscussion());
                    sectionBDTO.setScientificResearch(srDTO);
                }

                List<CommunityPartnershipDTO> cpDTOs = sectionBCPRepository.findByReportId(reportId).stream().map(cp -> {
                    CommunityPartnershipDTO cpDTO = new CommunityPartnershipDTO();
                    cpDTO.setId(cp.getId());
                    cpDTO.setActivity(cp.getActivityImplemented());
                    cpDTO.setDescription(cp.getDescription());
                    cpDTO.setComment(cp.getComments());
                    return cpDTO;
                }).collect(Collectors.toList());
                sectionBDTO.setCommunityPartnership(cpDTOs);

                APRSummarySectionBOE sectionBOE = sectionBOERepository.findByReportId(reportId);
                if (sectionBOE != null) {
                    OtherEvaluationDTO oeDTO = new OtherEvaluationDTO();
                    oeDTO.setId(sectionBOE.getId());
                    oeDTO.setEvaluationMethod(sectionBOE.getEvaluationMethod());
                    oeDTO.setDate(DateUtil.formatDate(sectionBOE.getDate()));
                    oeDTO.setNoOfParticipants(sectionBOE.getNoOfParticipants());
                    oeDTO.setSummaryOfEvaluatorReview(sectionBOE.getSer());
                    oeDTO.setProgramResponse(sectionBOE.getProgramResponse());
                    sectionBDTO.setOtherEvaluation(oeDTO);
                }

                reportDTO.setSectionB(sectionBDTO);
            }

            List<SectionCDTO> sectionCDTOs = sectionCKPIPERepository.findByReportId(reportId).stream().map(sectionCKPIPE -> {
                SectionCDTO sectionCDTO = new SectionCDTO();
                sectionCDTO.setId(sectionCKPIPE.getId());
                sectionCDTO.setKpiId(sectionCKPIPE.getKpiId());
                sectionCDTO.setActualValue(sectionCKPIPE.getActualValue());
                sectionCDTO.setInternalBenchmark(sectionCKPIPE.getInternalBenchmark());
                sectionCDTO.setAnalysis(sectionCKPIPE.getAnalysis());
                sectionCDTO.setNewTarget(sectionCKPIPE.getNewTarget());
                sectionCDTO.setComments(sectionCKPIPE.getComments());
                return sectionCDTO;
            }).collect(Collectors.toList());
            reportDTO.setSectionC(sectionCDTOs);

            APRSummarySectionD sectionD = sectionDRepository.findByReportId(reportId);
            if (sectionD != null) {
                SectionDDTO sectionDDTO = new SectionDDTO();
                sectionDDTO.setId(sectionD.getId());
                sectionDDTO.setTeaching(sectionD.getTeaching());
                sectionDDTO.setAssessment(sectionD.getAssessment());
                sectionDDTO.setGuidanceCounseling(sectionD.getGuidanceCounseling());
                sectionDDTO.setLearningResources(sectionD.getLearningResources());
                sectionDDTO.setFaculty(sectionD.getFaculty());
                sectionDDTO.setResearchActivities(sectionD.getResearchActivities());
                reportDTO.setSectionD(sectionDDTO);
            }

            List<SectionEDTO> sectionEDTOs = sectionERepository.findByReportId(reportId).stream().map(sectionE -> {
                SectionEDTO sectionEDTO = new SectionEDTO();
                sectionEDTO.setId(sectionE.getId());
                sectionEDTO.setDevelopmentPlanId(sectionE.getDpId());
                sectionEDTO.setPriorityOfImprovement(sectionE.getPi());
                sectionEDTO.setAction(sectionE.getAction());
                sectionEDTO.setActionResponsibility(sectionE.getActionResponsibility());
                return sectionEDTO;
            }).collect(Collectors.toList());
            reportDTO.setSectionE(sectionEDTOs);

            APRSectionF sectionF = sectionFRepository.findByReportId(reportId);
            if (sectionF != null) {
                SectionFDTO sectionFDTO = new SectionFDTO();
                sectionFDTO.setId(sectionF.getId());
                sectionFDTO.setCouncilCommitteeId(sectionF.getCouncilCommittee());
                sectionFDTO.setReferenceNo(sectionF.getReferenceNo());
                sectionFDTO.setApprovalDate(DateUtil.formatDate(sectionF.getApprovalDate()));
                sectionFDTO.setDepartmentStamp(Base64.getEncoder().encodeToString(sectionF.getDepartmentStamp()).getBytes());
                reportDTO.setSectionF(sectionFDTO);
            }

            log.info("Completed fetching report with ID: {}", reportId);
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportDataConstant.APR_DATA_GET_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportDataConstant.APR_DATA_GET_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), reportDTO,
                    String.valueOf(AnnualProgramReportDataConstant.APR_DATA_GET_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportDataConstant.APR_DATA_GET_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            log.error("Error while fetching Annual Program Report Data {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportDataConstant.APR_DATA_GET_FAILED);
        }
    }
    @Transactional
    public ResponseEntity<ResponseObject> deleteReportById(String reportId) {
        try {
            log.info("Deleting report with ID: {}", reportId);

            sectionARepository.deleteByReportId(reportId);
            sectionBSECRepository.deleteByReportId(reportId);
            sectionBSEPQRepository.deleteByReportId(reportId);
            sectionBSRIDRYRepository.deleteByReportId(reportId);
            sectionBCPRepository.deleteByReportId(reportId);
            sectionBOERepository.deleteByReportId(reportId);
            sectionCKPIMasterRepository.deleteByReportId(reportId);
            sectionCKPIPERepository.deleteByReportId(reportId);
            sectionDRepository.deleteByReportId(reportId);
            sectionERepository.deleteByReportId(reportId);
            sectionFRepository.deleteByReportId(reportId);
            sectionBRepository.deleteByReportId(reportId);

            log.info("Completed deleting report data with ID: {}", reportId);
            return CommonUtils.buildResponseEntity(Arrays.asList(AnnualProgramReportDataConstant.APR_DATA_DELETE_SUCCESS.getBusinessMsg()),
                    AnnualProgramReportDataConstant.APR_DATA_DELETE_SUCCESS.getHttpStatus().getReasonPhrase(),
                    String.valueOf(Math.round(Math.random() * 100)), null,
                    String.valueOf(AnnualProgramReportDataConstant.APR_DATA_DELETE_SUCCESS.getHttpStatus().value()), null,
                    new HttpHeaders(), AnnualProgramReportDataConstant.APR_DATA_DELETE_SUCCESS.getHttpStatus());
        } catch (Exception ex) {
            log.error("Error while deleting report data {}", ex.getMessage());
            throw new CustomException(AnnualProgramReportDataConstant.APR_DATA_DELETION_FAILED);
        }
    }

}
