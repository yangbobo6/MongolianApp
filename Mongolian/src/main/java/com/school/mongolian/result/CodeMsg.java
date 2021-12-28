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
    public static CodeMsg PASSWORD_WRONG = new CodeMsg(40001,"密码错误");

    //获取谚语 / 每日一句 板块
    public static final CodeMsg PHOTO_ERROR = new CodeMsg(30001,"服务器返回错误");
    public static final CodeMsg DAILY_SEN_List_ERROR = new CodeMsg(30002,"每日一句集合返回错误");
    public static final CodeMsg DAILY_SEN_ERROR = new CodeMsg(30003,"每日一句返回错误");
    public static final CodeMsg DAILY_TYPE_ERROR = new CodeMsg(30004,"没有该类型");

    //获取视频板块
    public static final CodeMsg VIDEO_ERROR = new CodeMsg(50000,"没有视频");


    //获取单词板块
    public static final CodeMsg WORD_ERROR = new CodeMsg(60000,"没有查询到单词");

    //文本模块
    public static final CodeMsg TEXT_ERROR = new CodeMsg(70000,"没有查询到文本");

    //测试错误
    public static final CodeMsg TEST_ERROR = new CodeMsg(80000,"没有查询到测试文本");
    public static final CodeMsg GRADE_ERROR = new CodeMsg(80001,"没到检测到名字");

    //Wechat错误
    public static final CodeMsg OutOfSchool = new CodeMsg(90001,"查询结果为空");


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
