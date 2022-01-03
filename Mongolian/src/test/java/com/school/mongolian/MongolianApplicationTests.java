package com.school.mongolian;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MongolianApplicationTests {
    @Test
    void timeTest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(new Date().getTime());
        String format = sdf.format(date);
        System.out.println(format);
    }

    @Test
    void ArrayTest(){
        List<String> strings = new ArrayList<String>();
        strings.add("123");
        strings.add("234");
        System.out.println(strings);

    }

}
