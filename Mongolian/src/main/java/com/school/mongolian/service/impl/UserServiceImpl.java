package com.school.mongolian.service.impl;

import com.school.mongolian.dao.UserDao;
import com.school.mongolian.po.User;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
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

    //通过id获取用户
    @Override
    public User getUserById(long id) {
        log.info("进入service");
        User user = userDao.getById(id);
        log.info("退出service");
        return user;
    }

    //判断登录是否成功
    @Override
    public Boolean login(HttpServletResponse response,String name, String password) {
        User user = userDao.getByName(name);
        if(!user.getPassword().equals(password)){
            return false;
        }
        //如果验证成功，则生成jwt登录
        String token = JwtUtil.sign(user.getName(),user.getId()+"");
        log.info(token);
        //添加到cookie里面
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
        System.out.println(cookie.getComment()+ "   "+cookie.getVersion());
        cookie.getVersion();
        response.addCookie(cookie);
    }

    //用户注册功能
    @Override
    public Result register(String name, String password, String phone) {

        if(userDao.getByName(name)!=null ){
            return Result.error(CodeMsg.NAME_ERROR);
        }
        if(userDao.getByPhone(phone)!=null && !phone.equals("")){
            return Result.error(CodeMsg.PHOTO_ERROR);
        }

        Boolean insert = userDao.insert(name, password, phone);
        return Result.success("注册成功");


    }



}
