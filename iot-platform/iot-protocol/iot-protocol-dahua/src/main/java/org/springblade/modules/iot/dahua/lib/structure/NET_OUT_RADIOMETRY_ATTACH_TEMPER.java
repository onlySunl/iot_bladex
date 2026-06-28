package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_RadiometryAttachTemper 出参
*/
public class NET_OUT_RADIOMETRY_ATTACH_TEMPER extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_RADIOMETRY_ATTACH_TEMPER() {
        this.dwSize = this.size();
    }
}

