package org.example.resumemodifierbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
   private String institutionName;
   private String fieldOfStudy;
   private String degree;
   private String grade;
   private String city;
   private String country;
   private String fromDate;
   private String toDate;
   private String isPresent;
   private String description;

   private String comment;
   private Integer score;
}
