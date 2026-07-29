package org.springblade.modules.iot.base.exception;

/**
 * 设备异常类
 *
 * @author Chill
 */
public class DeviceException extends IotException {

    private static final long serialVersionUID = 1L;

    public DeviceException(String message) {
        super(10001, message);
    }

    public DeviceException(Integer code, String message) {
        super(code, message);
    }

}
