package com.school.mongolian.service;

import com.school.mongolian.po.User;
import com.school.mongolian.result.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface UserService {
    public User getUserById(long id);

    public Result<Map> login(HttpServletResponse response, String phone, String password);

    public Result register(String password, String phone);

}
