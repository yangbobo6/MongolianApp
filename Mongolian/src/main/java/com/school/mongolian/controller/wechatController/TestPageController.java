package com.school.mongolian.controller.wechatController;

import com.school.mongolian.result.Result;
import com.school.mongolian.util.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Page")
public class TestPageController {
    @RequestMapping("/getName")
    public Result<Page<String>> getName(){
        List<String> listPage = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            listPage.add("yangbo"+i);
        }
        Page<String> page = new Page<>(3,3);
        page.build(listPage);

        return Result.success(page);
    }
}
