package org.springblade.modules.iot.vo.result.server;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springblade.basic.utils.ArithUtil;
import org.springblade.basic.utils.DateUtils;

import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * JVM相关信息
 *
 * @author mqttsnet
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
@Schema(title = "JvmResultVO", description = "JVM相关信息")
public class JvmResultVO {
    @Schema(description = "当前JVM占用的内存总数(M)")
    private double total;

    @Schema(description = "JVM最大可用内存总数(M)")
    private double max;

    @Schema(description = "JVM空闲内存(M)")
    private double free;

    @Schema(description = "JDK版本")
    private String version;

    @Schema(description = "JDK路径")
    private String home;

    public double getTotal() {
        return ArithUtil.div(total, (1024 * 1024), 2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getMax() {
        return ArithUtil.div(max, (1024 * 1024), 2);
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getFree() {
        return ArithUtil.div(free, (1024 * 1024), 2);
    }

    public void setFree(double free) {
        this.free = free;
    }

    public double getUsed() {
        return ArithUtil.div(total - free, (1024 * 1024), 2);
    }

    public double getUsage() {
        return ArithUtil.mul(ArithUtil.div(total - free, total, 4), 100);
    }

    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        return DateUtils.formatAsDateTime(DateUtils.getServerStartDate());
    }


    /**
     * JDK运行时间
     */
    public String getRunTime() {
        return DateUtils.getDatePoor(new Date(), DateUtils.getServerStartDate());
    }


    /**
     * 运行参数
     */
    public String getInputArgs() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().toString();
    }
}
