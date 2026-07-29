package org.springblade.modules.iot.base.biz.common;

import com.mqttsnet.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.base.service.common.BaseDictService;
import org.springblade.modules.iot.system.entity.system.DefDict;
import org.springblade.modules.iot.system.service.system.DefDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典大业务层
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/10/9 3:31 下午
 * @create [2021/10/9 3:31 下午 ] [mqttsnet] [初始创建]
 */
@Service
@RequiredArgsConstructor
public class BaseDictBiz {
    private final DefDictService defDictService;
    private final BaseDictService baseDictService;

    public Boolean importDict(Long id) {
        DefDict defDict = defDictService.getById(id);
        ArgumentAssert.notNull(defDict, "系统字典不存在，请选择正确的字典进行导入");
        ArgumentAssert.isFalse(baseDictService.checkByKey(defDict.getKey(), null), "字典[{}]已存在，请勿重复导入", defDict.getKey());
        List<DefDict> itemList = defDictService.findItemByDictId(id);

        return baseDictService.saveDictAndItem(defDict, itemList);
    }
}
