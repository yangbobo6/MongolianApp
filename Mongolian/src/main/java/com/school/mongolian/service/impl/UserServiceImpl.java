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
    public Boolean login(HttpServletResponse response,String phone, String password) {
        User user = userDao.getByPhone(phone);
        if(!user.getPassword().equals(password)){
            return false;
        }
        //如果验证成功，则生成jwt登录
        String token = JwtUtil.sign(user.getPhone(),user.getId()+"");
        log.info(token);

//        if(token!=null){
//            HashMap<String,Object> hm = new HashMap<String,Object>();
//            hm.put("token",token);
//            //hm.put("status",user.getRole());
//            hm.put("phone",user.getPhone());
//            response.addHeader("token",token);
//            return Result.success();
//        }

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
    public Result register(String password, String phone) {

        if(userDao.getByPhone(phone)!=null && !phone.equals("")){
            return Result.error(CodeMsg.PHOTO_ERROR);
        }
        String name = "蒙语用户";
        name = name + "_"+phone.substring(6);

        Boolean insert = userDao.insert(name,password, phone);
        return Result.success("注册成功");
    }

    //注销登录




}
