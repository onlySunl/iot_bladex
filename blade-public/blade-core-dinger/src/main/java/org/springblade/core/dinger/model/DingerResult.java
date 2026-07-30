package org.springblade.core.dinger.model;

import lombok.Data;

/**
 * 钉钉返回结果
 *
 * @author Chill
 */
@Data
public class DingerResult {

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误消息
     */
    private String errmsg;

    /**
     * 任务ID（工作通知）
     */
    private Long taskId;

    /**
     * 是否成功
     */
    public boolean isSuccess() {
        return errcode != null && errcode == 0;
    }

}
