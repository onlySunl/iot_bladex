package org.springblade.modules.iot.msg.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.common.iot.constant.DsConstant;
import org.springblade.core.database.mybatis.conditions.Wraps;
import org.springblade.core.mvc.request.PageParams;
import org.springblade.core.mvc.service.impl.SuperServiceImpl;
import org.springblade.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.msg.entity.ExtendNotice;
import org.springblade.modules.iot.msg.manager.ExtendNoticeManager;
import org.springblade.modules.iot.msg.service.ExtendNoticeService;
import org.springblade.modules.iot.msg.vo.query.ExtendNoticePageQuery;
import org.springblade.modules.iot.msg.vo.result.ExtendNoticeResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 业务实现类
 * 通知表
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExtendNoticeServiceImpl extends SuperServiceImpl<ExtendNoticeManager, Long, ExtendNotice> implements ExtendNoticeService {
    @Override
    public IPage<ExtendNoticeResultVO> page(IPage<ExtendNoticeResultVO> page, PageParams<ExtendNoticePageQuery> params) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean mark(List<Long> noticeIds, Long employeeId) {
        if (CollectionUtil.isEmpty(noticeIds) || employeeId == null) {
            return true;
        }

        return superManager.update(
                Wraps.<ExtendNotice>lbU().eq(ExtendNotice::getRecipientId, employeeId)
                        .in(ExtendNotice::getId, noticeIds)
                        .set(ExtendNotice::getIsRead, true)
                        .set(ExtendNotice::getReadTime, LocalDateTime.now())
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteMyNotice(List<Long> noticeIds) {
        ArgumentAssert.notEmpty(noticeIds, "请选择需要删除的消息");
        return superManager.removeByIds(noticeIds);
    }
}


