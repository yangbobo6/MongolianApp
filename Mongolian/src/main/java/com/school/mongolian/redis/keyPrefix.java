package com.school.mongolian.redis;

/**
 * @Author: yangbo
 * @Date: 2022-02-14-17:56
 * @Description:  redis 设置统一格式  前缀 + 过期时间
 */
public interface keyPrefix {
    //过期时间
    public int expireSecond();
    //设置前缀
    public String getPrefix();
}
