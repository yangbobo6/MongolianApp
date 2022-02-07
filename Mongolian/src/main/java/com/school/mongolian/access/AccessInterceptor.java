package com.school.mongolian.access;

import com.school.mongolian.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
@Slf4j
public class AccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        //response.setHeader("Access-Control-Allow-Origin", "*");
        //response.setHeader("allowedOriginPatterns", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Expose-Headers", "Token");


        log.info("验证token前11");
        //验证token
        String token = request.getHeader("token");
        log.info(token);
        boolean result = JwtUtil.verify(token);
        if (result) {
            log.info("验证token成功");
            return true;
        }else{
            log.info("验证token失败");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject res = new JSONObject();
            res.put("code","409");
            res.put("msg","token验证失败,请先登录");
            PrintWriter out = null ;
            out = response.getWriter();
            out.write(res.toString());
            out.flush();
            out.close();
            return false;
        }

    }
}
