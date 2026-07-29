package org.springblade.basic.serialize;

import cn.hutool.core.util.NumberUtil;
import org.springblade.basic.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStrFormat extends SimpleDateFormat {

    private static final long serialVersionUID = -3903346237575186721L;

    @Override
    public Date parse(String dateStr) {
        try {
            if (NumberUtil.isNumber(dateStr)) {
                long dateLong = NumberUtil.parseLong(dateStr);
                return new Date(dateLong);
            }

            return super.parse(dateStr);
        } catch (Exception e) {
            return DateUtils.parseDates(dateStr);
        }
    }
}
