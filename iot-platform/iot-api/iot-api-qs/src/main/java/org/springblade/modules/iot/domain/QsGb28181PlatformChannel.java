package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import org.springblade.modules.iot.common.entity.CustomBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 国标GB28181平台通道关联对象 qs_gb28181_platform_channel
 *
 * @author ruoyi
 */
@Data
@TableName("qs_gb28181_platform_channel")
@EqualsAndHashCode(callSuper = true)
@Table(value = "qs_gb28181_platform_channel", comment = "国标28181平台通道表")
public class QsGb28181PlatformChannel extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */

    /**
     * 国标GB28181平台ID
     */
    private Long platformId;

    /**
     * 设备ID
     */
    private Long deviceId;
}
