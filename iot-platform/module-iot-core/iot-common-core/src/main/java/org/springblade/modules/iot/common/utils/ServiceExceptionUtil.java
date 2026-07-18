package org.springblade.modules.iot.common.utils;

import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.IResultCode;
import org.springblade.core.tool.api.ResultCode;
import org.springblade.modules.iot.common.constant.ErrorCode;
import org.springblade.modules.iot.common.constant.GlobalErrorCodeConstants;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * {@link ServiceException} 工具类
 * 使用 {} 占位符格式化消息，规避 String.format 百分号匹配异常
 * 适配框架原生 ServiceException 构造，统一携带 IResultCode 错误码
 */
@Slf4j
public class ServiceExceptionUtil {

    // ==================== 基于业务自定义 ErrorCode 抛异常 ====================
    public static ServiceException exception(ErrorCode errorCode) {
        return buildException(errorCode, null);
    }

    public static ServiceException exception(ErrorCode errorCode, Object... params) {
        String msg = doFormat(errorCode.getCode(), errorCode.getMsg(), params);
        // 包装临时IResultCode，替换原有msg
        IResultCode wrapCode = wrapResultCode(errorCode, msg);
        return buildException(wrapCode, null);
    }

    public static ServiceException exception(ErrorCode errorCode, Throwable cause, Object... params) {
        String msg = doFormat(errorCode.getCode(), errorCode.getMsg(), params);
        IResultCode wrapCode = wrapResultCode(errorCode, msg);
        return buildException(wrapCode, cause);
    }

    // ==================== 通用 IResultCode 兼容（全局错误码通用） ====================
    public static ServiceException exception(IResultCode resultCode) {
        return buildException(resultCode, null);
    }

    public static ServiceException exception(IResultCode resultCode, Object... params) {
        String msg = doFormat(resultCode.getCode(), resultCode.getMessage(), params);
        IResultCode wrapCode = wrapResultCode(resultCode, msg);
        return buildException(wrapCode, null);
    }

    public static ServiceException exception(IResultCode resultCode, Throwable cause, Object... params) {
        String msg = doFormat(resultCode.getCode(), resultCode.getMessage(), params);
        IResultCode wrapCode = wrapResultCode(resultCode, msg);
        return buildException(wrapCode, cause);
    }

    // ==================== 快捷参数非法异常 ====================
    public static ServiceException invalidParamException(String messagePattern, Object... params) {
        return exception(GlobalErrorCodeConstants.BAD_REQUEST, params);
    }

    public static ServiceException invalidParamException(Throwable cause, String messagePattern, Object... params) {
        return exception(GlobalErrorCodeConstants.BAD_REQUEST, cause, params);
    }

    // ==================== 底层构建异常统一入口 ====================
    private static ServiceException buildException(IResultCode code, Throwable cause) {
        if (cause == null) {
            return new ServiceException(code);
        } else {
            return new ServiceException(code, cause);
        }
    }

    /**
     * 包装IResultCode，替换格式化后的消息
     */
    private static IResultCode wrapResultCode(IResultCode origin, String newMsg) {
        return new IResultCode() {
            @Override
            public int getCode() {
                return origin.getCode();
            }

            @Override
            public String getMessage() {
                return newMsg;
            }
        };
    }

    // ==================== 高频业务断言工具 ====================
    public static void throwIfNull(Object obj, String msg, Object... params) {
        if (obj == null) {
            throw invalidParamException(msg, params);
        }
    }

    public static void throwIfBlank(String str, String msg, Object... params) {
        if (!StringUtils.hasText(str)) {
            throw invalidParamException(msg, params);
        }
    }

    public static void throwIfEmpty(Collection<?> coll, String msg, Object... params) {
        if (coll == null || coll.isEmpty()) {
            throw invalidParamException(msg, params);
        }
    }

    public static void throwIf(boolean condition, IResultCode code, Object... params) {
        if (condition) {
            throw exception(code, params);
        }
    }

    // ==================== {} 占位符格式化核心 ====================
    @VisibleForTesting
    public static String doFormat(int code, String messagePattern, Object... params) {
        if (!StringUtils.hasText(messagePattern)) {
            log.warn("[doFormat][消息模板为空：错误码({})|参数({})", code, params);
            return "";
        }
        if (params == null || params.length == 0) {
            return messagePattern;
        }

        StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);
        int i = 0;
        int j;
        for (int l = 0; l < params.length; l++) {
            j = messagePattern.indexOf("{}", i);
            if (j == -1) {
                log.error("[doFormat][参数过多：错误码({})|模板({})|参数({})", code, messagePattern, params);
                sbuf.append(messagePattern.substring(i));
                return sbuf.toString();
            }
            sbuf.append(messagePattern, i, j);
            sbuf.append(params[l]);
            i = j + 2;
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            log.error("[doFormat][参数过少：错误码({})|模板({})|参数({})", code, messagePattern, params);
        }
        sbuf.append(messagePattern.substring(i));
        return sbuf.toString();
    }

}