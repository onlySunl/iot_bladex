package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_GetAtomsphData接口入参
*/
public class NET_IN_GET_ATOMSPHDATA extends SdkStructure
{
    public int              dwSize;

    public NET_IN_GET_ATOMSPHDATA() {
        this.dwSize = this.size();
    }
}

