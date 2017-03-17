package org.chenll.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * Created by chenlile on 17-3-9.
 * 日期时间计算工具
 * 利用joda类库
 */
public class DateTimeUtils {

    private static final String DEFAULTPATTER="yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        System.err.println("show:"+date2Str(getDateAfterWeek(new Date(),2),"yyyy/MM/dd"));
        System.err.println("show:"+str2Date("2012-12-12 12:25:30",DEFAULTPATTER));
    }

    public static Date str2Date(String dateStr,String pattern){
        if(StringUtils.isBlank(pattern)){
            return DateTime.parse(dateStr, DateTimeFormat.forPattern(DEFAULTPATTER)).toDate();
        }
        return DateTime.parse(dateStr, DateTimeFormat.forPattern(pattern)).toDate();
    }

    public static String date2Str(Date date,String pattern){
        if(StringUtils.isBlank(pattern)){
            return new DateTime(date).toString(DEFAULTPATTER);
        }
        return new DateTime(date).toString(pattern);
    }

    //计算参数日期上个月的最后一天
    public static String getLastMothDay(Date date){
        DateTime dt = new DateTime(date);
        return dt.minusMonths(1).dayOfMonth().withMaximumValue().toString();
    }

    /**
     * 计算当前日期加上几个星期的日期
     * @param date  参数日期
     * @param numWeek  几个星期的个数，比如2周，就填2
     * @return
     */
    public static Date getDateAfterWeek(Date date,int numWeek){
        DateTime dt = new DateTime(date);
        return dt.plusWeeks(2).toDate();
    }

    /**
     * 获取加n秒后的日期
     * @param date
     * @param seconds
     * @return
     */
    public static Date getDateAfterSeconds(Date date,int seconds){
        DateTime dt = new DateTime(date);
        return dt.plusSeconds(seconds).toDate();
    }
    /**
     * 获取某月第一天
     *
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        DateTime first = dateTime.dayOfMonth().withMinimumValue();
        first = first.withTime(0, 0, 0, 0);
        return first.toDate();

    }
}
