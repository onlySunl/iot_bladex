package org.springblade.core.condition.constant;


import org.springblade.basic.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public final class DefaultValueConstant {
    public static final Date NULL_DATE = DateUtils.parse("9999-12-31", DateUtils.DEFAULT_DATE_FORMAT);
    public static final Date NULL_DATE_TIME = DateUtils.parseDatetime("9999-12-31 00:00:00");
    public static final String STRING = "";
    public static final Boolean BOOLEAN = Boolean.FALSE;
    public static final long LONG = 0L;
    public static final int INTEGER = 0;
    public static final BigDecimal BIG_DECIMAL = new BigDecimal("0");
    public static final Date DATE = new Date(NULL_DATE.getTime());
    public static final Date DATE_TIME = new Date(NULL_DATE_TIME.getTime());
    public static final int SINGLE_JAVA_ENUM = -1;
    public static final String MULTIPLE_JAVA_ENUM = "";
    private DefaultValueConstant() {
    }
}
