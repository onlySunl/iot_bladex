package org.springblade.modules.iot.sdkcore.common;

import com.alibaba.fastjson2.annotation.JSONField;
import org.springblade.modules.iot.sdkcore.sign.StringUtils;
import lombok.Data;

/**
 * @author 六如
 */
@Data
public class Result<T> {
    private String code;
    private String msg;
    private String subCode;
    private String subMsg;
    private String solution;
    private T data;

    @JSONField(serialize = false)
    public boolean isSuccess() {
        return StringUtils.isEmpty(subCode);
    }

}
