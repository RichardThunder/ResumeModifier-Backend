package org.example.resumemodifierbackend.controller;

import jakarta.websocket.OnClose;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.resumemodifierbackend.Utils.Result;
import org.example.resumemodifierbackend.model.ResumeData;
import org.example.resumemodifierbackend.service.Impl.PDFPareserServiceImpl;
import org.example.resumemodifierbackend.service.PDFParserService;
import org.example.resumemodifierbackend.service.QnAService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @PostMapping("/api/pdfupload")
    public Result<ResumeData> pdfUploader(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        log.info("You successfully uploaded {}!", file.getOriginalFilename());
        String content = pdfParserService.getTextFromPDF(file.getInputStream());
        ResumeData response = pdfParserService.sendContentToGemini(content);
        log.info("Response from Gemini: {}",response);
        return Result.success(response);

    }

    @PostMapping("/api/qna")
    public Result<String> qna(@RequestBody Map<String,String> payload){
        String question  = payload.get("question");
        log.info("Get question successfully: {}", question);
        return Result.success(qnaService.getAnswer(question));
    }


}