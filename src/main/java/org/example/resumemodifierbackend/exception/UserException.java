package org.example.resumemodifierbackend.exception;

import lombok.Getter;
import lombok.Setter;
import org.example.resumemodifierbackend.Utils.ResponseCode;

@Getter
@Setter
public class UserException extends RuntimeException {
    private ResponseCode code;
    public UserException(ResponseCode messageEnum) {
        super(messageEnum.getMessage());
        this.code = messageEnum;
    }
}