package com.school.mongolian.service.impl;

import com.school.mongolian.dao.UserDao;
import com.school.mongolian.dto.LoginDto;
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
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    //定义cookie
    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    UserDao userDao;

    //通过id获取用户
    @Override
    public User getUserById(int id) {
        log.info("进入service");
        User user = userDao.getById(id);
        log.info("退出service");
        return user;
    }

    //判断登录是否成功
    @Override
    public Result<Map> login(HttpServletResponse response, String phone, String password) {
        User user = userDao.getByPhone(phone);
        if(!user.getPassword().equals(password)){
            return Result.error(CodeMsg.USER_NULL);
        }
        //如果验证成功，则生成jwt登录
        String token = JwtUtil.sign(user.getPhone(),user.getId()+"");
        LoginDto user1 = new LoginDto();
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setPhone(user.getPhone());
        user1.setSex(user.getSex());

        if(token!=null){
            HashMap<String,Object> hm = new HashMap<String,Object>();
            hm.put("token",token);
            //hm.put("status",user.getRole());
            hm.put("user",user1);
            response.addHeader("token",token);
            return Result.success(hm);
        }

//        //添加到cookie里面
//        if(token!=null){
//            Cookie cookie= addCookie(response,token,user);
//            return Result.success(cookie);
//        }
        return Result.error(CodeMsg.USER_NULL);


    }


    //设置cookie
    private Cookie addCookie(HttpServletResponse response,String token,User user){
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge((int) JwtUtil.EXPIRE_TIME);
        cookie.setPath("/");
        cookie.getComment();

        System.out.println(cookie.getComment()+ "   "+cookie.getVersion());
        cookie.getVersion();
        response.addCookie(cookie);
        return cookie;
    }

    //用户注册功能
    @Override
    public Result register(String password, String phone) {

        if(userDao.getByPhone(phone)!=null && !phone.equals("")){
            return new Result(40002,"该用户已存在",false);
        }
        String name = "蒙语用户";
        name = name + "_"+phone.substring(6);

        Boolean insert = userDao.insert(name,password, phone);
        return new Result("注册成功",true);
    }

    //完善个人信息


    //注销登录

}
