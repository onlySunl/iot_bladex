package org.springblade.modules.iot.vo.result.server;


import com.mqttsnet.basic.utils.ArithUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 內存相关信息
 *
 * @author mqttsnet
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
@Schema(title = "MemResultVO", description = "内存相关信息")
public class MemResultVO {

    @Schema(description = "内存总量")
    private double total;

    @Schema(description = "已用内存")
    private double used;

    @Schema(description = "剩余内存")
    private double free;

    public double getTotal() {
        return ArithUtil.div(total, (1024 * 1024 * 1024), 2);
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public double getUsed() {
        return ArithUtil.div(used, (1024 * 1024 * 1024), 2);
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public double getFree() {
        return ArithUtil.div(free, (1024 * 1024 * 1024), 2);
    }

    public void setFree(long free) {
        this.free = free;
    }

    public double getUsage() {
        return ArithUtil.mul(ArithUtil.div(used, total, 4), 100);
    }
}
