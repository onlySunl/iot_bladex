package org.springblade.modules.iot.framework.common.pojo;

import lombok.Data;
import java.io.Serializable;

/**
 * CommonResult adapter - wraps BladeX R for compatibility.
 */
@Data
public class CommonResult<T> implements Serializable {

    private Integer code;
    private T data;
    private String msg;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = 0;
        result.data = data;
        result.msg = "success";
        return result;
    }

    public static <T> CommonResult<T> success() {
        return success(null);
    }

    public static <T> CommonResult<T> error(Integer code, String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <T> CommonResult<T> error(String msg) {
        return error(500, msg);
    }

    public boolean isSuccess() {
        return this.code != null && this.code == 0;
    }
}
