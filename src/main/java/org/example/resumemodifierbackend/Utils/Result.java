package org.example.resumemodifierbackend.Utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Result<T> {
    private Integer status;
    private String msg;
    private T data;
    public Result(Integer code, String msg, T data) {
        this.status = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(ResponseCode responseCode, T data) {
        this.status = responseCode.getCode();
        this.msg = responseCode.getMessage();
        this.data = data;
    }
    public static <T> Result<T> success(T data){
        return new Result<>(ResponseCode.SUCCESS, data);
    }
    public static <T> Result<T> success(ResponseCode responseCode, T data){
        return new Result<>(ResponseCode.SUCCESS, null);
    }
    public static <T> Result<T> error(ResponseCode responseCode,T msg){
        return new Result<>(responseCode, msg);
    }

}
