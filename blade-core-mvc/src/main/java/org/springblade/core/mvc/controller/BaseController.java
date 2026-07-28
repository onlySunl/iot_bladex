package org.springblade.core.mvc.controller;

import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;

/**
 * 基础控制器
 *
 * @author Chill
 */
public class BaseController {

    /**
     * 成功响应
     */
    protected <T> R<T> success(T data) {
        return R.data(data);
    }

    /**
     * 成功响应
     */
    protected R<?> success() {
        return R.success("操作成功");
    }

    /**
     * 失败响应
     */
    protected R<?> fail(String message) {
        return R.fail(message);
    }

    /**
     * 判断是否为空
     */
    protected boolean isEmpty(String str) {
        return Func.isEmpty(str);
    }

    /**
     * 判断是否不为空
     */
    protected boolean isNotEmpty(String str) {
        return Func.isNotEmpty(str);
    }
}
