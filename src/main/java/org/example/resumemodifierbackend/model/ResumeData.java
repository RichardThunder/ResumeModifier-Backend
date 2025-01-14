package org.example.resumemodifierbackend.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ResumeData {
    private UserInfo userInfo;
    private List<Achievement> achievements;
    private List<Award> award;
    private List<Certification> certifications;
    private List<Education> education;
    private List<Project> projects;
    private List<Publication> publications;
    private List<Reference> references;
    private List<String> skills;
    private List<Volunteering> volunteering;
    private List<WorkExperience> workExperience;
}