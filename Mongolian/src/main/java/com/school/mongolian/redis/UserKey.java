package com.school.mongolian.redis;

/**
 * @Author: yangbo
 * @Date: 2022-02-15-16:13
 * @Description:
 */
public class UserKey extends BasePrefix{

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey token = new UserKey("tk");
    public static UserKey userKey = new UserKey("user");
}
