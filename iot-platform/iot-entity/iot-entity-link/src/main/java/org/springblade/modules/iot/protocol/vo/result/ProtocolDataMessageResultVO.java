package org.springblade.modules.iot.protocol.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;

/**
 * @program: iot-platform
 * @description: 协议数据内容返回VO
 * @packagename: org.springblade.modules.iot.device.vo.result
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-22 15:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Schema(title = "ProtocolDataMessageResultVO", description = "协议数据内容返回VO")
public class ProtocolDataMessageResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "消息头部")
    private Head head;

    @Schema(description = "报文体")
    private T dataBody;

    @Schema(description = "数据签名")
    private String dataSign;

    @Schema(description = "消息头部实体类")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Head {
        @Schema(description = "加密标志，0-不加密；1-SM4；2-AES", allowableValues = "0, 1, 2", example = "0")
        private Integer cipherFlag;

        @Schema(description = "消息ID（从1自增即可）", example = "3342")
        private Long mid;

        @Schema(description = "报文发送时间戳（毫秒）", example = "1624982406963")
        private Long timeStamp;
    }
}
