package org.springblade.modules.iot.protocol.vo.result;

import cn.hutool.core.map.MapUtil;
import com.mqttsnet.basic.interfaces.echo.EchoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * Description:
 *
 * @author Sun ShiHuan
 * @version 1.0.0
 * @since 2025/6/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(title = "DeviceAclCheckResultVO", description = "设备认证结果")
public class DeviceAclCheckResultVO implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 是否允许操作
     */
    private Boolean allowed;

    /**
     * 错误信息（当不允许时）
     */
    private String errorMessage;}
