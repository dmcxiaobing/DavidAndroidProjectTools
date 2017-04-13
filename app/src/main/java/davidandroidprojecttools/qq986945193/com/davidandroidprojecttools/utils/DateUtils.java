package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.enums.DateStyle;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.enums.Week;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public class DateUtils {

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    private static final Object object = new Object();

    /**
     * 获取SimpleDateFormat
     *
     * @param pattern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    public static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return 数值
     */
    public static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期字符串
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期字符串
     */
    public static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = DateToString(myDate, dateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    public static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps 时间long集合
     * @return 日期
     */
    public static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = {timestamps.get(i), timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
                // 因此不能将minAbsoluteValue取默认值0
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    minAbsoluteValue = absoluteValues.get(0);
                    for (int i = 1; i < absoluteValues.size(); i++) {
                        if (minAbsoluteValue > absoluteValues.get(i)) {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);

                    long dateOne = timestampsLastTmp[0];
                    long dateTwo = timestampsLastTmp[1];
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne : dateTwo;
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (getDateStyle(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 获取日期字符串的日期风格。失敗返回null。
     *
     * @param date 日期字符串
     * @return 日期风格
     */
    public static DateStyle getDateStyle(String date) {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyle style : DateStyle.values()) {
            if (style.isShowOnly()) {
                continue;
            }
            Date dateTmp = null;
            if (date != null) {
                try {
                    ParsePosition pos = new ParsePosition(0);
                    dateTmp = getDateFormat(style.getValue()).parse(date, pos);
                    if (pos.getIndex() != date.length()) {
                        dateTmp = null;
                    }
                } catch (Exception e) {
                }
            }
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        Date accurateDate = getAccurateDate(timestamps);
        if (accurateDate != null) {
            dateStyle = map.get(accurateDate.getTime());
        }
        return dateStyle;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date StringToDate(String date) {
        DateStyle dateStyle = getDateStyle(date);
        return StringToDate(date, dateStyle);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date    日期字符串
     * @param pattern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String pattern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(pattern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date      日期字符串
     * @param dateStyle 日期风格
     * @return 日期
     */
    public static Date StringToDate(String date, DateStyle dateStyle) {
        Date myDate = null;
        if (dateStyle != null) {
            myDate = StringToDate(date, dateStyle.getValue());
        }
        return myDate;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date      日期
     * @param dateStyle 日期风格
     * @return 日期字符串
     */
    public static String DateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        if (dateStyle != null) {
            dateString = DateToString(date, dateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date       旧日期字符串
     * @param newPattern 新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String newPattern) {
        DateStyle oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle newDateStyle) {
        DateStyle oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newDateStyle);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date        旧日期字符串
     * @param olddPattern 旧日期格式
     * @param newPattern  新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddPattern, String newPattern) {
        return DateToString(StringToDate(date, olddPattern), newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newParttern  新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle, String newParttern) {
        String dateString = null;
        if (olddDteStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(), newParttern);
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddPattern  旧日期格式
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddPattern, DateStyle newDateStyle) {
        String dateString = null;
        if (newDateStyle != null) {
            dateString = StringToString(date, olddPattern, newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
        String dateString = null;
        if (olddDteStyle != null && newDateStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date       日期字符串
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date       日期
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date         日期字符串
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date         日期
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date         日期字符串
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date         日期
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return getYear(StringToDate(date));
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        return getMonth(StringToDate(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return getDay(StringToDate(date));
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return getHour(StringToDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return getMinute(StringToDate(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return getSecond(StringToDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static String getDate(String date) {
        return StringToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return StringToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return DateToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期字符串
     * @return 星期
     */
    public static Week getWeek(String date) {
        Week week = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            week = getWeek(myDate);
        }
        return week;
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期
     * @return 星期
     */
    public static Week getWeek(Date date) {
        Week week = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
            case 0:
                week = Week.SUNDAY;
                break;
            case 1:
                week = Week.MONDAY;
                break;
            case 2:
                week = Week.TUESDAY;
                break;
            case 3:
                week = Week.WEDNESDAY;
                break;
            case 4:
                week = Week.THURSDAY;
                break;
            case 5:
                week = Week.FRIDAY;
                break;
            case 6:
                week = Week.SATURDAY;
                break;
        }
        return week;
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(String date, String otherDate) {
        return getIntervalDays(StringToDate(date), StringToDate(otherDate));
    }

    /**
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        int num = -1;
        Date dateTmp = DateUtils.StringToDate(DateUtils.getDate(date), DateStyle.YYYY_MM_DD);
        Date otherDateTmp = DateUtils.StringToDate(DateUtils.getDate(otherDate), DateStyle.YYYY_MM_DD);
        if (dateTmp != null && otherDateTmp != null) {
            long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
            num = (int) (time / (24 * 60 * 60 * 1000));
        }
        return num;
    }

    /**
     * 获取当前java.util.Date型日期
     *
     * @return java.util.Date() 当前日期
     */
    public static java.util.Date getCurrentJavaUtilDate() {

        return new java.util.Date();
    }

    /**
     * 按特定的日期格式获取当前字符串型日期
     *
     * @param dateFormatType String，日期格式<br>
     *                       几种日期格式和测试的结果<br>
     *                       "yyyy-MM-dd": 2012-08-02<br>
     *                       "yyyy-MM-dd hh:mm:ss": 2012-08-02 11:27:41<br>
     *                       "yyyy-MM-dd hh:mm:ss EE": 2012-08-02 11:27:41 星期四<br>
     *                       "yyyy年MM月dd日 hh:mm:ss EE": 2012年08月02日 11:27:41 星期四<br>
     * @return String 当前字符串型日期
     */
    public static String getCurrentStrDateBySpecifiedFormatType(
            String dateFormatType) {

        java.text.SimpleDateFormat simformat = new SimpleDateFormat(
                dateFormatType);
        return simformat.format(new java.util.Date());
    }

    /**
     * 判断今天是不是周末
     *
     * @return true/false
     */
    public static boolean isTodayWeekend() {

        Calendar c = Calendar.getInstance(); // 获取当前日期
        int day = c.get(Calendar.DAY_OF_WEEK); // 获取当前日期星期，英国算法(周日为一周第一天)
        if (day == 7 || day == 1) { // 如果是周六或周日就返回true
            return true;
        }
        return false;
    }

    /**
     * 获得间隔日期（主要是间隔N周、间隔N天）
     *
     * @param specifiedStrDate 指定日期
     * @param dateForamtType   指定日期格式
     * @param intervalNum      间隔数（周或者天）
     * @param calenderParam    指定修改日期格式的属性
     *                         Calendar.WEEK_OF_MONTH（周）或者Calendar.DAY_OF_MONTH（天）
     * @return
     */
    public static String getIntervalStrDate(String specifiedStrDate,
                                            String dateForamtType, int intervalNum, int calenderParam) throws ParseException {
        if (specifiedStrDate == null) {
            return null;
        }
        if (specifiedStrDate.trim().equals("")) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(dateForamtType);

        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.turnStrDateToJavaUtilDate(specifiedStrDate,
                dateForamtType));
        cal.add(calenderParam, intervalNum);
        return df.format(cal.getTime());
    }

    /**
     * 按照指定格式将字符串日期转换为SQL需要的日期对象
     *
     * @param strDate         String，欲转换的字符串型日期
     * @param dateFormateType String，指定的字符串日期格式
     * @return java.sql.Date 转换后的java.sql.Date型日期
     */
    public static java.sql.Date turnStrDateToJavaSqlDate(String strDate,
                                                         String dateFormateType) throws ParseException {

        if (strDate == null) {

            return null;
        }
        if (strDate.trim().equals("")) {

            return null;
        }

        return new java.sql.Date(turnStrDateToJavaUtilDate(strDate,
                dateFormateType).getTime());
    }

    /**
     * 判断两个字符串型日期是否指同一天
     *
     * @param strDate        字符串日期
     * @param anotherStrDate 另一个字符日期
     * @return boolean true/false
     */
    public static boolean isTwoStrDateTheSameDay(String strDate,
                                                 String anotherStrDate) throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date firstStrDate = null;
        Date secondStrDate = null;
        firstStrDate = df.parse(strDate);
        secondStrDate = df.parse(anotherStrDate);

        if (firstStrDate.getTime() > secondStrDate.getTime()) {

            return false;
        } else if (firstStrDate.getTime() < secondStrDate.getTime()) {

            return false;
        } else if (firstStrDate.getTime() == secondStrDate.getTime()) {

            return true;
        } else {

            return false;
        }
    }

    /**
     * 按指定的字符串格式将字符串型日期转化为java.util.Date型日期
     *
     * @param dateFormatType "yyyy-MM-dd" 或者 "yyyy-MM-dd hh:mm:ss"
     * @return java.util.Date型日期
     * @Param dateStr 字符型日期
     */
    public static java.util.Date turnStrDateToJavaUtilDate(String strDate,
                                                           String dateFormatType) throws ParseException {

        java.util.Date javaUtilDate = null;
        java.text.DateFormat df = new java.text.SimpleDateFormat(dateFormatType);
        if (strDate != null && (!"".equals(strDate)) && dateFormatType != null
                && (!"".equals(dateFormatType))) {
            javaUtilDate = df.parse(strDate);
        }
        return javaUtilDate;
    }

    /**
     * 将java.util.Date型日期转化指定格式的字符串型日期
     *
     * @param javaUtilDate   java.util.Date,传入的java.util.Date型日期
     * @param dateFormatType "yyyy-MM-dd"或者<br>
     *                       "yyyy-MM-dd hh:mm:ss EE"或者<br>
     *                       "yyyy年MM月dd日 hh:mm:ss EE" <br>
     *                       (年月日 时:分:秒 星期 ，注意MM/mm大小写)
     * @return String 指定格式的字符串型日期
     */
    public static String turnJavaUtilDateToStrDate(java.util.Date javaUtilDate,
                                                   String dateFormatType) {

        String strDate = "";
        if (javaUtilDate != null) {

            SimpleDateFormat sdf = new SimpleDateFormat(dateFormatType);
            strDate = sdf.format(javaUtilDate);
        }
        return strDate;
    }

    /**
     * 获取当年指定月份第一天的字符串日期
     *
     * @param specifiedMonth 指定月份
     * @param dateFormatType 日期格式
     * @return String 指定月份第一天的字符串日期
     */
    public static String getTheFirstDayOfSpecifiedMonth(int specifiedMonth,
                                                        String dateFormatType) {

        java.util.Date currentJavaUtilDate = getCurrentJavaUtilDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentJavaUtilDate);
        cal.set(Calendar.MONTH, specifiedMonth - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        java.util.Date resultDate = cal.getTime();
        String result = turnJavaUtilDateToStrDate(resultDate, dateFormatType);
        return result;
    }

    /**
     * 获取当年指定月份的最后一天字符串日期
     *
     * @param specifiedMonth 指定月份
     * @param dateFormatType 日期格式
     * @return String 时间字符串
     */
    public static String getTheLastDayOfSpecifiedMonth(int specifiedMonth,
                                                       String dateFormatType) throws ParseException {

        java.util.Date date = null;
        date = turnStrDateToJavaUtilDate(
                getTheFirstDayOfSpecifiedMonth(specifiedMonth, dateFormatType),
                dateFormatType);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String result = turnJavaUtilDateToStrDate(calendar.getTime(),
                "yyyy-MM-dd");
        return result;
    }

    /**
     * 获取当前月第一天的字符串日期
     *
     * @return String 当前月第一天的字符串日期。例如：当前日期是2012-08-2，则返回值为2012-08-1
     */
    public static String getTheFirstDayOfCurrentMonth() {

        Calendar cal = Calendar.getInstance();// 获取当前日期
        cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,即是本月第一天
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 格式化
        return df.format(cal.getTime());
    }

    /**
     * 获取当前月最后一天的字符串日期
     *
     * @return String 当前月最后一天的日期。 例如：当前日期是2012-08-2，则返回值为2012-08-31
     */
    public static String getTheLastDayOfCurrentMonth() {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//
        Calendar cal = Calendar.getInstance();// 获取当前日期，例如2012-08-02
        cal.set(Calendar.DAY_OF_MONTH, 1);// 转变为当前月的第一天，例如2012-08-01
        cal.add(Calendar.MONTH, 1);// 转变为下个月的第一天，例如2012-09-01
        cal.add(Calendar.DAY_OF_MONTH, -1);// 下个月第一天，倒数一天，即为当前月的最后一天。例如2012-08-31
        return df.format(cal.getTime());
    }

    /**
     * 验证传入日期是否为当前月最后一天
     *
     * @param targetObj 传入日期可为字符串、java.util.Date
     * @param formtStr  yyyy-MM-dd hh:mm:ss
     * @return true/false
     */
    public static boolean isTheLastDayOfCurrentMonth(Object targetObj,
                                                     String formtStr) throws ParseException {

        boolean flag = false;
        if (targetObj == null) {// 如果传入日期参数为null，则返回false
            return flag;
        } else if ("".equals(targetObj.toString())) {// 如果传入日期参数为空字符串，则返回false
            return flag;
        }

        String srcDate = "";
        if (targetObj.getClass() == String.class) {
            srcDate = DateUtils.turnJavaUtilDateToStrDate(DateUtils
                    .turnStrDateToJavaUtilDate(targetObj.toString(),
                            "yyyy-MM-dd"), formtStr);
        } else if (targetObj.getClass() == Date.class) {
            srcDate = DateUtils.turnJavaUtilDateToStrDate((Date) targetObj,
                    formtStr);
        } else {
            srcDate = DateUtils.turnJavaUtilDateToStrDate(new Date(),
                    "yyyy-MM-dd");
        }
        if (srcDate.compareTo(DateUtils.getTheLastDayOfCurrentMonth()) == 0) {// 和当前月最后一天比较的结果为0，则是当前月最后一天
            flag = true;
            return flag;
        } else {
            return flag;
        }
    }

    /**
     * 获取当前时间16位字符串
     * 小富修改为时间16位+4位随机数2012091811320043154
     *
     * @return String e.g."2012082110290016"
     */
    public static String getCurrentDateTime16Str() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssss");

//        * 生成随机数 *
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = result * 10 + array[i];
        }

        return sdf.format(new Date()) + result;
    }


    /**
     * 指定日期、相加月数值、格式，得到相加日期
     * 例如：2011-06-19  2  yyyy-MM-dd 结果：2011-08-19
     * 2011-06-19 12  yyyy-MM-dd 结果：2012-06-19
     *
     * @param date     指定日期
     * @param formtStr 格式
     * @param number   数组
     * @param calender 指定修改日期格式数组
     * @return
     * @author leiyunshi
     */
    public static String tragetDate(String date, String formtStr, int number, int calender) {
        if (date == null) {
            return null;
        }

        if (date.trim().equals("")) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(formtStr);//"yyyy-MM-dd"

        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.toDate(date));
        cal.add(calender, number);
//      System.out.println(df.format(cal.getTime()));
        return df.format(cal.getTime());
    }

    /**
     * 按照"yyyy-MM-dd"格式将字符串date转换为日期对象
     *
     * @param date
     * @return
     * @author leiyunshi
     */
    public static java.util.Date toDate(String date) {
        if (date == null) {
            return null;
        }
        if (date.trim().equals("")) {
            return null;
        }
        java.text.SimpleDateFormat simformat = new SimpleDateFormat(
                "yyyy-MM-dd");
        try {
            return simformat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取得两个时间段的时间间隔
     *
     * @param t1 时间1
     * @param t2 时间2
     * @return t2 与t1的间隔年数
     * @throws ParseException 如果输入的日期格式不是0000-00-00 格式抛出异常
     * @author color
     */
    public static int getBetweenYears(String t1, String t2) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse(t1);
        Date d2 = format.parse(t2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(d1);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

        return betweenYears;
    }

    /**
     * 取得两个时间段的时间间隔
     *
     * @param t1 时间1
     * @param t2 时间2
     * @return t2 与t1的间隔天数
     * @throws ParseException 如果输入的日期格式不是0000-00-00 格式抛出异常
     * @author color
     */
    public static int getBetweenDays(String t1, String t2)
            throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int betweenDays = 0;
        Date d1 = format.parse(t1);
        Date d2 = format.parse(t2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(d1);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }

}
