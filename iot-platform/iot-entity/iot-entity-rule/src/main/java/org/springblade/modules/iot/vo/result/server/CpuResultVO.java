package org.springblade.modules.iot.vo.result.server;


import com.mqttsnet.basic.utils.ArithUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * CPU相关信息
 *
 * @author mqttsnet
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
@Schema(title = "CpuResultVO", description = "CPU相关信息")
public class CpuResultVO {

    @Schema(description = "核心数")
    private int cpuNum;

    @Schema(description = "CPU总的使用率")
    private double total;

    @Schema(description = "CPU系统使用率")
    private double sys;

    @Schema(description = "CPU用户使用率")
    private double used;

    @Schema(description = "CPU当前等待率")
    private double wait;

    @Schema(description = "CPU当前空闲率")
    private double free;

    public int getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(int cpuNum) {
        this.cpuNum = cpuNum;
    }

    public double getTotal() {
        return ArithUtil.round(ArithUtil.mul(total, 100), 2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSys() {
        return ArithUtil.round(ArithUtil.mul(sys / total, 100), 2);
    }

    public void setSys(double sys) {
        this.sys = sys;
    }

    public double getUsed() {
        return ArithUtil.round(ArithUtil.mul(used / total, 100), 2);
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public double getWait() {
        return ArithUtil.round(ArithUtil.mul(wait / total, 100), 2);
    }

    public void setWait(double wait) {
        this.wait = wait;
    }

    public double getFree() {
        return ArithUtil.round(ArithUtil.mul(free / total, 100), 2);
    }

    public void setFree(double free) {
        this.free = free;
    }
}
