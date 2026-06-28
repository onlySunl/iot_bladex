package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 工作组包含的工作目录名称
*/
public class NET_DIRECTORIES_LIST_NAME extends SdkStructure
{
    /**
     * 名称
    */
    public byte[]           szName = new byte[256];

    public NET_DIRECTORIES_LIST_NAME() {
    }
}

