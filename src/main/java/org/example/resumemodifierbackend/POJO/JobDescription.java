package org.example.resumemodifierbackend.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.resumemodifierbackend.model.ResumeData;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class JobDescription {
    private ResumeData resumeData;
    private String jobDescription;
}
