package org.example.resumemodifierbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    private String skill;

    private String comment;
    private Integer score;
}
