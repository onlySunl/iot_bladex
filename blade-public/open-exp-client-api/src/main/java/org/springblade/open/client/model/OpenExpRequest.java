package org.springblade.open.client.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 开放平台请求
 *
 * @author Chill
 */
@Data
public class OpenExpRequest {

    /**
     * 请求路径
     */
    private String path;

    /**
     * 请求方法
     */
    private String method = "GET";

    /**
     * 请求头
     */
    private Map<String, String> headers = new HashMap<>();

    /**
     * 请求参数
     */
    private Map<String, Object> params = new HashMap<>();

    /**
     * 请求体
     */
    private Object body;

    /**
     * 创建 GET 请求
     */
    public static OpenExpRequest get(String path) {
        OpenExpRequest request = new OpenExpRequest();
        request.setPath(path);
        request.setMethod("GET");
        return request;
    }

    /**
     * 创建 POST 请求
     */
    public static OpenExpRequest post(String path) {
        OpenExpRequest request = new OpenExpRequest();
        request.setPath(path);
        request.setMethod("POST");
        return request;
    }

    /**
     * 添加请求头
     */
    public OpenExpRequest header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    /**
     * 添加请求参数
     */
    public OpenExpRequest param(String key, Object value) {
        this.params.put(key, value);
        return this;
    }

    /**
     * 设置请求体
     */
    public OpenExpRequest body(Object body) {
        this.body = body;
        return this;
    }

}
