package org.springblade.common.echo;

import java.util.Map;

/**
 * 回显 VO 接口
 */
public interface EchoVO {
    Map<String, Object> getEchoMap();
    void setEchoMap(Map<String, Object> echoMap);
}
