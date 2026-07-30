package org.springblade.modules.iot.msg.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.core.annotation.echo.Echo;
import org.springblade.model.constant.EchoApi;
import org.springblade.model.constant.EchoDictType;
import org.springblade.model.vo.AuditableResultVO;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * <p>
 * 表单查询方法返回值VO
 * 接口执行日志记录
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-09 23:58:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "ExtendInterfaceLoggingResultVO", description = "接口执行日志记录")
public class ExtendInterfaceLoggingResultVO extends AuditableResultVO {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 接口日志ID;
     * #extend_interface_log
     */
    @Schema(description = "接口日志ID")
    private Long logId;
    /**
     * 执行时间
     */
    @Schema(description = "执行时间")
    private LocalDateTime execTime;
    /**
     * 执行状态;
     * [01-初始化 02-成功 03-失败]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_INTERFACE_LOGGING_STATUS)
     */

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String params;
    /**
     * 接口返回
     */
    @Schema(description = "接口返回")
    private String result;
    /**
     * 业务ID
     */
    @Schema(description = "业务ID")
    private Long bizId;

    @Schema(description = "异常信息")
    private String errorMsg;

}
