package com.flaky.lifecycle.manager.flakylifecyclemanager.model;


import com.flaky.lifecycle.manager.flakylifecyclemanager.exception.WorkRunTimeException;

import java.io.Serializable;

/**
 */
public class Result<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer status = 0;

    private String message = "";

    private T data = null;

    public Result(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;

    }

    public Result(Integer status) {
        this.status = status;

    }

    public Result() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message != null ? message : "";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> create(Integer status, String message, T data) {
        return new Result<T>(status, message, data);
    }

    public static <T> Result<T> success(String message) {
        return new Result<T>(StatusCode.SUCCESS, message);
    }

    public static <T> Result<T> success() {
        return success("操作成功!");
    }

    public static <T> Result<T> successData(T data) {
        return create(StatusCode.SUCCESS, "OK", data);
    }

    public static <T> Result<T> create(Integer status, String message) {
        return new Result<T>(status, message);
    }

    public static void error(String msg) {
        throw new WorkRunTimeException(msg);
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
