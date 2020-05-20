package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;


import android.text.format.Time;

import java.util.Calendar;
import java.util.TimeZone;

/*
 * @Author ：David
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class CalendarUtil {


    /**
     * 获取时分
     */
    public static String getHourMin() {
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        int mHour = mCalendar.get(Calendar.HOUR);
        int mMinuts = mCalendar.get(Calendar.MINUTE);
        String mMinString = null;
        if (mMinuts < 10) {
            mMinString = "0" + mMinuts;
        } else {
            mMinString = "" + mMinuts;
        }
        return mHour + ":" + mMinString;
    }


    /**
     * 获取时分
     */
    public static String getYearMonthDay() {
        Time time = new Time("GMT+8");
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int day = time.monthDay;
        return year + "." + month + "." + day;
    }

    /**
     * 获取星期几
     */
    public static String getCurrentWeek() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
//        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
//        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "日";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
//        return mYear + "年" + mMonth + "月" + mDay + "日" + "/星期" + mWay;
        return "星期" + mWay;

    }

//    public String getData2() {
//        Time time = new Time("GMT+8");
//        time.setToNow();
//        int year = time.year;
//        int month = time.month;
//        int day = time.monthDay;
//        int minute = time.minute;
//        int hour = time.hour + 8;
//        int sec = time.second;
//        String mMinString = null;
//        String secString = null;
//        if (minute < 10) {
//            mMinString = "0" + minute;
//        } else {
//            mMinString = "" + minute;
//        }
//
//        if (sec < 10) {
//            secString = "0" + sec;
//        } else {
//            secString = "" + sec;
//        }
//
//        String str1 = "��ǰʱ��Ϊ��" + year +
//                "�� " + month +
//                "�� " + day +
//                "�� " + hour +
//                ":" + mMinString +
//                ": " + secString;
//
//        return str1;
//    }
}
