package org.springblade.modules.iot.vo.result.server;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 系统相关信息
 *
 * @author mqttsnet
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
@Schema(title = "SysResultVO", description = "系统相关信息")
public class SysResultVO {

    @Schema(description = "服务器名称")
    private String computerName;

    @Schema(description = "服务器IP")
    private String computerIp;

    @Schema(description = "项目路径")
    private String userDir;

    @Schema(description = "操作系统")
    private String osName;

    @Schema(description = "系统架构")
    private String osArch;

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getComputerIp() {
        return computerIp;
    }

    public void setComputerIp(String computerIp) {
        this.computerIp = computerIp;
    }

    public String getUserDir() {
        return userDir;
    }

    public void setUserDir(String userDir) {
        this.userDir = userDir;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }
}
