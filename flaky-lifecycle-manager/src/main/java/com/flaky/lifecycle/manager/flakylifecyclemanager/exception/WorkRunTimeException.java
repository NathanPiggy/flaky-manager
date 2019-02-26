package com.flaky.lifecycle.manager.flakylifecyclemanager.exception;


import com.flaky.lifecycle.manager.flakylifecyclemanager.model.StatusCode;

public class WorkRunTimeException extends CodeRunTimeException {

    public WorkRunTimeException(String message) {
        super(StatusCode.WORK_ERROR, message);
    }

    public WorkRunTimeException(int code, String message) {
        super(code, message);
    }

}
