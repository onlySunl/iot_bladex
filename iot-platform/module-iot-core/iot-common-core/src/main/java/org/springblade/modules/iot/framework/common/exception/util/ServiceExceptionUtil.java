package org.springblade.modules.iot.framework.common.exception.util;

import org.springblade.modules.iot.framework.common.exception.ErrorCode;
import org.springblade.modules.iot.framework.common.exception.ServiceException;

/**
 * ServiceExceptionUtil adapter.
 */
public class ServiceExceptionUtil {
    public static ServiceException exception(ErrorCode errorCode) {
        return new ServiceException(errorCode);
    }

    public static ServiceException exception(ErrorCode errorCode, Object... params) {
        String msg = String.format(errorCode.getMsg(), params);
        return new ServiceException(errorCode.getCode(), msg);
    }
}
