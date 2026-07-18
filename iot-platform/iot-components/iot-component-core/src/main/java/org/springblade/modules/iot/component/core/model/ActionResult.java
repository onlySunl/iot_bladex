

package org.springblade.modules.iot.component.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 动作执行结果
 *
 * @author sjg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionResult implements Serializable {

    /**
     * 状态码，0:成功，x:其它错误码
     */
    private int code;

    /**
     * 失败原因
     */
    private String reason;

}
