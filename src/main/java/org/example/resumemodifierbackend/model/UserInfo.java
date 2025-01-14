package org.example.resumemodifierbackend.model;
import com.alibaba.fastjson2.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
  private String firstName;
  private String lastName;
  private String headLine;
  private String phoneNumber;
  private String email;
  private String linkedInURL;
  private String websiteOrOtherProfileURL;

  private String comment;
  private Integer score;
}
