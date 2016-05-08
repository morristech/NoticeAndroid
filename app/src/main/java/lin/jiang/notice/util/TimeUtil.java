package lin.jiang.notice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {

    private static final SimpleDateFormat UTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'", Locale.CHINA);
    private static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);

    public static final String MM_dd = "MM-dd";

    public static String convertUTC(String utc) {
        return convertUTC(utc, null);
    }

    public static String convertUTC(String utc, String partten) {
        SimpleDateFormat fmt = null;
        if (StringUtil.isBlank(partten)) {
            fmt = DEFAULT_SDF;
        } else {
            try {
                fmt = new SimpleDateFormat(partten, Locale.CANADA);
            } catch (IllegalArgumentException e) {
                fmt = DEFAULT_SDF;
            }
        }
        String convertTime;
        Date resultDate;
        long resultTime = 0;
        // 如果传入参数异常，使用本地时间
        if (StringUtil.isBlank(utc))
            resultTime = System.currentTimeMillis();
        else {
            try { // 将输入时间字串转换为UTC时间
                UTC.setTimeZone(TimeZone.getTimeZone("GMT00:00"));
                resultDate = UTC.parse(utc);
                resultTime = resultDate.getTime();
            } catch (Exception e) { // 出现异常时，使用本地时间
                resultTime = System.currentTimeMillis();
                fmt.setTimeZone(TimeZone.getDefault());
                convertTime = fmt.format(resultTime);
                return convertTime;
            }
        }
        // 设定时区
        fmt.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        convertTime = fmt.format(resultTime);
        return convertTime;
    }

    public static String formatToday(String utc) {
        String time = convertUTC(utc);
        if (isToday(time)) {
            return "今日";
        } else {
            return convertUTC(utc, MM_dd);
        }
    }

    public static boolean isJustNow(String utc) {
        String time = convertUTC(utc);
        try {
            long millis = DEFAULT_SDF.parse(time).getTime();
            long now = Calendar.getInstance(Locale.CHINA).getTime().getTime();
            return Math.abs(now - millis) <= 30 * 60 * 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isToday(String date) {
        try {
            DEFAULT_SDF.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        String today = DEFAULT_SDF.format(Calendar.getInstance(Locale.CHINA).getTime());
        return (date.split(" ")[0].equals(today.split(" ")[0]));
    }
}
