package org.example.resumemodifierbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Award {
    private String name;
    private String issuer;
    private String urlToAward;
    private String dateOfAward;
    private String description;
}
