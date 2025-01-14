package org.example.resumemodifierbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteering {
    private String name;
    private String role;
    private String city;
    private String country;
    private String fromDate;
    private String toDate;
    private String description;

    private String comment;
    private Integer score;
}
