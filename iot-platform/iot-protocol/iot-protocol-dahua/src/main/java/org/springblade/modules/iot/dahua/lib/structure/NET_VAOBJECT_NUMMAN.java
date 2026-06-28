package org.springblade.modules.iot.dahua.lib.structure;



import org.springblade.modules.iot.dahua.lib.NET_RECT;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.Pointer;

/**
 * 检测到的人信息
 * @author 29779
 */
public class NET_VAOBJECT_NUMMAN extends SdkStructure {
    public int              nObjectID;                            // 物体ID，每个ID表示一个唯一的物体
	/**
	 * @link EM_UNIFORM_STYLE 制服样式 
	 */
    public int              emUniformStyle;
    public NET_RECT         stuBoundingBox;                       // 包围盒,手套对象在全景图中的框坐标,为0~8191相对坐标
    public NET_RECT         stuOriginalBoundingBox;               // 包围盒,绝对坐标
    /**
     * 排队时长
    */
    public long             nQueueDuration;
    /**
     * 人体属性信息,参见结构体定义 {@link HUMAN_ATTRIBUTES_INFO}
    */
    public Pointer          pstuHumanAttributes;
    /**
     * 人体属性信息扩展,参见结构体定义 {@link HUMAN_ATTRIBUTES_INFO_EX}
    */
    public Pointer          pstuHumanAttributesEx;
    /**
     * 人脸属性,参见结构体定义 {@link NET_FACE_ATTRIBUTE}
    */
    public Pointer          pstuFaceAttributes;
    public byte[]           byReserved = new byte[120-3*NetSDKConstants.POINTERSIZE]; // 预留字节
}

