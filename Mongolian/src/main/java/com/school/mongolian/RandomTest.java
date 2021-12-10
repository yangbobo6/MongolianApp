package com.school.mongolian;

import com.school.mongolian.dao.DailySenDao;
import com.school.mongolian.po.DailySen;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTest {
    public List<DailySen> getDailySenDao() {
        List<DailySen> list = new ArrayList<>();

        list.add(new DailySen(1,"yang","123"));
        list.add(new DailySen(2,"bo","12333"));
        list.add(new DailySen(3,"tan","12223"));
        list.add(new DailySen(4,"jing","12344"));
        return list;
    }

    public static void main(String[] args) {
        //获取一个序列里面的随机对象
        RandomTest randomTest = new RandomTest();
        List<DailySen> list = randomTest.getDailySenDao();
        int num = (int)(Math.random()*list.size());
        //Random random = new Random(list.size());

        System.out.println(list.get(num));
    }
}
