package org.springblade.modules.iot.msg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import com.mqttsnet.basic.context.ContextConstants;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.jackson.JsonUtil;
import com.mqttsnet.basic.utils.ArgumentAssert;
import com.mqttsnet.basic.utils.SpringUtils;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.common.constant.JobConstant;
import org.springblade.modules.iot.job.dto.XxlJobInfoVO;
import org.springblade.modules.iot.job.facade.JobFacade;
import org.springblade.modules.iot.model.entity.system.SysUser;
import org.springblade.modules.iot.msg.entity.ExtendMsg;
import org.springblade.modules.iot.msg.entity.ExtendMsgRecipient;
import org.springblade.modules.iot.msg.entity.ExtendMsgTemplate;
import org.springblade.modules.iot.msg.entity.ExtendNotice;
import org.springblade.modules.iot.msg.enumeration.MsgTemplateTypeEnum;
import org.springblade.modules.iot.msg.enumeration.SourceType;
import org.springblade.modules.iot.msg.enumeration.TaskStatus;
import org.springblade.modules.iot.msg.event.MsgEventVO;
import org.springblade.modules.iot.msg.event.MsgSendEvent;
import org.springblade.modules.iot.msg.manager.ExtendMsgManager;
import org.springblade.modules.iot.msg.manager.ExtendMsgRecipientManager;
import org.springblade.modules.iot.msg.manager.ExtendNoticeManager;
import org.springblade.modules.iot.msg.service.ExtendMsgService;
import org.springblade.modules.iot.msg.vo.result.ExtendMsgResultVO;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgPublishVO;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgSendVO;
import org.springblade.modules.iot.msg.ws.WebSocketSubject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务实现类
 * 消息
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [mqttsnet]
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExtendMsgServiceImpl extends SuperServiceImpl<ExtendMsgManager, Long, ExtendMsg> implements ExtendMsgService {
    @Autowired
    private ExtendMsgRecipientManager extendMsgRecipientManager;
    @Autowired
    private ExtendNoticeManager extendNoticeManager;
    @Autowired
    private JobFacade jobFacde;

    @Override
    public ExtendMsgResultVO getResultById(Long id) {
        ExtendMsg msg = superManager.getById(id);
        ExtendMsgResultVO result = BeanUtil.toBean(msg, ExtendMsgResultVO.class);
        if (result != null) {
            List<ExtendMsgRecipient> list = extendMsgRecipientManager.listByMsgId(id);
            result.setRecipientList(list.stream().map(ExtendMsgRecipient::getRecipient).toList());
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean publish(ExtendMsgPublishVO data, SysUser sysUser) {
        ExtendMsg extendMsg = BeanUtil.toBean(data, ExtendMsg.class);
        extendMsg.setType(MsgTemplateTypeEnum.NOTICE.getCode());
        extendMsg.setChannel(SourceType.APP);

        extendMsg.setCreatedOrgId((sysUser != null && sysUser.getEmployee() != null) ? sysUser.getEmployee().getLastDeptId() : null);
        if (data != null && data.getDraft() != null && data.getDraft()) {
            extendMsg.setStatus(TaskStatus.DRAFT);
        } else {
            extendMsg.setStatus(TaskStatus.WAITING);
        }
        if (extendMsg.getId() == null) {
            superManager.save(extendMsg);
        } else {
            superManager.updateById(extendMsg);
            extendMsgRecipientManager.remove(Wraps.<ExtendMsgRecipient>lbQ().eq(ExtendMsgRecipient::getMsgId, extendMsg.getId()));
        }
        List<ExtendMsgRecipient> recipientList = data.getRecipientList().stream().map((item) -> {
            ExtendMsgRecipient recipient = new ExtendMsgRecipient();
            recipient.setMsgId(extendMsg.getId());
            recipient.setRecipient(item);
            return recipient;
        }).toList();
        extendMsgRecipientManager.saveBatch(recipientList);

        if (data.getSendTime() == null) {
            List<ExtendNotice> noticeList = data.getRecipientList().stream().map((item) -> {
                ExtendNotice notice = new ExtendNotice();
                BeanUtil.copyProperties(extendMsg, notice);
                notice.setId(null);
                notice.setMsgId(extendMsg.getId());
                notice.setRecipientId(Long.valueOf(item));
                notice.setIsHandle(false);
                notice.setIsRead(false);
                notice.setHandleTime(null);
                notice.setReadTime(null);
                notice.setUrl(data.getUrl());
                notice.setTarget(data.getTarget());
                notice.setAutoRead(data.getAutoRead() == null ? true : data.getAutoRead());
                return notice;
            }).toList();
            extendNoticeManager.saveBatch(noticeList);

            data.getRecipientList().forEach(employeeId -> {
                WebSocketSubject subject = WebSocketSubject.Holder.getSubject(employeeId);
                // 通知客户端 接收消息
                if (subject != null) {
                    subject.notify("1", null);
                }
            });

            extendMsg.setStatus(TaskStatus.SUCCESS);
            superManager.updateById(extendMsg);
        } else {
            // 延时消息任务由 thinglinks-base-executor 模块执行。
            Map<String, Long> param = MapUtil.builder(ContextConstants.TENANT_ID_HEADER, ContextUtil.getTenantId()).put("msgId", extendMsg.getId()).build();

            XxlJobInfoVO xxlJobInfoVO = XxlJobInfoVO.create(JobConstant.DEF_BASE_JOB_GROUP_NAME,
                    "【发送消息】" + extendMsg.getTitle(), extendMsg.getSendTime(), JobConstant.PUBLISH_MSG_JOB_HANDLER, JsonUtil.toJson(param));
            jobFacde.addTimingTask(xxlJobInfoVO);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishNotice(Long msgId) {
        ExtendMsg extendMsg = superManager.getById(msgId);
        ArgumentAssert.notNull(extendMsg, "消息不存在");
        List<ExtendMsgRecipient> recipientList = extendMsgRecipientManager.listByMsgId(extendMsg.getId());
        ArgumentAssert.notEmpty(recipientList, "消息接收人为空");

        List<ExtendNotice> noticeList = recipientList.stream().map((item) -> {
            ExtendNotice notice = new ExtendNotice();
            BeanUtil.copyProperties(extendMsg, notice);
            notice.setId(null);
            notice.setMsgId(extendMsg.getId());
            notice.setRecipientId(Long.valueOf(item.getRecipient()));
            notice.setIsHandle(false);
            notice.setIsRead(false);
            notice.setHandleTime(null);
            notice.setReadTime(null);
            notice.setAutoRead(true);
            return notice;
        }).toList();
        extendNoticeManager.saveBatch(noticeList);

        recipientList.forEach(employeeId -> {
            WebSocketSubject subject = WebSocketSubject.Holder.getSubject(employeeId);
            // 通知客户端 接收消息
            if (subject != null) {
                subject.notify("1", null);
            }
        });

        extendMsg.setStatus(TaskStatus.SUCCESS);
        superManager.updateById(extendMsg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean send(ExtendMsgSendVO data, ExtendMsgTemplate msgTemplate, SysUser sysUser) {
        ExtendMsg extendMsg = BeanUtil.toBean(data, ExtendMsg.class);
        extendMsg.setTemplateCode(data.getCode());
        extendMsg.setChannel(SourceType.SERVICE);
        extendMsg.setType(msgTemplate.getType());
        extendMsg.setRemindMode(msgTemplate.getRemindMode());
        if (CollUtil.isNotEmpty(data.getParamList())) {
            extendMsg.setParam(JsonUtil.toJson(data.getParamList()));
        }
        if (CollUtil.isNotEmpty(data.getConfigList())) {
            extendMsg.setConfigList(JsonUtil.toJson(data.getConfigList()));
        }
        extendMsg.setCreatedOrgId((sysUser != null && sysUser.getEmployee() != null) ? sysUser.getEmployee().getLastDeptId() : null);

        extendMsg.setStatus(TaskStatus.WAITING);
        if (extendMsg.getId() == null) {
            superManager.save(extendMsg);
        } else {
            superManager.updateById(extendMsg);
            extendMsgRecipientManager.remove(Wraps.<ExtendMsgRecipient>lbQ().eq(ExtendMsgRecipient::getMsgId, extendMsg.getId()));
        }

        List<ExtendMsgRecipient> recipientList = data.getRecipientList().stream().map((item) -> {
            ExtendMsgRecipient recipient = new ExtendMsgRecipient();
            recipient.setMsgId(extendMsg.getId());
            recipient.setRecipient(item.getRecipient());
            recipient.setExt(item.getExt());
            return recipient;
        }).toList();
        extendMsgRecipientManager.saveBatch(recipientList);

        //3, 判断是否立即发送
        if (data.getSendTime() == null) {
            MsgEventVO msgEventVO = new MsgEventVO();
            // 复制请求头中的参数
            msgEventVO.setMsgId(extendMsg.getId()).copy();

            // 具体的发送逻辑请看： MsgSendListener
            SpringUtils.publishEvent(new MsgSendEvent(msgEventVO));
        } else {
            // 延时消息任务由 thinglinks-base-executor 模块执行。
            Map<String, Long> param = MapUtil.builder(ContextConstants.TENANT_ID_HEADER, ContextUtil.getTenantId()).put("msgId", extendMsg.getId()).build();

            XxlJobInfoVO xxlJobInfoVO = XxlJobInfoVO.create(JobConstant.DEF_BASE_JOB_GROUP_NAME,
                    "【发送消息】" + extendMsg.getTitle(), extendMsg.getSendTime(), JobConstant.SMS_SEND_JOB_HANDLER, JsonUtil.toJson(param));
            jobFacde.addTimingTask(xxlJobInfoVO);
        }
        return true;
    }


}
