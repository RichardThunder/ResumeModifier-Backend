package org.example.resumemodifierbackend.handle;

import org.example.resumemodifierbackend.Utils.ResponseCode;
import org.example.resumemodifierbackend.Utils.Result;
import org.example.resumemodifierbackend.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandle extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        logger.info("进入error");

        if (e instanceof UserException) {
            logger.error(e.getMessage());
            return Result.error( ((UserException) e).getCode(), e.getMessage());
        } else {
            logger.error("系统异常 {}", e.getMessage());
            return Result.error(ResponseCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}