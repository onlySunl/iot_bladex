package org.springblade.modules.iot.vo.query;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.codec.Base64;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: thinglinks-cloud
 * @description: MQTT 发送消息VO
 * @packagename: org.springblade.modules.iot.mqtt.vo
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-20 19:11
 **/
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "PublishMessageRequestVO", description = "MQTT 发送消息VO")
public class PublishMessageRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "可选的调用者提供的请求ID", example = "1234567890")
    private Long reqId;

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID", required = true, example = "thinglinks")
    private String tenantId;

    @NotBlank(message = "消息主题不能为空")
    @Schema(description = "消息主题", required = true, example = "exampleTopic")
    private String topic;

    @NotBlank(message = "QoS等级不能为空")
    @Schema(description = "QoS of the message to be published", required = true, example = "1")
    private String qos;

    @Schema(description = "消息过期秒数", example = "3600")
    private String expirySeconds;

    @Schema(description = "发布者类型", required = true, example = "web")
    private String clientType;

    @Schema(description = "消息负载（支持普通字符串或Base64编码字符串）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String payload;

    @Schema(description = "是否强制Base64解码（默认自动检测）", example = "false")
    private Boolean forceBase64Decode = false;

    /**
     * 智能获取负载作为字节数组
     * - 如果是Base64格式，则解码
     * - 如果是普通字符串，则按UTF-8编码
     */
    public byte[] getPayloadAsBytes() {
        if (payload == null) {
            return null;
        }

        if (forceBase64Decode || Base64.isBase64(payload)) {
            try {
                return Base64.decode(payload);
            } catch (Exception e) {
                log.warn("Base64解码失败，回退到字符串编码: {}", e.getMessage());
                // 解码失败时回退到字符串编码
                return payload.getBytes(StandardCharsets.UTF_8);
            }
        } else {
            // 普通字符串按UTF-8编码
            return payload.getBytes(StandardCharsets.UTF_8);
        }
    }

    /**
     * 智能获取负载作为字符串
     * - 如果是Base64格式，则解码后转为字符串
     * - 如果是普通字符串，则直接返回
     */
    public String getPayloadAsString() {
        if (payload == null) {
            return null;
        }

        if (forceBase64Decode || Base64.isBase64(payload)) {
            try {
                byte[] bytes = Base64.decode(payload);
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (Exception e) {
                log.warn("Base64解码失败，返回原始字符串: {}", e.getMessage());
                return payload;
            }
        } else {
            // 普通字符串直接返回
            return payload;
        }
    }

    /**
     * 设置负载数据（自动Base64编码）
     */
    public void setPayloadData(byte[] data) {
        if (data == null) {
            this.payload = null;
            return;
        }
        this.payload = Base64.encode(data);
        this.forceBase64Decode = true;
    }

    /**
     * 设置负载数据（可选择是否Base64编码）
     */
    public void setPayloadData(String data, boolean encodeAsBase64) {
        if (data == null) {
            this.payload = null;
            return;
        }

        if (encodeAsBase64) {
            this.payload = Base64.encode(data);
            this.forceBase64Decode = true;
        } else {
            this.payload = data;
            this.forceBase64Decode = false;
        }
    }

    /**
     * 设置负载数据（自动检测是否编码）
     */
    public void setPayloadData(String data) {
        if (data == null) {
            this.payload = null;
            return;
        }

        // 如果已经是Base64格式，直接使用
        if (Base64.isBase64(data)) {
            this.payload = data;
            this.forceBase64Decode = true;
        } else {
            this.payload = data;
            this.forceBase64Decode = false;
        }
    }
}
