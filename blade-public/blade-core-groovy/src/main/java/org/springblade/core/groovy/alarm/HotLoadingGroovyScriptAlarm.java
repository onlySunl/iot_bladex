package org.springblade.core.groovy.alarm;

import org.springblade.core.groovy.entity.ScriptEntry;

import java.util.List;

/**
 * 热加载脚本告警接口
 *
 * @author mqttsnet 2025/03/29 22:45
 */
public interface HotLoadingGroovyScriptAlarm {

    /**
     * 加载脚本异常告警
     *
     * @param scriptEntries 脚本实集合
     * @param throwable     异常信息
     * @author mqttsnet 2024/9/29 10:46 下午
     */
    void alarm(List<ScriptEntry> scriptEntries, Throwable throwable);

}
