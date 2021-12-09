package com.school.mongolian.service.impl;

import com.school.mongolian.dao.UserDao;
import com.school.mongolian.po.User;
import com.school.mongolian.service.UserService;
import com.school.mongolian.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    //定义cookie
    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(long id) {
        log.info("进入service");
        User user = userDao.getById(id);
        log.info("退出service");
        return user;
    }

    @Override
    public Boolean login(HttpServletResponse response,String name, String password) {
        User user = userDao.getByName(name);
        if(!user.getPassword().equals(password)){
            return false;
        }
        String token = JwtUtil.sign(user.getName(),user.getId()+"");
        if(token!=null){
            addCookie(response,token,user);
        }

        return true;
    }

    //设置cookie
    private void addCookie(HttpServletResponse response,String token,User user){
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge((int) JwtUtil.EXPIRE_TIME);
        cookie.setPath("/");
        cookie.getComment();
        cookie.getVersion();
        response.addCookie(cookie);
    }
}
