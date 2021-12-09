package com.school.mongolian;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        long time = new Date().getTime();
        System.out.println(time);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,12,8,00,00,00);
        System.out.println(calendar.getTime());
        long time2 = calendar.getTimeInMillis();
        System.out.println(time2);

        int days = (int)(time - time2)/(1000*3600*24);

        System.out.println(days);


    }
    
    
    public int getDays() {
        String dateStr = "2008-1-1 1:21:28";
        String dateStr2 = "2010-1-2 1:21:28";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int days = 0;
        try {
            Date date2 = format.parse(dateStr2);
            Date date = format.parse(dateStr);
            days = differentDaysByMillisecond(date, date2);
            System.out.println("两个日期的差距：" + days );
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


    private static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
}
