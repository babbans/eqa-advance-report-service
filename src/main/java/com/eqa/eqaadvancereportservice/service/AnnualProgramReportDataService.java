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

    @Transactional
    public ResponseEntity<ResponseObject> saveOrUpdateReport(ReportDTO reportDTO) {
        try {
            log.info("Starting to save or update report with ID: {}", reportDTO.getReportId());

            if (reportDTO.getSectionA() != null) {
                log.info("Saving section A");
                APRSummarySectionA sectionA = new APRSummarySectionA();
                sectionA.setReportId(reportDTO.getReportId());
                sectionA.setNsep(reportDTO.getSectionA().getNsep());
                sectionA.setNswsp(reportDTO.getSectionA().getNswsp());
                sectionA.setNswcp(reportDTO.getSectionA().getNswcp());
                sectionARepository.save(sectionA);
            }

            if (reportDTO.getSectionB() != null) {
                log.info("Saving section B");

                if (reportDTO.getSectionB().getStudentEvaluationOfCourse() != null) {
                    for (StudentEvaluationOfCourseDTO secDTO : reportDTO.getSectionB().getStudentEvaluationOfCourse()) {
                        log.info("Saving student evaluation of course for course code: {}", secDTO.getCourseCode());
                        APRSummarySectionBSEC sectionBSEC = new APRSummarySectionBSEC();
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
    public ResponseEntity<ResponseObject> getReportById(String reportId) throws CustomException {
        try {
            log.info("Fetching report with ID: {}", reportId);
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setReportId(reportId);

            // Fetch section A
            APRSummarySectionA sectionA = sectionARepository.findByReportId(reportId);
            if (sectionA != null) {
                log.info("Found section A for report ID: {}", reportId);
                SectionADTO sectionADTO = new SectionADTO();
                sectionADTO.setNsep(sectionA.getNsep());
                sectionADTO.setNswsp(sectionA.getNswsp());
                sectionADTO.setNswcp(sectionA.getNswcp());
                reportDTO.setSectionA(sectionADTO);
            }

            // Fetch section B - Student Evaluation of Course
            SectionBDTO sectionBDTO = new SectionBDTO();

            List<APRSummarySectionBSEC> sectionBSECList = sectionBSECRepository.findByReportId(reportId);
            if (!sectionBSECList.isEmpty()) {
                List<StudentEvaluationOfCourseDTO> secDTOList = new ArrayList<>();
                for (APRSummarySectionBSEC sectionBSEC : sectionBSECList) {
                    StudentEvaluationOfCourseDTO secDTO = new StudentEvaluationOfCourseDTO();
                    secDTO.setCourseCode(sectionBSEC.getCourseCode());
                    secDTO.setNoOfStudentEvaluatedCourse(sectionBSEC.getNswec());
                    secDTO.setPercentageOfParticipant(sectionBSEC.getPp());
                    secDTO.setEvaluationResult(sectionBSEC.getEr());
                    secDTO.setRecommendation(sectionBSEC.getDr());
                    secDTOList.add(secDTO);
                }
                sectionBDTO.setStudentEvaluationOfCourse(secDTOList);
                log.info("Student Evaluation Of Course retrieved for report ID: {}", reportId);
            }

            APRSummarySectionBSEPQ sectionBSEPQ = sectionBSEPQRepository.findByReportId(reportId);
            if (sectionBSEPQ != null) {
                StudentEvaluationOfProgramQualityDTO sepqDTO = new StudentEvaluationOfProgramQualityDTO();
                sepqDTO.setEvaluationDate(DateUtil.formatDate(sectionBSEPQ.getEvaluationDate()));
                sepqDTO.setNoOfParticipants(sectionBSEPQ.getNoOfParticipants());
                sepqDTO.setStudentFeedback(sectionBSEPQ.getStudentFeedback());
                sepqDTO.setProgramResponse(sectionBSEPQ.getProgramResponse());
                sectionBDTO.setStudentEvaluationOfProgramQuality(sepqDTO);
                log.info("Student Evaluation Of Program Quality retrieved for report ID: {}", reportId);
            }

            // Fetch section B - Scientific Research
            APRSummarySectionBSRIDRY sectionBSRIDRY = sectionBSRIDRYRepository.findByReportId(reportId);
            if (sectionBSRIDRY != null) {
                log.info("Found scientific research for report ID: {}", reportId);
                if (reportDTO.getSectionB() == null) {
                    reportDTO.setSectionB(new SectionBDTO());
                }
                ScientificResearchDTO srDTO = new ScientificResearchDTO();
                srDTO.setPublishedScientificResearch(sectionBSRIDRY.getPsr());
                srDTO.setCurrentResearchProjects(sectionBSRIDRY.getCrp());
                srDTO.setConferencesOrganizedByProgram(sectionBSRIDRY.getCop());
                srDTO.setSeminarsHeldByProgram(sectionBSRIDRY.getShp());
                srDTO.setConferencesAttendees(sectionBSRIDRY.getCa());
                srDTO.setSeminarsAttendees(sectionBSRIDRY.getSa());
                srDTO.setDiscussion(sectionBSRIDRY.getDiscussion());
                sectionBDTO.setScientificResearch(srDTO);
            }

            // Fetch section B - Community Partnership
            List<APRSummarySectionBCP> sectionBCPs = sectionBCPRepository.findByReportId(reportId);
            if (sectionBCPs != null && !sectionBCPs.isEmpty()) {
                log.info("Found community partnership activities for report ID: {}", reportId);
                if (reportDTO.getSectionB() == null) {
                    reportDTO.setSectionB(new SectionBDTO());
                }
                for (APRSummarySectionBCP sectionBCP : sectionBCPs) {
                    CommunityPartnershipDTO cpDTO = new CommunityPartnershipDTO();
                    cpDTO.setActivity(sectionBCP.getActivityImplemented());
                    cpDTO.setDescription(sectionBCP.getDescription());
                    cpDTO.setComment(sectionBCP.getComments());
                    sectionBDTO.getCommunityPartnership().add(cpDTO);
                }
            }

            // Fetch section B - Other Evaluation
            APRSummarySectionBOE sectionBOE = sectionBOERepository.findByReportId(reportId);
            if (sectionBOE != null) {
                log.info("Found other evaluation for report ID: {}", reportId);
                if (reportDTO.getSectionB() == null) {
                    reportDTO.setSectionB(new SectionBDTO());
                }
                OtherEvaluationDTO oeDTO = new OtherEvaluationDTO();
                oeDTO.setEvaluationMethod(sectionBOE.getEvaluationMethod());
                oeDTO.setDate(DateUtil.formatDate(sectionBOE.getDate()));
                oeDTO.setNoOfParticipants(sectionBOE.getNoOfParticipants());
                oeDTO.setSummaryOfEvaluatorReview(sectionBOE.getSer());
                oeDTO.setProgramResponse(sectionBOE.getProgramResponse());
                sectionBDTO.setOtherEvaluation(oeDTO);
            }
            // Set Section B to the report DTO if it has any data
            if (sectionBDTO.getStudentEvaluationOfCourse() != null || sectionBDTO.getStudentEvaluationOfProgramQuality() != null
                    || sectionBDTO.getScientificResearch() != null || sectionBDTO.getCommunityPartnership() != null
                    || sectionBDTO.getOtherEvaluation() != null) {
                reportDTO.setSectionB(sectionBDTO);
            }
            // Fetch section C
            List<APRSummarySectionCKPIPE> sectionCKPIPEs = sectionCKPIPERepository.findByReportId(reportId);
            if (sectionCKPIPEs != null && !sectionCKPIPEs.isEmpty()) {
                log.info("Found section C KPI performance evaluation for report ID: {}", reportId);
                for (APRSummarySectionCKPIPE sectionCKPIPE : sectionCKPIPEs) {
                    SectionCDTO sectionCDTO = new SectionCDTO();
                    sectionCDTO.setKpiId(sectionCKPIPE.getKpiId());
                    sectionCDTO.setActualValue(sectionCKPIPE.getActualValue());
                    sectionCDTO.setInternalBenchmark(sectionCKPIPE.getInternalBenchmark());
                    sectionCDTO.setAnalysis(sectionCKPIPE.getAnalysis());
                    sectionCDTO.setNewTarget(sectionCKPIPE.getNewTarget());
                    sectionCDTO.setComments(sectionCKPIPE.getComments());
                    reportDTO.getSectionC().add(sectionCDTO);
                }
            }

            // Fetch section D
            APRSummarySectionD sectionD = sectionDRepository.findByReportId(reportId);
            if (sectionD != null) {
                log.info("Found section D for report ID: {}", reportId);
                SectionDDTO sectionDDTO = new SectionDDTO();
                sectionDDTO.setTeaching(sectionD.getTeaching());
                sectionDDTO.setAssessment(sectionD.getAssessment());
                sectionDDTO.setGuidanceCounseling(sectionD.getGuidanceCounseling());
                sectionDDTO.setLearningResources(sectionD.getLearningResources());
                sectionDDTO.setFaculty(sectionD.getFaculty());
                sectionDDTO.setResearchActivities(sectionD.getResearchActivities());
                reportDTO.setSectionD(sectionDDTO);
            }

            // Fetch section E
            List<APRSummarySectionE> sectionEs = sectionERepository.findByReportId(reportId);
            if (sectionEs != null && !sectionEs.isEmpty()) {
                log.info("Found section E program development plan for report ID: {}", reportId);
                for (APRSummarySectionE sectionE : sectionEs) {
                    SectionEDTO sectionEDTO = new SectionEDTO();
                    sectionEDTO.setDevelopmentPlanId(sectionE.getDpId());
                    sectionEDTO.setPriorityOfImprovement(sectionE.getPi());
                    sectionEDTO.setAction(sectionE.getAction());
                    sectionEDTO.setActionResponsibility(sectionE.getActionResponsibility());
                    reportDTO.getSectionE().add(sectionEDTO);
                }
            }

            // Fetch section F
            APRSectionF sectionF = sectionFRepository.findByReportId(reportId);
            if (sectionF != null) {
                log.info("Found section F for report ID: {}", reportId);
                SectionFDTO sectionFDTO = new SectionFDTO();
                sectionFDTO.setCouncilCommitteeId(sectionF.getCouncilCommittee());
                sectionFDTO.setReferenceNo(sectionF.getReferenceNo());
                sectionFDTO.setApprovalDate(DateUtil.formatDate(sectionF.getApprovalDate()));
                sectionFDTO.setDepartmentStamp(sectionF.getDepartmentStamp());
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
            throw new CustomException(AnnualProgramReportDataConstant.APR_DATA_NOT_FOUND);
        }
    }
}
