package org.example.resumemodifierbackend.service.Impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.resumemodifierbackend.Utils.WebClientHelper;
import org.example.resumemodifierbackend.service.QnAService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class QnAServiceImpl implements QnAService {
    @Resource
    private final WebClientHelper webClientHelper;
    public QnAServiceImpl(WebClientHelper webClientHelper) {
        this.webClientHelper = webClientHelper;
    }
    public String getAnswer(String question) {
        //build request body
        Map<String, Object> requestBody =webClientHelper.buildRequestBody(question);
        //make request to server
        return webClientHelper.makeRequest(requestBody);
    }
}
//        curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=GEMINI_API_KEY" \
//        -H 'Content-Type: application/json' \
//        -X POST \
//        -d '{
//        "contents": [{
//            "parts":[{"text": "Explain how AI works"}]
//        }]
//    }'
