package org.springblade.core.dinger.content;

import org.springblade.core.dinger.properties.FeiShuProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 飞书通知请求体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeiShuInfoReq {

    private FeiShuProperties feiShuProperties;

    private FeiShuInfo feiShuInfo;

}
