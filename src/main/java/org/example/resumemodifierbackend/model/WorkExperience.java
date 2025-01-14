package org.example.resumemodifierbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperience {
   private String companyName;
   private String jobTitle;
   private String city;
   private String country;
   private String fromDate;
   private String toDate;
   private Boolean isPresent;
   private String description;

   private String comment;
   private Integer score;
}
