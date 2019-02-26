package com.flaky.lifecycle.manager.flakylifecyclemanager.exception;

public class CodeRunTimeException extends RuntimeException {

    int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CodeRunTimeException(String message) {
        super(message);
    }

    public CodeRunTimeException(int code, String message) {
        super(message);
        this.code = code;
    }

}
