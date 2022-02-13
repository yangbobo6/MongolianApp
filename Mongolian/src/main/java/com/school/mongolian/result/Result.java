package com.school.mongolian.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data){
        this.code = 200;
        this.data = data;
    }
    public Result(String msg,T data){
        this.code = 200;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(CodeMsg cmg){
        if(cmg==null){
            return;
        }
        this.code = cmg.getCode();
        this.msg = cmg.getMsg();
    }

    //返回值成功时
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    //返回失败时
    public static <T> Result<T> error(CodeMsg cmg){
        return new Result<T>(cmg);
    }

    public static <T> Result<T> error(T data){
        return new Result<T>(data);
    }

    public static final Result<Boolean> RELEASE_ERROR = new Result<Boolean>(11000,"发帖失败",true);



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
