package com.school.mongolian.access;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// 2021/12/4
//在请求处理之前进行调用（Controller方法调用之前）
@Slf4j
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("过滤器生效");
        registry.addInterceptor(new AccessInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login/**")
                .excludePathPatterns("/test/**")
                .excludePathPatterns("/register/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/dailySen/**")
                .excludePathPatterns("/folkway/**")
                .excludePathPatterns("/video/**")
                .excludePathPatterns("/word/**")
                .excludePathPatterns("/text/**")
                .excludePathPatterns("/testGrade/**")
                .excludePathPatterns("/Wechat/**","/Page/**","/brand/**","/folktale/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/swagger-ui.html#/**")
                ;
    }

    //如果访问/images/下的路径，那么就映射到后面的路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// 允许跨域访问的路径
                .allowedOriginPatterns("*")  // 设置允许跨域请求的域名
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")// 允许请求方法
                .maxAge(168000)// 预检间隔时间
                .allowedHeaders("*")// 允许头部设置
                .allowCredentials(true); // 是否发送cookie
    }
}
