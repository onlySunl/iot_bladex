package org.springblade.core.databridge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发送结果
 *
 * @author Chill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendResult {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public static SendResult success() {
        return new SendResult(true, "success", null);
    }

    public static SendResult success(Object data) {
        return new SendResult(true, "success", data);
    }

    public static SendResult fail(String message) {
        return new SendResult(false, message, null);
    }
}
