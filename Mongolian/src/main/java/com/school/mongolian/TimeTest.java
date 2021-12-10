package com.school.mongolian;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//  https://blog.csdn.net/wdf521121/article/details/88861610
public class TimeTest {
    public static void main(String[] args) throws ParseException {
        System.out.println(System.currentTimeMillis());

        //处理时间慢  慎用！！
        System.out.println(Calendar.getInstance().getTime());
        System.out.println(Calendar.getInstance().getTimeInMillis());

        System.out.println(new Date().getTime());

        System.out.println("-----------------------");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        System.out.println(date);

        System.out.println(new Date());

        //计算两个时间的差的天数
        String dateStr = "2008-1-1 1:21:28";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.parse(dateStr));   //美式时间
        System.out.println(format.parse(dateStr).getTime()); //毫秒
        long time = format.parse(dateStr).getTime();
        long time2 = new Date().getTime();
        int days = (int)((time2 - time)/(1000*3600*24));
        System.out.println(time+"---------"+time2);
        System.out.println(days);
    }
}
