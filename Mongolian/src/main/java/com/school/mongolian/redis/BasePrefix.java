package com.school.mongolian.redis;

/**
 * @Author: yangbo
 * @Date: 2022-02-14-18:00
 * @Description:
 */
public abstract class BasePrefix implements keyPrefix{
    private int expireSeconds;
    private String prefix;

    //有的在redis数据库存储的不存在过期时间
    public BasePrefix(String prefix) {
        this.expireSeconds = 0;
        this.prefix = prefix;
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    //设置过期时间

    @Override
    public int expireSecond() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        //通过反射获取前缀名
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }
}
