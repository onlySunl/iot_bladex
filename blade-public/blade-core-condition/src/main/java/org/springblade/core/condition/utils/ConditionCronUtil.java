package org.springblade.core.condition.utils;

import cn.hutool.cron.pattern.CronPattern;

/**
 * -----------------------------------------------------------------------------
 * File Name: ConditionCronUtil.java
 * -----------------------------------------------------------------------------
 * Description:
 * Cron 常用工具类
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-10-23 16:08
 */
public class ConditionCronUtil {
    /**
     * Converts interval in seconds to its nearest representable CRON expression for Quartz.
     *
     * @param intervalSeconds Interval in seconds.
     * @return CRON expression representing the interval as close as possible.
     */
    public static String secondsToCron(int intervalSeconds) {
        if (intervalSeconds <= 0) {
            throw new IllegalArgumentException("Interval must be positive");
        }

        // Quartz supports specifying seconds, so we can directly create a CRON expression.
        if (intervalSeconds < 60) {
            return String.format("0/%d * * * * ?", intervalSeconds);
        }

        // For intervals in minutes, we approximate to the nearest minute.
        int intervalMinutes = intervalSeconds / 60;
        if (intervalMinutes < 60) {
            return String.format("0 0/%d * * * ?", intervalMinutes);
        }

        // For intervals in hours, we approximate to the nearest hour.
        int intervalHours = intervalMinutes / 60;
        return String.format("0 0 0/%d * * ?", intervalHours);
    }

    /**
     * Converts interval in minutes to its nearest representable CRON expression for Quartz.
     *
     * @param intervalMinutes Interval in minutes.
     * @return CRON expression representing the interval as close as possible.
     */
    public static String minutesToCron(int intervalMinutes) {
        return secondsToCron(intervalMinutes * 60);
    }

    /**
     * Converts interval in hours to its nearest representable CRON expression for Quartz.
     *
     * @param intervalHours Interval in hours.
     * @return CRON expression representing the interval as close as possible.
     */
    public static String hoursToCron(int intervalHours) {
        return minutesToCron(intervalHours * 60);
    }

    /**
     * This utility method is used to validate a given CRON expression.
     *
     * <p>
     * A CRON expression is a string representing a schedule in a time-based job-scheduling format.
     * </p>
     *
     * @param cron The CRON expression to be validated.
     * @return true if the CRON expression is valid, otherwise false.
     */
    public static boolean validateCronExpression(String cron) {
        try {
            // Attempt to parse the CRON expression. An exception will be thrown if it's invalid.
            CronPattern pattern = new CronPattern(cron);
            return true; // If parsing is successful, return true.
        } catch (Exception e) {
            return false; // If parsing fails, return false.
        }
    }


    public static void main(String[] args) {
        System.out.println(ConditionCronUtil.secondsToCron(18000));     // 0/45 * * * * ?
        System.out.println(ConditionCronUtil.minutesToCron(10));     // 0 0/10 * * * ?
        System.out.println(ConditionCronUtil.hoursToCron(2));        // 0 0 0/2 * * ?
    }
}
