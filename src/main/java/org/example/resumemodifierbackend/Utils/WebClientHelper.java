package org.example.resumemodifierbackend.Utils;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class WebClientHelper {
    @Resource
    private  WebClient webClient;
    @Value("${gemini.api.key}")
    private String geminiAPIKEY;

    //构建通用提示词请求体
    public Map<String, Object> buildRequestBody(String prompt){
        return Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                ),
                "generationConfig", Map.of("response_mime_type", "application/json")
        );
    }

    public String makeRequest(Map<String ,Object> requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("generativelanguage.googleapis.com")
                        .path("v1beta/models/gemini-1.5-flash:generateContent")
                        .queryParam("key", geminiAPIKEY)
                        .build()
                )
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
