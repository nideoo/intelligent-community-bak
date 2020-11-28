package com.enc.util;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 * 字符串处理类
 *
 * @version 1.0
 * @date 2019/9/7 21:55
 */
public final class DateFormat {

    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(DateFormat.PATTERN_DATETIME);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DateFormat.PATTERN_DATE);
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern(DateFormat.PATTERN_TIME);
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    private DateFormat() {
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
     * "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd
     * HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(parsePatterns[0]).parse(str.toString());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return long
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return long
     */
    public static long pastHour(Date date) {

        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return long
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis 毫秒
     * @return String
     */
    public static String formatTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 设置年
     *
     * @param date 时间
     * @param amount 年数，-1表示减少
     * @return 设置后的时间
     */
    public static Date setYears(Date date, int amount) {
        return set(date, Calendar.YEAR, amount);
    }

    /**
     * 设置月
     *
     * @param date 时间
     * @param amount 月数，-1表示减少
     * @return 设置后的时间
     */
    public static Date setMonths(Date date, int amount) {
        return set(date, Calendar.MONTH, amount);
    }

    /**
     * 设置周
     *
     * @param date 时间
     * @param amount 周数，-1表示减少
     * @return 设置后的时间
     */
    public static Date setWeeks(Date date, int amount) {
        return set(date, Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * 设置天
     *
     * @param date 时间
     * @param amount 天数，-1表示减少
     * @return 设置后的时间
     */
    public static Date setDays(Date date, int amount) {
        return set(date, Calendar.DATE, amount);
    }

    /**
     * 设置小时
     *
     * @param date 时间
     * @param amount 小时数，-1表示减少
     * @return 设置后的时间
     */
    public static Date setHours(Date date, int amount) {
        return set(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 设置分钟
     *
     * @param date  时间
     * @param amount 分钟数，-1表示减少
     * @return 设置后的时间
     */
    public static Date setMinutes(Date date, int amount) {
        return set(date, Calendar.MINUTE, amount);
    }

    /**
     * 设置秒
     *
     * @param date 时间
     * @param amount 秒数，-1表示减少
     * @return 设置后的时间
     */
    public static Date setSeconds(Date date, int amount) {
        return set(date, Calendar.SECOND, amount);
    }

    /**
     * 设置毫秒
     *
     * @param date 时间
     * @param amount 毫秒数，-1表示减少
     * @return 设置后的时间
     */
    public static Date setMilliseconds(Date date, int amount) {
        return set(date, Calendar.MILLISECOND, amount);
    }

    /**
     * 设置日期属性
     *
     * @param date 时间
     * @param calendarField 更改的属性
     * @param amount 改数，-1表示减少
     * @return 设置后的时间
     */
    private static Date set(Date date, int calendarField, int amount) {
        Assert.notNull(date, "The date must not be null");
        Calendar c = Calendar.getInstance();
        c.setLenient(false);
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param format ConcurrentDateFormat
     * @return 时间
     */
    public static Date parse(String dateStr, ConcurrentDateFormat format) {
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日期时间格式化
     *
     * @param temporal 时间
     * @return 格式化后的时间
     */
    public static String formatDateTime(TemporalAccessor temporal) {
        return DATETIME_FORMAT.format(temporal);
    }

    /**
     * 日期时间格式化
     *
     * @param temporal  时间
     * @return 格式化后的时间
     */
    public static String formatDate(TemporalAccessor temporal) {
        return DATE_FORMAT.format(temporal);
    }

    /**
     * 时间格式化
     *
     * @param temporal 时间
     * @return 格式化后的时间
     */
    public static String formatTime(TemporalAccessor temporal) {
        return TIME_FORMAT.format(temporal);
    }

    /**
     * 日期格式化
     *
     * @param temporal  时间
     * @param pattern 表达式
     * @return 格式化后的时间
     */
    public static String format(TemporalAccessor temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param pattern 表达式
     * @return 时间
     */
    public static TemporalAccessor parseTemporalAccessor(String dateStr, String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return format.parse(dateStr);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param formatter DateTimeFormatter
     * @return 时间
     */
    public static TemporalAccessor parse(String dateStr, DateTimeFormatter formatter) {
        return formatter.parse(dateStr);
    }

}
