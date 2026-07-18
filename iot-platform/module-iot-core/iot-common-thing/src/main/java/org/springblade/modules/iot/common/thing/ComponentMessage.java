

package org.springblade.modules.iot.common.thing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComponentMessage {
    private String id;
    private String componentId; // 组件ID
    private String name; // 组件名称
    private String content; // 消息内容
    private String type; // 组件类型
    private Long time;

}
