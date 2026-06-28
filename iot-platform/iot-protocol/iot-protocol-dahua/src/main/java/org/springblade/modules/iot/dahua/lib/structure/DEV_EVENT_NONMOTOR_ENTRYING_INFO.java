package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

public class DEV_EVENT_NONMOTOR_ENTRYING_INFO extends SdkStructure {
    public int             nChannelID;
    public int             nAction;
    public byte[]          szName = new byte[128];
    public double          PTS;
    public NET_TIME_EX    UTC;
    public int             nEventID;
    public int             nRuleID;
    public int             nSequence;
    public int             emClassType;
    public int             nObjectNum;
    public VA_OBJECT_NONMOTOR[] stuObjects = new VA_OBJECT_NONMOTOR[8];
    public SCENE_IMAGE_INFO_EX stuSceneImage;
    public NET_IMAGE_INFO_EX2[] stuImageInfo = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32);
    public int             nImageInfoNum;
    public byte[]          byReserved = new byte[1024];
}

