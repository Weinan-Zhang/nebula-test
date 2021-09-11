package io.nebula.test.web.controller;

import io.nebula.test.model.ResponseDto;
import io.nebula.test.web.controller.exception.TestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TestExceptionHandler {
    @ExceptionHandler(TestException.class)
    @ResponseBody
    public ResponseDto<Object> handleTestException(TestException e) {
        return ResponseDto.buildResponse(e.getCode(), e.getMsg(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseDto handleException(TestException e) {
        return ResponseDto.buildResponse(e.getCode(), e.getMsg(), null);
    }
}
