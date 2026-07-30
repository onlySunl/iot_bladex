package org.springblade.modules.iot.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.basic.base.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static org.springblade.modules.iot.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 业务附件
 * </p>
 *
 * @author mqttsnet
 * @date 2021-06-30
 * @create [2021-06-30] [mqttsnet] [初始创建]
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_appendix")
@AllArgsConstructor
public class Appendix extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;
    /**
     * 业务id
     */
    @TableField(value = "biz_id")
    private Long bizId;

    /**
     * 业务类型
     */
    @TableField(value = "biz_type", condition = LIKE)
    private String bizType;
    @TableField(value = "tenant_id")
    private Long tenantId;


    @Builder
    public Appendix(Long id, LocalDateTime createdTime, Long createdBy, Long bizId, String bizType, Long tenantId) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.bizId = bizId;
        this.bizType = bizType;
        this.tenantId = tenantId;
    }

}
