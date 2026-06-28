package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 消息物体结构
 */
public class NET_MSG_OBJECT extends SdkStructure {
    public int nObjectId;               // 物体ID
    public int nObjectType;              // 物体类型
    public byte[] szObjectType = new byte[128];  // 物体类型名称
    public int nConfidence;             // 置信度
    public int nAction;                  // 物体动作
    public NET_RECT stBoundingBox;       // 包围盒
    public NET_POINT stCenterPoint;     // 物体中心点
    public int nPolygonNum;             // 凸多边形顶点数
    public NET_POINT[] stConvexPolygon = new NET_POINT[16];  // 凸多边形
    public byte[] byReserved = new byte[128];  // 保留字段
    
    public NET_MSG_OBJECT() {
        for (int i = 0; i < stConvexPolygon.length; i++) {
            stConvexPolygon[i] = new NET_POINT();
        }
    }
}
