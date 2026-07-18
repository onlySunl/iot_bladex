

package org.springblade.modules.iot.service.product;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelRespVO;
import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelSaveReqVO;
import org.springblade.modules.iot.convert.ShowModelConvert;
import org.springblade.modules.iot.entity.ShowModelDO;
import org.springblade.modules.iot.dal.mysql.showmodel.ShowModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;


/**
 * 产品显示模型 Service 实现类
 *
 * @author EnjoyIot
 */
@Service
@Validated
public class ShowModelServiceImpl implements ShowModelService {

    @Resource
    private ShowModelMapper showModelMapper;

    @Override
    public Long createShowModel(ShowModelSaveReqVO createReqVO) {
        // 插入
        ShowModelDO showModel = BeanUtils.toBean(createReqVO, ShowModelDO.class);
        showModelMapper.insert(showModel);
        // 返回
        return showModel.getId();
    }

    @Override
    public void saveShowModel(ShowModelSaveReqVO updateReqVO) {
        // 校验存在
        String productKey = updateReqVO.getProductKey();
        List<ShowModelRespVO> showModelByProductKey = getShowModelByProductKey(productKey);
        boolean exist = false;
        if (CollUtil.isNotEmpty(showModelByProductKey)) {
            for (ShowModelRespVO s : showModelByProductKey) {
                if (s.getTyp().equals(updateReqVO.getTyp())) {
                    exist = true;
                    break;
                }
            }
        }

        if (exist) {
            // 更新
            LambdaUpdateWrapper updateWrapper = new LambdaUpdateWrapper<ShowModelDO>().set(ShowModelDO::getCnf, updateReqVO.getCnf())
                    .eq(ShowModelDO::getProductKey, updateReqVO.getProductKey()).eq(ShowModelDO::getTyp, updateReqVO.getTyp());
            showModelMapper.update(null, updateWrapper);
        } else {
            ShowModelDO updateObj = BeanUtils.toBean(updateReqVO, ShowModelDO.class);

            showModelMapper.insert(updateObj);
        }

    }

    @Override
    public void deleteShowModel(Long id) {
        // 校验存在
        validateShowModelExists(id);
        // 删除
        showModelMapper.deleteById(id);
    }

    private void validateShowModelExists(Long id) {
        if (showModelMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.NOT_EXISTS);
        }
    }

    @Override
    public ShowModelRespVO getShowModel(Long id) {
        return ShowModelConvert.INSTANCE.convert(showModelMapper.selectById(id));
    }

    @Override
    public List<ShowModelRespVO> getShowModelByProductKey(String productKey) {
        return ShowModelConvert.INSTANCE.convertList(showModelMapper.selectList(ShowModelDO::getProductKey, productKey));
    }

}
