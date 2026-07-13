

package org.springblade.modules.iot.pojo.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 场景联动日志VO
 * @Author gitee.com/NexIoT
 * @since 2025-01-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SceneLinkageLogVO {

    /** 日志ID */
    private Long id;

    /** 业务ID */
    private String cId;

    /** 业务名称 */
    private String cName;

    /** 执行状态 */
    private Byte cStatus;

    /** 业务类型 */
    private Byte cType;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 创建者 */
    private String createBy;

    /** 内容 */
    private String content;

    /** 设备元数据 */
    private String cDeviceMeta;

    /** 场景名称 */
    private String sceneName;

    /** 触发条件 */
    private String touch;

    /** 触发条件JSON */
    private String triggerCondition;

    /** 执行动作JSON */
    private String execAction;

    /** 场景状态 */
    private Integer sceneStatus;

    /** 触发类型 */
    private String triggerType;
}
