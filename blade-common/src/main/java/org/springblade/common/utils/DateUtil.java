package org.springblade.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil extends org.springblade.core.tool.utils.DateUtil {
    public static final String YYYYMMDD_FORMAT = "yyyyMMdd";
    public static final String YYYYMMDDHHMM_FORMAT = "yyyyMMddHHmm";
    public static final String CHINESE_DATETIME_FORMAT = "yyyy年MM月dd日 HH:mm:ss";
    public static final String CHINESE_DATETIME_FORMAT_LINE = "yyyy年MM月dd日 HH时mm分";

    public static final String HHMM_FORMAT = "HHmm";

    // ── 默认格式化器 ──
    public static final DateTimeFormatter FMT_STANDARD = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter FMT_DATE     = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FMT_TIME     = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter FMT_COMPACT  = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter FMT_ISO      = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public static final DateTimeFormatter YYYYMMDDHHMM_FORMATTER = DateTimeFormatter.ofPattern(YYYYMMDDHHMM_FORMAT);

    private DateUtil() {
        // 工具类，禁止实例化
    }

    // ═══════════════════════════════════════════
    //  Date → LocalDateTime / LocalDate
    // ═══════════════════════════════════════════

    /** Date → LocalDateTime（使用系统默认时区） */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /** Date → LocalDate（使用系统默认时区） */
    public static LocalDate toLocalDate(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /** Date → LocalTime（使用系统默认时区） */
    public static LocalTime toLocalTime(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    // ═══════════════════════════════════════════
    //  LocalDateTime / LocalDate → Date
    // ═══════════════════════════════════════════

    /** LocalDateTime → Date（使用系统默认时区） */
    public static Date toDate(LocalDateTime ldt) {
        if (ldt == null) return null;
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /** LocalDate → Date（以当天 00:00:00 为准，系统默认时区） */
    public static Date toDate(LocalDate ld) {
        if (ld == null) return null;
        return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /** LocalDateTime → Date（指定时区） */
    public static Date toDate(LocalDateTime ldt, ZoneId zone) {
        if (ldt == null) return null;
        return Date.from(ldt.atZone(zone != null ? zone : ZoneId.systemDefault()).toInstant());
    }

    // ═══════════════════════════════════════════
    //  毫秒时间戳 ↔ LocalDateTime
    // ═══════════════════════════════════════════

    /** 毫秒时间戳 → LocalDateTime */
    public static LocalDateTime toLocalDateTime(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /** LocalDateTime → 毫秒时间戳 */
    public static long toEpochMilli(LocalDateTime ldt) {
        if (ldt == null) return 0L;
        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // ═══════════════════════════════════════════
    //  字符串 ↔ LocalDateTime
    // ═══════════════════════════════════════════

    /** 字符串 → LocalDateTime（使用默认格式 yyyy-MM-dd HH:mm:ss） */
    public static LocalDateTime parse(String str) {
        if (str == null || str.isBlank()) return null;
        return LocalDateTime.parse(str, FMT_STANDARD);
    }

    /** 字符串 → LocalDateTime（指定格式） */
    public static LocalDateTime parse(String str, DateTimeFormatter fmt) {
        if (str == null || str.isBlank()) return null;
        return LocalDateTime.parse(str, fmt);
    }

    /** 字符串 → LocalDate（使用默认格式 yyyy-MM-dd） */
    public static LocalDate parseDate(String str) {
        if (str == null || str.isBlank()) return null;
        return LocalDate.parse(str, FMT_DATE);
    }

    /** LocalDateTime → 字符串（默认格式） */
    public static String format(LocalDateTime ldt) {
        if (ldt == null) return null;
        return ldt.format(FMT_STANDARD);
    }

    /** LocalDateTime → 字符串（指定格式） */
    public static String format(LocalDateTime ldt, DateTimeFormatter fmt) {
        if (ldt == null) return null;
        return ldt.format(fmt);
    }

    /** LocalDate → 字符串（默认格式 yyyy-MM-dd） */
    public static String format(LocalDate ld) {
        if (ld == null) return null;
        return ld.format(FMT_DATE);
    }

    /** LocalDateTime → 字符串（指定 pattern） */
    public static String format(LocalDateTime ldt, String pattern) {
        if (ldt == null) return null;
        return ldt.format(DateTimeFormatter.ofPattern(pattern));
    }

    // ═══════════════════════════════════════════
    //  便捷时间操作
    // ═══════════════════════════════════════════

    /** N 天前/后（负数往前） */
    public static LocalDateTime daysAgo(long days) {
        return LocalDateTime.now().minus(days, ChronoUnit.DAYS);
    }

    /** N 小时前/后 */
    public static LocalDateTime hoursAgo(long hours) {
        return LocalDateTime.now().minus(hours, ChronoUnit.HOURS);
    }

    /** 两个 LocalDateTime 相差毫秒 */
    public static long betweenMillis(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) return 0L;
        return Duration.between(start, end).toMillis();
    }

    /** 两个 LocalDateTime 相差天数 */
    public static long betweenDays(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) return 0L;
        return ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate());
    }

    // ═══════════════════════════════════════════
    //  兼容 DateUtils 方法（用于迁移）
    // ═══════════════════════════════════════════

    /** Date → LocalDateTime（兼容 DateUtils.date2LocalDateTime） */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return toLocalDateTime(date);
    }

    /** LocalDateTime → Date（兼容 DateUtils.localDateTime2Date） */
    public static Date localDateTime2Date(LocalDateTime ldt) {
        return toDate(ldt);
    }

    /** 获取当前毫秒时间戳（兼容 DateUtils.millisecondStampL） */
    public static long millisecondStampL() {
        return System.currentTimeMillis();
    }

    /** 解析日期时间字符串为 Date（兼容 DateUtils.parseDatetime） */
    public static Date parseDatetime(String str) {
        if (str == null || str.isBlank()) return null;
        LocalDateTime ldt = parse(str);
        return toDate(ldt);
    }

    /** 解析日期时间字符串为 Date（指定格式） */
    public static Date parseDatetime(String str, String pattern) {
        if (str == null || str.isBlank()) return null;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime ldt = LocalDateTime.parse(str, fmt);
        return toDate(ldt);
    }
}
