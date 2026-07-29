package org.springblade.modules.iot.msg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.base.service.SuperService;
import org.springblade.modules.iot.msg.entity.ExtendNotice;
import org.springblade.modules.iot.msg.vo.query.ExtendNoticePageQuery;
import org.springblade.modules.iot.msg.vo.result.ExtendNoticeResultVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 通知表
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet]
 */
public interface ExtendNoticeService extends SuperService<Long, ExtendNotice> {

    /**
     * 分页查询
     *
     * @param page
     * @param params
     * @return
     */
    IPage<ExtendNoticeResultVO> page(IPage<ExtendNoticeResultVO> page, PageParams<ExtendNoticePageQuery> params);

    /**
     * 标记 已读
     *
     * @param noticeIds
     * @param employeeId
     * @return
     */
    Boolean mark(List<Long> noticeIds, Long employeeId);

    /**
     * 删除我的通知
     *
     * @param noticeIds
     * @return
     */
    Boolean deleteMyNotice(List<Long> noticeIds);
}


