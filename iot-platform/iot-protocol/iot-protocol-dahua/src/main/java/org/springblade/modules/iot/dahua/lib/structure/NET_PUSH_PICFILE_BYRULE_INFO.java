package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;



/** 
* @author 291189
* @description  推送远程图片文件，添加任务时无规则和图片信息，通过推送图片接口，每张图片中带有不同的规则信息（目前能源场景中使用） 
* @date 2022/06/28 16:19:15
*/
public class NET_PUSH_PICFILE_BYRULE_INFO extends SdkStructure {
/** 
结构体大小
*/
    public			int            dwSize;
/** 
智能任务启动规则 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_ANALYSE_TASK_START_RULE}
*/
    public			int            emStartRule;
/** 
任务数据
*/
    public			byte[]         szTaskUserData = new byte[256];

public NET_PUSH_PICFILE_BYRULE_INFO(){
		this.dwSize=this.size();
}
}

