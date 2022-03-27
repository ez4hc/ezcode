package com.ezcode.common.utils;

/**
 * @Author: hc
 * @Date: 2022/3/26 18:03
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@Slf4j
public class DateUtil {

    /**
     * 将日期字符串转为指定格式的日期字符串
     *
     * @param dateStr    日期字符串
     * @param oriPattern 源格式,默认yyyyMMddHHmmss
     * @param desPattern 目标格式,默认yyyy-MM-dd HH:mm:ss
     * @return 返回目标格式的日期字符串
     * @Author: l90080613
     */
    public static String formateDate(String dateStr, String oriPattern, String desPattern) {
        if (dateStr == null || "".equals(dateStr)) {
            return "";
        }

        /* 默认的日期格式化前后的格式 */
        if (oriPattern == null || "".equals(oriPattern)) {
            oriPattern = "yyyyMMddHHmmss".substring(0, dateStr.length());
        }
        if (desPattern == null || "".equals(desPattern)) {
            desPattern = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(oriPattern);

        try {
            Date date = sdf.parse(dateStr);
            sdf = new SimpleDateFormat(desPattern);
            //返回指定的字符串
            return sdf.format(date);
        }
        catch (ParseException e1) {
            log.error("", e1);
            //异常时,原值返回
            return dateStr;
        }
    }

    /**
     * 日期字符串转换成日期类型 ，格式yyyyMMddHHmmss
     * <功能详细描述>
     *
     * @param dateStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date parseDate(String dateStr) throws Exception {
        return parseDate(dateStr, "yyyyMMddHHmmss");
    }

    /**
     * 日期字符串转换成日期格式
     * <功能详细描述>
     *
     * @param dateStr
     * @param pattern
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date parseDate(String dateStr, String pattern) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(dateStr);
        }
        catch (ParseException e) {
            log.error("日期格式转换异常，date:" + dateStr + ",pattern:" + pattern);
            throw new Exception("系统内部异常，日期转换异常。");
        }
    }

    /**
     * 对日期字符串的月份进行偏移
     *
     * @param dateStr    日期字符串
     * @param oriPattern 源格式,默认yyyyMMddHHmmss
     * @param desPattern 目标格式,默认yyyy-MM-dd HH:mm:ss
     * @param offset     月份偏移量
     * @return
     * @Author: l00211629
     */
    public static String addMonth(String dateStr, String oriPattern, String desPattern, int offset) {
        if (dateStr == null || "".equals(dateStr)) {
            return "";
        }
        /* 默认的日期格式化前后的格式 */
        if (oriPattern == null || "".equals(oriPattern)) {
            oriPattern = "yyyyMMddHHmmss".substring(0, dateStr.length());
        }
        if (desPattern == null || "".equals(desPattern)) {
            desPattern = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(oriPattern);
        try {
            Date date = sdf.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            //增加月份
            c.add(Calendar.MONTH, offset);
            SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(desPattern);
            return simpleDateTimeFormat.format(c.getTime());

        }
        catch (ParseException e) {
            e.printStackTrace();
            //异常时,原值返回
            return dateStr;
        }
    }

    /**
     * 按默认格式将日期字符串转为指定格式的日期字符串
     *
     * @param dateStr 日期字符串
     * @return
     * @Author: l90080613
     */
    public static String formateDate(String dateStr) {
        return DateUtil.formateDate(dateStr, "", "");
    }

    /**
     * 对日期字符串的月份进行偏移。日期格式按默认的格式
     *
     * @param dateStr 日期字符串
     * @return
     * @Author: l90080613
     */
    public static String addMonth(String dateStr, int offset) {
        return DateUtil.addMonth(dateStr, "", "", offset);
    }

    /**
     * 获取当前时间串，格式 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = new Date(System.currentTimeMillis());
        return sdf.format(d);
    }

    /**
     * 根据传入的时间格式获取当前日期字符串
     *
     * @return
     */
    public static String getFormateNowTime(String pattern) {
        if (pattern == null || "".equals(pattern)) {
            return "";
        }
        //获取当前请求时间
        Date dateTime = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(dateTime);
    }

    /**
     * 根据传人的yyyyMM取当前月的最后一天yyyyMMdd
     *
     * @param yyyyMM 日期字符串
     * @return 返回目标格式的日期字符串
     * @Author: 85810  兰平雄
     */
    public static String getLastDayOfMonth(String yyyyMM) {
        if (yyyyMM == null || "".equals(yyyyMM)) {
            return "";
        }
        if (yyyyMM.length() != 6) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        // 设置传入的时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        // 指定一个日期
        Date date = null;
        try {
            date = dateFormat.parse(yyyyMM);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        // 对 calendar 设置为 date 所定的日期
        cal.setTime(date);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(cal.getTime());
    }

    /**
     * 返回指定时间HH:mm:ss的毫秒数
     *
     * @param time
     * @return
     */
    public static long getTimeMillis(String time) {
        try {
            SimpleDateFormat dateSdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            SimpleDateFormat daySdf = new SimpleDateFormat("yy-MM-dd");
            Date currentDate = dateSdf.parse(daySdf.format(new Date()) + " " + time);
            return currentDate.getTime();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将指定月份往前推几个月的时间
     *
     * @param dateStr 指定时间
     * @param month   往前推的月份
     * @return 上某月的时间 格式 yyyyMM
     */
    public static String beforeMonth(String dateStr, int month) {
        String lastMonth = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date sourceDate;
        if (null == dateStr || "".equals(dateStr)) {
            return "";
        }
        try {
            sourceDate = sdf.parse(dateStr);

            Calendar cal = Calendar.getInstance();
            cal.setTime(sourceDate);
            cal.add(Calendar.MONTH, -month);

            lastMonth = sdf.format(cal.getTime());
        }
        catch (ParseException e) {
            log.error("", e);
        }
        return lastMonth;
    }

}
