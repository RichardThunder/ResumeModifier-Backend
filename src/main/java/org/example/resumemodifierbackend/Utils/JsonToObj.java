package org.example.resumemodifierbackend.Utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.resumemodifierbackend.model.ResumeData;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JsonToObj {
    public  ResumeData convert(String json) {
        // Step 1: Parse top-level JSON
        JSONObject rootObject = JSON.parseObject(json);

//        // Step 2: Extract the "data" field and parse it as JSON
//        String dataString = rootObject.getString("data");
//        JSONObject dataObject = JSON.parseObject(dataString);

        // Step 3: Navigate to the nested "text" field
        String parsedJson = rootObject
                .getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text");


        log.info("userInfoJson:{}",parsedJson);
        // Step 4: Deserialize the "text" into UserInfo object
        ResumeData rd = JSON.parseObject(parsedJson, ResumeData.class);

        log.info("obj:{}",rd.toString());

        return rd;
    }
}
