package com.school.mongolian.service;

import com.school.mongolian.po.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;


public interface UserService {
    public User getUserById(long id);

    public Boolean login(HttpServletResponse response,String name, String password);

}
