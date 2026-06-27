package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import java.io.Serializable;

/**
 * 大华设备系统参数
 *
 * @author ruoyi
 */
public class DahuaSystemParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 视频制式
     * 0-PAL
     * 1-NTSC
     * 2-SECAM
     */
    private Integer videoStandard;

    /**
     * 国家/地区配置
     */
    private String country;

    public Integer getVideoStandard() {
        return videoStandard;
    }

    public void setVideoStandard(Integer videoStandard) {
        this.videoStandard = videoStandard;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
