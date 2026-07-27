package org.springblade.modules.iot.dashboard.enumeration;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * -----------------------------------------------------------------------------
 * File Name: TimeUnitEnum
 * -----------------------------------------------------------------------------
 * Description:
 * TimeUnitEnum
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2023/12/4       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023/12/4 00:20
 */
@Getter
@AllArgsConstructor
@Schema(title = "TimeUnitEnum", description = "Time Unit Enum")
public enum TimeUnitEnum {
    MINUTE("1m", "yyyyMMddHHmm", ChronoUnit.MINUTES) {
        @Override
        public String extractDateKey(String pattern) {
            return pattern.substring(0, 8);
        }

        @Override
        public String extractTimeField(String pattern) {
            return pattern.substring(8);
        }
    },
    HOUR("1h", "yyyyMMddHH", ChronoUnit.HOURS) {
        @Override
        public String extractDateKey(String pattern) {
            return pattern.substring(0, 8);
        }

        @Override
        public String extractTimeField(String pattern) {
            return pattern.substring(8);
        }
    },
    DAY("1d", "yyyyMMdd", ChronoUnit.DAYS) {
        @Override
        public String extractDateKey(String pattern) {
            return pattern;
        }

        @Override
        public String extractTimeField(String pattern) {
            return "";
        }
    };

    private final String code;
    private final String pattern;
    private final ChronoUnit chronoUnit;

    public abstract String extractDateKey(String pattern);

    public abstract String extractTimeField(String pattern);

    public static Optional<TimeUnitEnum> fromCode(String code) {
        return Arrays.stream(TimeUnitEnum.values())
                .filter(timeUnitEnum -> timeUnitEnum.getCode().equalsIgnoreCase(code))
                .findFirst();
    }
}