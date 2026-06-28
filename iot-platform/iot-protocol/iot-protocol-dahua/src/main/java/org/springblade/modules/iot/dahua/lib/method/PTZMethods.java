package org.springblade.modules.iot.dahua.lib.method;

import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.structure.*;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 云台控制相关方法
 */
public interface PTZMethods extends NetSDKLib {

    //获取人脸信息
    public boolean CLIENT_DoFindFaceInfo(LLong lFindHandle, NET_IN_FACEINFO_DO_FIND pstIn, NET_OUT_FACEINFO_DO_FIND pstOut, int nWaitTime);

    //停止查询人脸信息
    public boolean CLIENT_StopFindFaceInfo(LLong lFindHandle);

    /***********************************************************************************
     *						                     诱导屏相关接口									  *
     **********************************************************************************/
    // 设置诱导屏配置信息接口
    public boolean CLIENT_SetGuideScreenCfg(LLong lLoginID, NET_IN_SET_GUIDESCREEN_CFG pInParam, NET_OUT_SET_GUIDESCREEN_CFG pstOutPqram, int nWaitTime);
}
