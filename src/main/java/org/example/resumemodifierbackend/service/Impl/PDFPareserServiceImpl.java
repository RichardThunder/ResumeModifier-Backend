package org.example.resumemodifierbackend.service.Impl;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.example.resumemodifierbackend.Utils.JsonToObj;
import org.example.resumemodifierbackend.Utils.WebClientHelper;
import org.example.resumemodifierbackend.model.ResumeData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PDFPareserServiceImpl {
    @Resource
    private final WebClientHelper webClientHelper;
    @Resource
    private final JsonToObj jsonToObj;
    PDFPareserServiceImpl(WebClientHelper webClientHelper,JsonToObj jsonToObj) {
        this.webClientHelper = webClientHelper;
        this.jsonToObj = jsonToObj;
    }
    //extract text from pdf
    public String getTextFromPDF(InputStream pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }

    //send content to gemini (structural output)
    public ResumeData sendContentToGemini(String content) {
        //build requestBody
        String largeText = "parse this resume using this JSON schema, if it is a resume, set isResume to true, if not, set isResume to false; if it is a chinese name, swap lastname and firstname "
                + content
                + "in this JSON format +"
                + "{ \"userInfo\": {\n    \"firstName\": \"str\",\n    \"lastName\": \"str\",\n    \"headLine\": \"str\",\n    \"phoneNumber\": \"str\",\n    \"email\": \"str\",\n    \"linkedInURL\": \"str\",\n    \"websiteOrOtherProfileURL\": \"str\"\n  },\n  \"achievements\": [\n    {\n      \"achievement\": \"str\"\n    }\n  ],\n  \"award\": [\n    {\n      \"name\": \"str\",\n      \"issuer\": \"str\",\n      \"urlToAward\": \"str\",\n      \"dateOfAward\": \"str\",\n      \"description\": \"str\"\n    }\n  ],\n  \"certifications\": [\n    {\n      \"name\": \"str\",\n      \"issuer\": \"str\",\n      \"date\": \"str\",\n      \"expiryDate\": \"str\",\n      \"url\": \"str\",\n      \"description\": \"str\"\n    }\n  ],\n  \"education\": [\n    {\n      \"institutionName\": \"str\",\n      \"fieldOfStudy\": \"str\",\n      \"degree\": \"str\",\n      \"grade\": \"str\",\n      \"city\": \"str\",\n      \"country\": \"str\",\n      \"fromDate\": \"str\",\n      \"toDate\": \"str\",\n      \"isPresent\": \"str\",\n      \"description\": \"str\"\n    }\n  ],\n  \"projects\": [\n    {\n      \"title\": \"str\",\n      \"projectRole\": \"str\",\n      \"city\": \"str\",\n      \"country\": \"str\",\n      \"fromDate\": \"str\",\n      \"toDate\": \"str\",\n      \"isPresent\": \"bool\",\n      \"description\": \"str\"\n    }\n  ],\n  \"publications\": [\n    {\n      \"name\": \"str\",\n      \"url\": \"str\",\n      \"publisher\": \"str\",\n      \"date\": \"str\"\n    }\n  ],\n  \"references\": [\n    {\n      \"company\": \"str\",\n      \"personName\": \"str\",\n      \"roleOfPerson\": \"str\",\n      \"email\": \"str\",\n      \"phoneNumber\": \"str\",\n      \"description\": \"str\"\n    }\n  ],\n  \"skills\": [\n   \"str\",  ],\n  \"volunteering\": [\n    {\n      \"name\": \"str\",\n      \"role\": \"str\",\n      \"city\": \"str\",\n      \"country\": \"str\",\n      \"fromDate\": \"str\",\n      \"toDate\": \"str\",\n      \"description\": \"str\"\n    }\n  ],\n  \"workExperience\": [\n    {\n      \"companyName\": \"str\",\n      \"jobTitle\": \"str\",\n      \"city\": \"str\",\n      \"country\": \"str\",\n      \"fromDate\": \"str\",\n      \"toDate\": \"str\",\n      \"isPresent\": \"bool\",\n      \"description\": \"str\"\n    }\n  ]\n}";

        Map<String, Object> requestBody= webClientHelper.buildRequestBody(largeText);
    //use body to mono to get response
        String response =  webClientHelper.makeRequest(requestBody);
        log.info("Response from Gemini: {}",response);
        //convert json to object
        return jsonToObj.convert(response);
    }


}

//curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=$GOOGLE_API_KEY" \
//        -H 'Content-Type: application/json' \
//        -d '{
//        "contents": [{
//        "parts":[
//        {"text": "List a few popular cookie recipes using this JSON schema:
//
//Recipe = {\"recipe_name\": str}
//Return: list[Recipe]"
//        }
//        ]
//        }],
//        "generationConfig": { "response_mime_type": "application/json" }
//        }' 2> /dev/null | head