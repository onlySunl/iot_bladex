package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import lombok.Data;

/**
 * 海康ISUP预置点信息
 *
 * @FileName HaiKangIsupPresetInfo
 * @Description
 * @Author fengcheng
 * @date 2026-04-30
 **/
@Data
public class HaiKangIsupPresetInfo {
    public int index;
    public String name;

    public HaiKangIsupPresetInfo(int index, String name) {
        this.index = index;
        this.name = name;
    }
}
