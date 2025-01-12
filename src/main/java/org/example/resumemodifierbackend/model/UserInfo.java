package org.example.resumemodifierbackend.model;
import com.alibaba.fastjson2.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
  @JSONField(name = "firstName")
  private String firstName;
  @JSONField(name = "lastName")
  private String lastName;
  @JSONField(name = "headLine")
  private String headLine;
  @JSONField(name = "phoneNumber")
  private String phoneNumber;
  @JSONField(name = "email")
  private String email;
  @JSONField(name = "linkedInURL")
  private String linkedInURL;
  @JSONField(name = "websiteOrOtherProfileURL")
  private String websiteOrOtherProfileURL;
}
