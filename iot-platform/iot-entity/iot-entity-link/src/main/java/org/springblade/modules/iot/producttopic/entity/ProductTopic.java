package org.springblade.modules.iot.producttopic.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 实体类
 * 产品Topic信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_product_topic", comment = "ProductTopic table")
public class ProductTopic extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 产品标识
     */
    @AutoColumn(value = "product_identification", comment = "产品标识")
    private String productIdentification;

    /**
     * 功能类型
     */
    @AutoColumn(value = "function_type", comment = "功能类型")
    private Integer functionType;

    /**
     * Topic类型(0:基础Topic,1:自定义Topic)
     */
    @AutoColumn(value = "topic_type", comment = "Topic类型(0:基础Topic,1:自定义Topic)")
    private Integer topicType;
    /**
     * topic
     */
    @AutoColumn(value = "topic", comment = "topic")
    private String topic;
    /**
     * 发布者
     */
    @AutoColumn(value = "publisher", comment = "发布者")
    private Integer publisher;
    /**
     * 订阅者
     */
    @AutoColumn(value = "subscriber", comment = "订阅者")
    private Integer subscriber;
    /**
     * 备注
     */
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;

    /**
     * 逻辑删除标识:0-未删除 1-已删除
     */
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;

}
