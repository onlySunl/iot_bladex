package org.springblade.open.client.model;

import lombok.Data;

/**
 * 开放平台响应
 *
 * @author Chill
 */
@Data
public class OpenExpResponse<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 是否成功
     */
    public boolean isSuccess() {
        return code != null && code == 0;
    }

    /**
     * 创建成功响应
     */
    public static <T> OpenExpResponse<T> success(T data) {
        OpenExpResponse<T> response = new OpenExpResponse<>();
        response.setCode(0);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    /**
     * 创建失败响应
     */
    public static <T> OpenExpResponse<T> error(Integer code, String message) {
        OpenExpResponse<T> response = new OpenExpResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

}
