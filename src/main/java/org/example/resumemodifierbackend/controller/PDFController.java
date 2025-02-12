package org.example.resumemodifierbackend.controller;

import com.alibaba.fastjson2.JSON;
import jakarta.websocket.OnClose;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.resumemodifierbackend.POJO.JobDescription;
import org.example.resumemodifierbackend.Utils.Result;
import org.example.resumemodifierbackend.model.ResumeData;
import org.example.resumemodifierbackend.service.Impl.JobDescriptionServiceImpl;
import org.example.resumemodifierbackend.service.Impl.PDFPareserServiceImpl;
import org.example.resumemodifierbackend.service.PDFParserService;
import org.example.resumemodifierbackend.service.QnAService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class PDFController {
    private QnAService qnaService;
    private PDFPareserServiceImpl pdfParserService;
    private JobDescriptionServiceImpl jobDescriptionsService;

    @PostMapping("/api/qna")
    public Result<String> qna(@RequestBody Map<String,String> payload){
        String question  = payload.get("question");
        log.info("Get question successfully: {}", question);
        return Result.success(qnaService.getAnswer(question));
    }

    @PostMapping("/api/pdfupload")
    public Result<ResumeData> pdfUploader(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        log.info("You successfully uploaded {}!", file.getOriginalFilename());
        String content = pdfParserService.getTextFromPDF(file.getInputStream());
        ResumeData response = pdfParserService.sendContentToGemini(content);
        log.info("Response from Gemini: {}",response);
        return Result.success(response);

    }
    @PostMapping("/api/job_description_upload")
    public Result<ResumeData> getJobDescription(@RequestParam("job_description") String job_description, @RequestParam("json") String json){
        log.info("You successfully uploaded job description!:{}",json);
        /*ResumeData rd = JSON.parseObject(json, ResumeData.class);
        JobDescription jobDescription  = new JobDescription(rd,job_description);
        ResumeData result =  jobDescriptionsService.handleJobDescription(jobDescription);
        */
        ResumeData result =  jobDescriptionsService.handleJobDescription(job_description,json);
        return Result.success(result);
    }

}