package org.example.resumemodifierbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reference {
    private String company;
    private String personName;
    private String roleOfPerson;
    private String email;
    private String phoneNumber;
    private String description;
}
