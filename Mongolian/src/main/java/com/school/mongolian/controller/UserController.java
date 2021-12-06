package com.school.mongolian.controller;

import com.school.mongolian.dto.LoginDto;
import com.school.mongolian.po.User;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import com.school.mongolian.service.UserService;
import com.school.mongolian.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "登录验证")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<String> login(HttpServletResponse response,@RequestBody LoginDto loginDto){
        Boolean login = userService.login(response,loginDto.getName(), loginDto.getPassword());
        if(login){
            return Result.success("登录成功");
        }else {
            return Result.error(CodeMsg.PASSWORD_WRONG);
        }
    }


    @RequestMapping("getUserById")
    public Result<User> getById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")long id){
        String token = request.getHeader("token");
        int userId =Integer.parseInt(JwtUtil.getUserId(token));
        User user = userService.getUserById(userId);
        if(user==null){
            return Result.error(CodeMsg.UPDATE_APP);
        }
        return Result.success(user);
    }
}
