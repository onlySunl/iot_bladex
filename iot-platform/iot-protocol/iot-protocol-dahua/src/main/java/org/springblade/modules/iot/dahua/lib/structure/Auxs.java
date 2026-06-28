package org.springblade.modules.iot.dahua.lib.structure;/**
 * @author 47081
 * @descriptio
 * @date 2020/11/9
 * @version 1.0
 */


import static org.springblade.modules.iot.dahua.lib.constant.SDKStructureFieldLenth.CFG_COMMON_STRING_32;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * @author 47081
 * @version 1.0
 * @description
 * @date 2020/11/9
 */
public class Auxs extends SdkStructure {
    public byte[]           auxs = new byte[CFG_COMMON_STRING_32];
}

