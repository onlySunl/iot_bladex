package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.Pointer;

/**
 * 场景图像信息扩展
 */
public class SCENE_IMAGE_INFO_EX extends SdkStructure {
    /**
     * 图像大小
     */
    public int dwImageSize;
    /**
     * 图像数据指针
     */
    public Pointer pImageData;
    /**
     * 图像宽度
     */
    public int nWidth;
    /**
     * 图像高度
     */
    public int nHeight;

    public SCENE_IMAGE_INFO_EX() {
    }
}
