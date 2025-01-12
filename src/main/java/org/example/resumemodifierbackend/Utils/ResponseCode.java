package org.example.resumemodifierbackend.Utils;

public enum ResponseCode {
    SUCCESS(200,"Success"),
    BAD_REQUEST(400,"Bad Request"),
    UNAUTHORIZED(401,"Unauthorized"),
    FORBIDDEN(403,"Forbidden"),
    NOT_FOUND(404,"Not Found"),
    // 服务端错误状态码
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    // 自定义状态码
    VALIDATION_ERROR(1001, "Validation Error"),
    BUSINESS_ERROR(1002, "Business Logic Error"),
    INVALID_USER_AGE(1003,"Invalid user age"),
    INVALID_USER_NAME(1004,"Invalid user name");

    int code;
    String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public static ResponseCode getResponseCode(int code) {
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.getCode() == code) {
                return responseCode;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }
}
