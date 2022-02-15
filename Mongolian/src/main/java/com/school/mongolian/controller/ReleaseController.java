package com.school.mongolian.controller;

import com.school.mongolian.dao.ReleaseDao;
import com.school.mongolian.dao.UserDao;
import com.school.mongolian.dto.ReleaseDto;
import com.school.mongolian.po.Release;
import com.school.mongolian.po.User;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import com.school.mongolian.service.UserService;
import com.school.mongolian.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: yangbo
 * @Date: 2022-02-13-17:55
 * @Description:
 */
@Api("发布帖子模块")
@RestController
@RequestMapping("/release")
public class ReleaseController {
    @Autowired
    private ReleaseDao releaseDao;
    @Autowired
    private UserService userService;

    //用户发送帖子
    @ApiOperation("发布帖子")
    @RequestMapping(value = "/insertInfo",method = RequestMethod.POST)
    public Result<Boolean> insertInfo(HttpServletRequest request, @RequestParam("text")String text) throws ParseException {
        String token = request.getHeader("token");
        int userId =Integer.parseInt(JwtUtil.getUserId(token));
        System.out.println(userId);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = df.format(new Date());
        Date date = df.parse(date1);

        /*
        * name 值怎样取出来
        * 1. 前端传送过来
        * 2. 后端查询数据库 （消耗）
         * */
        User user = userService.getUserById(userId);
        Boolean b = releaseDao.releaseInfo(userId,user.getName(), text, date);
        if(b){
            return new Result<Boolean>("发送成功",true);
        }
        return Result.RELEASE_ERROR;

    }

    //查看帖子
    @ApiOperation("查找帖子")
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public Result<List<Release>> selectRelease(){
        List<Release> releasesList = releaseDao.selectRelease();
        if(releasesList.size()==0){
            return Result.error(CodeMsg.NULL_RELEASE);
        }
        return Result.success(releasesList);


    }

}
