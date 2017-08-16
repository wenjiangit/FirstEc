package com.douliu.latte.ec.data;

/**
 *
 * Created by douliu on 2017/8/14.
 */
public class RspModel<T> {

    private String message;

    private int code;

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }

    @Override
    public String toString() {
        return "RspModel{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
