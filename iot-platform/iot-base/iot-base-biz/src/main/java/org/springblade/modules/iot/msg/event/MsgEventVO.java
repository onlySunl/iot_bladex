package org.springblade.modules.iot.msg.event;

import org.springblade.model.vo.BaseEventVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author mqttsnet
 * @version v1.0
 * @date 2022/7/29 10:08 PM
 * @create [2022/7/29 10:08 PM ] [mqttsnet] [初始创建]
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MsgEventVO extends BaseEventVO {
    Long msgId;
}
