package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 47081
 * @version 1.0
 * @description 增益设置
 * @date 2021/2/22
 */
public class CFG_THERMO_GAIN extends SdkStructure {
  /** 自动增益控制 [0-255]具体取值范围由能力决定 */
    public int              nAgc;
  /** 最大自动增益 [0-255]具体取值范围由能力决定 */
    public int              nAgcMaxGain;
  /** 增益均衡 具体取值范围由能力决定 */
    public int              nAgcPlateau;
}

