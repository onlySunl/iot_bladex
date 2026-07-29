package org.springblade.modules.iot.dinger.vo.param;

import org.springblade.modules.iot.dinger.enumeration.NotificationChannelType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @program: blade-cloud
 * @description: 通知渠道参数
 * @packagename: org.springblade.modules.iot.dinger.vo.param
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-09-11 00:30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Schema(title = "NotificationChannelParamVO", description = "通知渠道参数")
public class NotificationChannelParamVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "通知渠道不能为空")
    @Schema(description = "通知渠道", requiredMode = Schema.RequiredMode.REQUIRED)
    private NotificationChannelType channelType;

    @Schema(description = "需要@的人员列表")
    private List<String> atPersons;

    @Schema(description = "消息标题")
    private String title;

    @NotNull(message = "消息内容不能为空")
    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @Valid
    @Schema(description = "钉钉参数")
    private DingTalkParams dingTalkParams;

    @Valid
    @Schema(description = "企业微信参数")
    private WeWoekTalkParams weWoekTalkParams;

    // ... 其他的通用属性
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "钉钉参数")
class DingTalkParams {
    @NotNull(message = "Token ID不能为空")
    @Schema(description = "Token ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tokenId;

    @NotNull(message = "Secret不能为空")
    @Schema(description = "Secret", requiredMode = Schema.RequiredMode.REQUIRED)
    private String secret;

    @Schema(description = "需要@的人员列表")
    private List<String> atPersons;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "企业微信参数")
class WeWoekTalkParams {
    @NotNull(message = "Token ID不能为空")
    @Schema(description = "Token ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tokenId;

    @NotNull(message = "Secret不能为空")
    @Schema(description = "Secret", requiredMode = Schema.RequiredMode.REQUIRED)
    private String secret;

    @Schema(description = "需要@的人员列表")
    private List<String> atPersons;
}

