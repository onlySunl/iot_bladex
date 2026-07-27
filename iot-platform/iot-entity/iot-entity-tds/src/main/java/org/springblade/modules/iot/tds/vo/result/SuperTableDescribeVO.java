package org.springblade.modules.iot.tds.vo.result;


import cn.hutool.core.map.MapUtil;
import com.mqttsnet.basic.interfaces.echo.EchoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;
import org.springblade.common.entity.CustomBaseEntity;

/**
 * @program: thinglinks-cloud
 * @description: 超级表结构VO
 * @packagename: org.springblade.modules.iot.tds.vo.result
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-09-17 21:12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "SuperTableDescribeVO", description = "超级表结构VO")
public class SuperTableDescribeVO extends CustomBaseEntity implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 标记
     */
    @Schema(description = "标记")
    private String note;
    /**
     * 字段名
     */
    @Schema(description = "字段名")
    private String field;
    /**
     * 字段长度
     */
    @Schema(description = "字段长度")
    private Integer length;
    /**
     * 字段类型
     */
    @Schema(description = "字段类型")
    private String type;

    @Override
    public Map<String, Object> getEchoMap() {
        return echoMap;
    }}
