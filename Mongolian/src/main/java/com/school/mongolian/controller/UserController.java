package com.school.mongolian.controller;

import com.school.mongolian.dto.LoginDto;
import com.school.mongolian.dto.RegisterDto;
import com.school.mongolian.po.User;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import com.school.mongolian.service.UserService;
import com.school.mongolian.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Api(tags = "登录验证")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //登录接口
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<Map> login(HttpServletResponse response,
                                @RequestParam("password") String password,
                                @RequestParam("phone") String phone){
        Result<Map> login = userService.login(response,phone, password);
        if(login!=null){
            return login;
        }else {
            return Result.error(CodeMsg.PASSWORD_WRONG);
        }
    }

    @ApiOperation("通过id获取用户")
    @RequestMapping("getUserById")
    public Result<User> getById(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("token");
        int userId =Integer.parseInt(JwtUtil.getUserId(token));
        User user = userService.getUserById(userId);
        if(user==null){
            return Result.error(CodeMsg.USER_NULL);
        }
        return Result.success(user);
    }

    //注册接口
    @ApiOperation("注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<String> register(@RequestParam("password")String password,@RequestParam("phone") String phone){
        Result register = userService.register(password, phone);
        return register;
    }

    //注销登录
    @DeleteMapping(value = "/logout")
    public Result logout(int id){
        return null;
    }
}