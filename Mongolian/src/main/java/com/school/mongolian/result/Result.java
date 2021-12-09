package com.school.mongolian.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data){
        this.code = 300;
        this.data = data;
    }

    public Result(CodeMsg cmg){
        if(cmg==null){
            return;
        }
        this.code = cmg.getCode();
        this.msg = cmg.getMsg();
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    public static <T> Result<T> error(CodeMsg cmg){
        return new Result<T>(cmg);
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
