package io.nebula.test.web.controller.exception;

public class TestException extends RuntimeException
{
    private int code;
    private String msg;

    public TestException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() { return code; }

    public String getMsg() { return msg; }
}
