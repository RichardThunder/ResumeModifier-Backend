package org.example.resumemodifierbackend.service.Impl;

import jakarta.annotation.Resource;
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
    @Resource
    WebClientHelper webClientHelper;
    @Resource
    JsonToObj jsonToObj;
    public ResumeData handleJobDescription(String jobDescription,String resumeData) {
        //get JD
        //String jd = jobDescription.getJobDescription();

        String criteriaPrompt = """
                Personal Information (100 points)
                1. Completeness and Accuracy (50 points)
                   Name: clear and accurate, avoid nicknames or irregular formatting (10 points)
                  Contact information: valid and frequently used (20 points)
                 Social media links: valid and relevant to the position (10 points)
                 Address information: concise and relevant to the position (10 points)
                
                2. Conciseness and standardization (30 points) \s
                 Concise information: avoid redundancy (e.g. unnecessary personal details) (15 points)
                  Professionalism: use a professional email address, provide social media links to showcase skills (15 points)
                
                3. Relevance (20 points)
                   Match with the position: whether the content of personal information is relevant to the position applied for (20 points)
                
                Work Experience (100 points)
                1. Completeness and accuracy (40 points)
                    Position information and company name, in-service time (20 points)
                   Job content and responsibilities: detailed, clear and easy to understand (20 points)
                
                2. Quantification of Achievements and Demonstration of Skills (30 points)  \s
                Quantification of achievements: clear demonstration of achievements, including data support (20 points)
                Skill demonstration: list of relevant skills and tools (10 points)
                
                3. Match with job requirements (20 points)
                   Relevance of work experience to job requirements (20 points)
                
                4. Depth and Specialization of Job Responsibilities (10 points)
                   Depth and specialization: technical or managerial depth in the job description (10 points)
                
                Education (100 points)
                1. Completeness and Accuracy (40 points)
                   School and Degree Information: Accurate and complete (20 points)
                   Majors and Programs: list relevant programs, especially those related to the position (20 points)
                
                2. Academic Achievements and Program Experience (30 points)
                   Academic Achievements: Highlight achievements and awards relevant to the position (20 points)
                   Project experience and research: list project experience relevant to the position (10 points)
                
                3. Match between educational experience and job requirements (20 points)
                   Professional relevance: how well the major of study matches the needs of the position (20 points)
                
                4. Depth and Specialization of Educational Experience (10 points)
                    Academic Depth: Demonstration of professionalism and academic depth (10 points)
                
                Project (100 points)
                1. Completeness and Accuracy (40 points) \s
                 Project background and objectives: clearly describe the core of the project (20 points)
                  Project Roles and Responsibilities: Clearly identify individual roles and contributions (20 points)
                
                2. Project Achievements and Quantification (30 points)
                   Achievements and Technical Applications: Clearly describe project achievements and technical applications (20 points)
                   Problem Solving Skills: Describe the key problems solved and their impact (10 points)
                
                3. Match between project and job requirements (20 points)
                   Relevance of the project to the job requirements (20 points)
                
                4. Technical depth and complexity of the project (10 points)
                """;
                //ResumeData resumeData = jobDescription.getResumeData();
        String prompt = " The following resume is analyzed by scoring each section of personal information, work experience, education, and project experience on a scale of 0-100, and storing the “score” and the “comment” for the improvement of the section under that section. A overall analysis is needed include comment and score , and the resume content is as follows:"
                + resumeData+" The scoring criteria are:"+criteriaPrompt+"The job description is:"+jobDescription
                +" The comment should contains strong point and  drawbacks and suggestions for improvement."
                +" you should return a json in this format"
                + " resumeData： { \"overallAnalysis\":{\"comment\": \"str\", \"score\": \"number\"}, \"userInfo\": {\n    \"firstName\": \"str\",\n    \"lastName\": \"str\",\n    \"headLine\": \"str\",\n    \"phoneNumber\": \"str\",\n    \"email\": \"str\",\n    \"linkedInURL\": \"str\",\n    \"websiteOrOtherProfileURL\": \"str\"\n  },\n  \"achievements\": [\n    {\n      \"achievement\": \"str\"\n    }\n  ],\n  \"award\": [\n    {\n      \"name\": \"str\",\n      \"issuer\": \"str\",\n      \"urlToAward\": \"str\",\n      \"dateOfAward\": \"str\",\n      \"description\": \"str\"\n, \"comment\": \"str\"\n, \"score\": \"number\"\n   }\n  ],\n  \"certifications\": [\n    {\n      \"name\": \"str\",\n      \"issuer\": \"str\",\n      \"date\": \"str\",\n      \"expiryDate\": \"str\",\n      \"url\": \"str\",\n      \"description\": \"str\"\n  , \"comment\": \"str\"\n, \"score\": \"number\"\n   }\n  ],\n  \"education\": [\n    {\n      \"institutionName\": \"str\",\n      \"fieldOfStudy\": \"str\",\n      \"degree\": \"str\",\n      \"grade\": \"str\",\n      \"city\": \"str\",\n      \"country\": \"str\",\n      \"fromDate\": \"str\",\n      \"toDate\": \"str\",\n      \"isPresent\": \"str\",\n      \"description\": \"str\"\n  , \"comment\": \"str\"\n, \"score\": \"number\"\n   }\n  ],\n  \"projects\": [\n    {\n      \"title\": \"str\",\n      \"projectRole\": \"str\",\n      \"city\": \"str\",\n      \"country\": \"str\",\n      \"fromDate\": \"str\",\n      \"toDate\": \"str\",\n      \"isPresent\": \"bool\",\n      \"description\": \"str\"\n   , \"comment\": \"str\"\n, \"score\": \"number\"\n  }\n  ],\n  \"publications\": [\n    {\n      \"name\": \"str\",\n      \"url\": \"str\",\n      \"publisher\": \"str\",\n      \"date\": \"str\"\n    }\n  ],\n  \"references\": [\n    {\n      \"company\": \"str\",\n      \"personName\": \"str\",\n      \"roleOfPerson\": \"str\",\n      \"email\": \"str\",\n      \"phoneNumber\": \"str\",\n      \"description\": \"str\"\n , \"comment\": \"str\"\n, \"score\": \"number\"\n     }\n  ],\n  \"skills\": [\n   \"str\",  ],\n  \"volunteering\": [\n    {\n      \"name\": \"str\",\n      \"role\": \"str\",\n      \"city\": \"str\",\n      \"country\": \"str\",\n      \"fromDate\": \"str\",\n      \"toDate\": \"str\",\n      \"description\": \"str\"\n  , \"comment\": \"str\"\n, \"score\": \"number\"\n   }\n  ],\n  \"workExperience\": [\n    {\n      \"companyName\": \"str\",\n      \"jobTitle\": \"str\",\n      \"city\": \"str\",\n      \"country\": \"str\",\n      \"fromDate\": \"str\",\n      \"toDate\": \"str\",\n      \"isPresent\": \"bool\",\n      \"description\": \"str\"\n , \"comment\": \"str\"\n, \"score\": \"number\"\n    }\n  ]\n}"
                +" Return: resumeData";

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
