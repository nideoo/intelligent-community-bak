package com.enc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Minedo
 * @Date 2019-01-08 16:20
 */
public class MinedoDemo {
    public static void main(String[] args) {

        String s = "嘉陵区$火花街道$春飞名都$6栋4单元$春飞名都6栋4单元";

        String str = getChineseName(s, '$', 0);
        System.out.println(str);

        String str1 = getChineseName(s, '$', 1);
        System.out.println(str1);

         String str2 = getChineseName(s, '$', 2);
        System.out.println(str2);








       /*计算百分比的案例
       //需要参与计算的数据
        long oneCount = 30L;
        long twoCount = 30L;
        long threeCount = 30L;


        BigDecimal one=new BigDecimal(oneCount);
        BigDecimal two=new BigDecimal(twoCount);
        BigDecimal three=new BigDecimal(threeCount);
        BigDecimal all=one.add(two).add(three);
        //各年龄阶段人口占比
        BigDecimal onePercent=one.divide(all,4,BigDecimal.ROUND_HALF_UP);
        BigDecimal twoPercent=two.divide(all,4,BigDecimal.ROUND_HALF_UP);
        BigDecimal threePercent=three.divide(all,4,BigDecimal.ROUND_HALF_UP);

        System.out.println("16岁以下的比例:"+onePercent);
        System.out.println("16-60岁的比例:"+twoPercent);
        System.out.println("60岁以上的比例:"+threePercent);

        System.out.println("--------------------------------");
        System.out.println("--------------------------------");

        System.out.println("16岁以下的人口数量:"+oneCount);
        System.out.println("16-60岁的人口数量:"+twoCount);
        System.out.println("60岁以上的人口数量:"+threeCount);*/

    }
    private static String getChineseName (String str,char ch, int type){
        int[] iArr = {};
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ch) {
                iArr = Arrays.copyOf(iArr, iArr.length + 1);
                iArr[iArr.length - 1] = i;
            }
        }
        String rStr = "";
        if (type == 0) {
            rStr = str.substring(0, iArr[0]);
        }
        if (type == 1) {
            rStr = str.substring(iArr[0] + 1, iArr[1]) + "办";
        }
        if (type == 2) {
            rStr = str.substring(iArr[1] + 1, iArr[2]);
        }
        return rStr;
    }
}
