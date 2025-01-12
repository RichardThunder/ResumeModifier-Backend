package org.example.resumemodifierbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
   private String title;
   private String projectRole;
   private String city;
   private String country;
   private String fromDate;
   private String toDate;
   private Boolean isPresent;
   private String description;
}
