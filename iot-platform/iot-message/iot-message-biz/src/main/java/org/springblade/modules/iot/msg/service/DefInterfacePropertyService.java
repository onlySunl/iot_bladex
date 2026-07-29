package org.springblade.modules.iot.msg.service;

import com.mqttsnet.basic.base.service.SuperService;
import org.springblade.modules.iot.msg.entity.DefInterfaceProperty;
import org.springblade.modules.iot.msg.vo.save.DefInterfacePropertyBatchSaveVO;

import java.util.Map;


/**
 * <p>
 * 业务接口
 * 接口属性
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
public interface DefInterfacePropertyService extends SuperService<Long, DefInterfaceProperty> {
    /**
     * 根据接口ID查询接口属性参数
     *
     * @param id
     * @return
     */
    Map<String, Object> listByInterfaceId(Long id);

    /**
     * 批量保存
     *
     * @param saveVO
     * @return
     */
    Boolean batchSave(DefInterfacePropertyBatchSaveVO saveVO);
}


