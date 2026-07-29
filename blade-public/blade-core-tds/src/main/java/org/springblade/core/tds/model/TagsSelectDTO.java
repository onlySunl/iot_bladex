package org.springblade.core.tds.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @program: thinglinks
 * @description: 标签查询模型
 * @packagename: com.mqttsnet.thinglinks.tdengine.api.domain.rule
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2022-07-27 18:40
 **/
@Data
public class TagsSelectDTO {

    private String dataBaseName;

    @NotBlank(message = "invalid operation: stableName can not be empty")
    private String stableName;

    @NotBlank(message = "invalid operation: tagsName can not be empty")
    private String tagsName;

    private Long startTime;

    private Long endTime;

}
