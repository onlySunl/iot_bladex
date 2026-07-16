package org.springblade.modules.iot.framework.common.exception;

import lombok.Data;
import java.io.Serializable;

/**
 * ErrorCode adapter.
 */
@Data
public class ErrorCode implements Serializable {
    private final Integer code;
    private final String msg;

    public ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
