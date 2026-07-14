

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceRuleLog;
import org.springblade.modules.iot.persistence.entity.vo.SceneLinkageLogVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IoTDeviceRuleLogMapper extends BaseMapper<IoTDeviceRuleLog> {

  /**
   * 关联查询场景联动执行日志
   *
   * @param cName 场景名称
   * @param cStatus 执行状态
   * @param cId 业务ID
   * @param cType 业务类型
   * @param triggerType 触发类型
   * @param createBy 创建者
   * @param createTimeStart 创建时间开始
   * @param createTimeEnd 创建时间结束
   * @return 查询结果
   */
  List<SceneLinkageLogVO> selectSceneLinkageLogs(
      @Param("cName") String cName,
      @Param("cStatus") Byte cStatus,
      @Param("cId") String cId,
      @Param("cType") Byte cType,
      @Param("triggerType") String triggerType,
      @Param("createBy") String createBy,
      @Param("createTimeStart") String createTimeStart,
      @Param("createTimeEnd") String createTimeEnd);
}
