package org.example.resumemodifierbackend.Utils;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class WebClientHelper {
    @Resource
    private  WebClient webClient;
    @Value("${gemini.api.key}")
    private String geminiAPIKEY;


    public String makeRequest(Map<String ,Object> resonseBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("generativelanguage.googleapis.com")
                        .path("v1beta/models/gemini-1.5-flash:generateContent")
                        .queryParam("key", geminiAPIKEY)
                        .build()
                )
                .header("Content-Type", "application/json")
                .bodyValue(resonseBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
