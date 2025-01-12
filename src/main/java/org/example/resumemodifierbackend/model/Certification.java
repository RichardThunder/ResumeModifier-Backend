package org.example.resumemodifierbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certification {
    private String name;
    private String issuer;
    private String date;
    private String expiryDate;
    private String url;
    private String description;
}
