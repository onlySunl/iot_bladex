package org.springblade.modules.iot.vo.result.server;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 系统文件相关信息
 *
 * @author mqttsnet
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
@Schema(title = "SysFileResultVO", description = "磁盘相关信息")
public class SysFileResultVO {

    @Schema(description = "盘符路径")
    private String dirName;

    @Schema(description = "盘符类型")
    private String sysTypeName;

    @Schema(description = "文件类型")
    private String typeName;

    @Schema(description = "总大小")
    private String total;

    @Schema(description = "剩余大小")
    private String free;

    @Schema(description = "已经使用量")
    private String used;

    @Schema(description = "资源的使用率")
    private double usage;

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getSysTypeName() {
        return sysTypeName;
    }

    public void setSysTypeName(String sysTypeName) {
        this.sysTypeName = sysTypeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }
}
