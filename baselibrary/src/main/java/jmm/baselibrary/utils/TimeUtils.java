package jmm.baselibrary.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils {

    public static String timeToString(String time) {
        long parseLong = 1;
        if (!TextUtils.isEmpty(time) && TextUtils.isDigitsOnly(time)) {
            parseLong = Long.parseLong(time) * 1000;
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(parseLong));
        }
        return time;
    }

    public static String timeToString2(String time) {
        if (!TextUtils.isEmpty(time) && TextUtils.isDigitsOnly(time)) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(time)));
        }
        return time;
    }

    /**
     * 日期格式字符串转换成时间戳
     *  @param date 字符串日期
     *  @return
     *
     */
    public static long date2TimeStamp(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            return sdf.parse(date).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }
}
