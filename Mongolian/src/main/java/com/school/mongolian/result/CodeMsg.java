package com.school.mongolian.result;

public class CodeMsg {
    private int code;
    private String msg;

    public CodeMsg(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    //软件更新提醒
    public static CodeMsg UPDATE_APP = new CodeMsg(97,"软件待更新");

    //登录异常


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
}
