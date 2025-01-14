package org.example.resumemodifierbackend.service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.resumemodifierbackend.POJO.JobDescription;
import org.example.resumemodifierbackend.Utils.JsonToObj;
import org.example.resumemodifierbackend.Utils.WebClientHelper;
import org.example.resumemodifierbackend.model.ResumeData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class JobDescriptionServiceImpl {
    WebClientHelper webClientHelper;
    JsonToObj jsonToObj;
    public ResumeData handleJobDescription(JobDescription jobDescription) {
        //get JD
        String jd = jobDescription.getJobDescription();
        String criteriaPrompt = "PersonalInformation(100points)1.CompletenessandAccuracy(50points)Name:clearandaccurate,avoidnicknamesorirregularformatting(10points)Contactinformation:validandfrequentlyused(20points)Socialmedialinks:validandrelevanttotheposition(10points)Addressinformation:conciseandrelevanttotheposition(10points)2.Concisenessandstandardization(30points)Conciseinformation:avoidredundancy(e.g.unnecessarypersonaldetails)(15points)Professionalism:useaprofessionalemailaddress,providesocialmedialinkstoshowcaseskills(15points)3.Relevance(20points)Matchwiththeposition:whetherthecontentofpersonalinformationisrelevanttothepositionappliedfor(20points)WorkExperience(100points)1.Completenessandaccuracy(40points)Positioninformationandcompanyname,in-servicetime(20points)Jobcontentandresponsibilities:detailed,clearandeasytounderstand(20points)2.QuantificationofAchievementsandDemonstrationofSkills(30points)Quantificationofachievements:cleardemonstrationofachievements,includingdatasupport(20points)Skilldemonstration:listofrelevantskillsandtools(10points)3.Matchwithjobrequirements(20points)Relevanceofworkexperiencetojobrequirements(20points)4.DepthandSpecializationofJobResponsibilities(10points)Depthandspecialization:technicalormanagerialdepthinthejobdescription(10points)Education(100points)1.CompletenessandAccuracy(40points)SchoolandDegreeInformation:Accurateandcomplete(20points)MajorsandPrograms:listrelevantprograms,especiallythoserelatedtotheposition(20points)2.AcademicAchievementsandProgramExperience(30points)AcademicAchievements:Highlightachievementsandawardsrelevanttotheposition(20points)Projectexperienceandresearch:listprojectexperiencerelevanttotheposition(10points)3.Matchbetweeneducationalexperienceandjobrequirements(20points)Professionalrelevance:howwellthemajorofstudymatchestheneedsoftheposition(20points)4.DepthandSpecializationofEducationalExperience(10points)AcademicDepth:Demonstrationofprofessionalismandacademicdepth(10points)Project(100points)1.CompletenessandAccuracy(40points)Projectbackgroundandobjectives:clearlydescribethecoreoftheproject(20points)ProjectRolesandResponsibilities:Clearlyidentifyindividualrolesandcontributions(20points)2.ProjectAchievementsandQuantification(30points)AchievementsandTechnicalApplications:Clearlydescribeprojectachievementsandtechnicalapplications(20points)ProblemSolvingSkills:Describethekeyproblemssolvedandtheirimpact(10points)3.Matchbetweenprojectandjobrequirements(20points)Relevanceoftheprojecttothejobrequirements(20points)4.Technicaldepthandcomplexityoftheproject(10points)";
        ResumeData resumeData = jobDescription.getResumeData();
        String prompt = " The following resume is analyzed by scoring each section of personal information, work experience, education, and project experience on a scale of 0-100, and storing the “score” and the “comment” for the improvement of the section under that section. A overall analysis is needed include comment and score , and the resume content is as follows:"
                + resumeData.toString() +"The scoring criteria are:"+criteriaPrompt+"The job description is:"+jd;
        log.info("prompt:{}",prompt);
        var requestBody = webClientHelper.buildRequestBody(prompt);
        var responseBody = webClientHelper.makeRequest(requestBody);
        return jsonToObj.convert(responseBody);
        //get resumeData
        //send request to gemini (write prompt)
        //get response
        //set response to POJO JD.resumeData
        //1.return JD.resumeData
        // write util to transfer to analysis
    }
}
