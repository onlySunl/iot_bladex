package org.springblade.modules.iot.base.exception;

/**
 * 产品异常类
 *
 * @author Chill
 */
public class ProductException extends IotException {

    private static final long serialVersionUID = 1L;

    public ProductException(String message) {
        super(10002, message);
    }

    public ProductException(Integer code, String message) {
        super(code, message);
    }

}
