package org.springblade.modules.iot.file.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springblade.core.mvc.mapper.SuperMapper;
import org.springblade.modules.iot.file.entity.Appendix;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 业务附件
 * </p>
 *
 * @author mqttsnet
 * @date 2021-06-30
 * @create [2021-06-30] [mqttsnet] [初始创建]
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface AppendixMapper extends SuperMapper<Appendix> {

}
