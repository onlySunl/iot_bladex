package org.springblade.core.dinger.process;


/**
 * 消息信息
 */
public interface INoticeProcessor {

    /**
     * @param obj 消息信息
     */
    Object sendNotice(Object obj);
}
