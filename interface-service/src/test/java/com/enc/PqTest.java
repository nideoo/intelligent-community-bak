package com.enc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PqTest {
    public static void main(String[] args) throws ParseException {
       /* PointPeopleVo vo=new PointPeopleVo();
        vo.setLableToNumber(1);
        vo.setLable();
        System.out.print(vo);*/
       /*String s="中海华庭$华景台一座$A单元";
       String [] strings=s.split("\\$");
       System.out.print(strings)*/;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String str="20110823";
        Date dt=sdf.parse(str);
        System.out.println("身份证日期为:"+dt);

        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.YEAR,-10);//日期减1年

        /*rightNow.add(Calendar.MONTH,3);//日期加3个月
        rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天*/
        Date dt1=rightNow.getTime();
        String reStr = sdf.format(dt1);
        int i = Integer.parseInt(reStr);
        System.out.println("数字"+i);
        System.out.println("字符串"+reStr);
    }
}
