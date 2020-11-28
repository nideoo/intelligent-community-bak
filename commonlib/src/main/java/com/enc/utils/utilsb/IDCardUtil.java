package com.enc.utils.utilsb;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.*;

//通过身份证号获取年龄，性别，所在省份信息
@Data
public class IDCardUtil {
    private String province;  //省份
    private String gender;    //性别
    private Date birthday;  // 出生日期
    private int year;         //年份
    private int age;          //年龄

    private Map<String, String> cityCodeMap = new HashMap<String, String>() {
        {
            this.put("11", "北京");
            this.put("12", "天津");
            this.put("13", "河北");
            this.put("14", "山西");
            this.put("15", "内蒙古");
            this.put("21", "辽宁");
            this.put("22", "吉林");
            this.put("23", "黑龙江");
            this.put("31", "上海");
            this.put("32", "江苏");
            this.put("33", "浙江");
            this.put("34", "安徽");
            this.put("35", "福建");
            this.put("36", "江西");
            this.put("37", "山东");
            this.put("41", "河南");
            this.put("42", "湖北");
            this.put("43", "湖南");
            this.put("44", "广东");
            this.put("45", "广西");
            this.put("46", "海南");
            this.put("50", "重庆");
            this.put("51", "四川");
            this.put("513300","四川藏区");
            this.put("513200","四川藏区");
            this.put("513422","四川藏区");
            this.put("52", "贵州");
            this.put("53", "云南");
            this.put("54", "西藏");
            this.put("61", "陕西");
            this.put("62", "甘肃");
            this.put("63", "青海");
            this.put("64", "宁夏");
            this.put("65", "新疆");
            this.put("71", "台湾");
            this.put("81", "香港");
            this.put("82", "澳门");
            this.put("91", "境外");
        }
    };

    //通过构造方法初始化各个成员属性
    public IDCardUtil(String idCard) {
        //获取省份
        String provinceId=idCard.substring(0,2);
        String siChuangId=idCard.substring(0,6);
        Set<String> key=this.cityCodeMap.keySet();
        for(String id:key){
            if("513300".equals(siChuangId) || "513200".equals(siChuangId) || "513422".equals(siChuangId)){
                this.province=this.cityCodeMap.get(siChuangId);
            }else if(id.equals(provinceId)){
                this.province=this.cityCodeMap.get(id);
                break;
            }
        }

        // 获取性别
        String id17=idCard.substring(16,17);
        if(Integer.parseInt(id17) % 2 !=0){
            this.gender = "男";
        }else{
            this.gender = "女";
        }

        String birthday=idCard.substring(6,14);
        Date birthdate;
        try{
            birthdate=new SimpleDateFormat("yyyyMMdd").parse(birthday);
            this.birthday=birthdate;
            GregorianCalendar currentDay = new GregorianCalendar();
            currentDay.setTime(birthdate);
            this.year = currentDay.get(Calendar.YEAR);
            //获取年龄
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
            String year = simpleDateFormat.format(new Date());
            this.age = Integer.parseInt(year) - this.year;
        }catch (Exception e){
            e.printStackTrace();
        }





    }
}
