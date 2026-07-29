package org.springblade.modules.iot.entity.script;

import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.basic.base.entity.Entity;

import java.io.Serial;


/**
 * <p>
 * 实体类
 * 规则脚本表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-03-24 09:54:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_rule_groovy_script", comment = "RuleGroovyScript table")
public class RuleGroovyScript extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 脚本名称
     */
    
    @AutoColumn(value = "name", comment = "脚本名称")
    private String name;

    /**
     * 应用ID
     */
    
    @AutoColumn(value = "app_id", comment = "应用ID")
    private String appId;
    /**
     * 脚本类型
     */
    
    @AutoColumn(value = "script_type", comment = "脚本类型")
    private String scriptType;
    /**
     * 渠道编码
     */
    
    @AutoColumn(value = "channel_code", comment = "渠道编码")
    private String channelCode;
    /**
     * 产品标识
     */
    
    @AutoColumn(value = "product_identification", comment = "产品标识")
    private String productIdentification;
    /**
     * 主题模式
     */
    
    @AutoColumn(value = "topic_pattern", comment = "主题模式")
    private String topicPattern;
    /**
     * 是否启用 [0-禁用 1-启用]
     */
    
    @AutoColumn(value = "enable", comment = "是否启用 [0-禁用 1-启用]")
    private Boolean enable;
    /**
     * 脚本内容
     */
    
    @AutoColumn(value = "script_content", comment = "脚本内容")
    private String scriptContent;
    /**
     * 扩展信息
     */
    
    @AutoColumn(value = "extend_params", comment = "扩展信息")
    private String extendParams;
    /**
     * 版本号
     */
    
    @AutoColumn(value = "object_version", comment = "版本号")
    private String objectVersion;
    }
