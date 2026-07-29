package org.springblade.modules.iot.job.dto;

import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: JobReturnT.java
 * -----------------------------------------------------------------------------
 * Description:
 * Job result
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-10-23 18:39
 */
public class JobReturnT<T> implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    public static final JobReturnT<String> SUCCESS = new JobReturnT<String>(null);
    public static final JobReturnT<String> FAIL = new JobReturnT<String>(FAIL_CODE, null);

    private int code;
    private String msg;
    private T content;

    public JobReturnT() {
    }

    public JobReturnT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JobReturnT(T content) {
        this.code = SUCCESS_CODE;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReturnT [code=" + code + ", msg=" + msg + ", content=" + content + "]";
    }

}
