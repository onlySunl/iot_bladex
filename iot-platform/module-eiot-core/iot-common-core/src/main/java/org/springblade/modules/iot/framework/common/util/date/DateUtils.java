package org.springblade.modules.iot.framework.common.util.date;

import java.time.format.DateTimeFormatter;

/**
 * DateUtils adapter.
 */
public class DateUtils {
    public static final String FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
}
