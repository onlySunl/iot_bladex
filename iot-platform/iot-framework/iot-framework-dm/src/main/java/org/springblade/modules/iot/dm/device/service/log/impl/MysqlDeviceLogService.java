

package org.springblade.modules.iot.dm.device.service.log.impl;
import org.springblade.modules.iot.common.enums.MessageType;
import MessageType;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import org.springblade.modules.iot.common.constant.MessageType;
import org.springblade.modules.iot.core.message.UPRequest;
import org.springblade.modules.iot.core.metadata.AbstractPropertyMetadata;
import org.springblade.modules.iot.pojo.bo.IoTDevicePropertiesBO;
import org.springblade.modules.iot.dm.device.service.impl.IoTProductDeviceService;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.persistence.dto.LogStorePolicyDTO;
import org.springblade.modules.iot.pojo.entity.IoTDeviceEvents;
import org.springblade.modules.iot.pojo.entity.IoTDeviceLog;
import org.springblade.modules.iot.pojo.entity.IoTDeviceLogMetadata.IoTDeviceLogMetadataBuilder;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import org.springblade.modules.iot.pojo.vo.IoTDeviceLogMetadataVO;
import org.springblade.modules.iot.pojo.vo.IoTDeviceLogVO;
import org.springblade.modules.iot.persistence.mapper.IoTDeviceLogMapper;
import org.springblade.modules.iot.persistence.mapper.IoTDeviceLogMetadataMapper;
import org.springblade.modules.iot.persistence.mapper.IoTDeviceLogMetadataShardMapper;
import org.springblade.modules.iot.persistence.mapper.IoTDeviceLogShardMapper;
import org.springblade.modules.iot.persistence.query.LogQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;

import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import org.springblade.modules.iot.common.util.PageUtil;
/**
 * 设备日志
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/9/22 16:10
 */
@Component
@Slf4j
public class MysqlDeviceLogService extends AbstractIoTDeviceLogService {
  private String storePolicy = "mysql";
  @Resource private IoTDeviceLogMapper ioTDeviceLogMapper;
  @Resource private IoTDeviceLogShardMapper ioTDeviceLogShardMapper;
  @Resource private IoTDeviceLogMetadataMapper ioTDeviceLogMetadataMapper;
  @Resource private IoTDeviceLogMetadataShardMapper ioTDeviceLogMetadataShardMapper;
  @Resource private IoTProductDeviceService iotProductDeviceService;

  /** 日志分表是否开启 */
  @Value("${shard.log.enable:true}")
  private Boolean enable;

  /** 日志meta分表是否开启 */
  @Value("${shard.logMeta.enable:true}")
  private Boolean metaEnable;

  @Override
  public String getPolicy() {
    return storePolicy;
  }

  @Override
  @Async
  public void saveDeviceLog(
      BaseUPRequest upRequest, IoTDeviceDTO ioTDeviceDTO, IoTProduct ioTProduct) {
    /** 产品数据存储策略，不为空则保存日志 */
    if (StrUtil.isNotBlank(ioTProduct.getStorePolicy())) {
      try {
        IoTDeviceLog log = build(upRequest, ioTDeviceDTO);
        if (enable) {
          ioTDeviceLogShardMapper.insert(log);
        }
      } catch (Exception e) {
        log.error("保存设备日志报错={}", e);
      }
      String storePolicyConfiguration = ioTProduct.getStorePolicyConfiguration();
      try {
        if (StrUtil.isNotBlank(storePolicyConfiguration)
            || MessageType.EVENT.equals(upRequest.getMessageType())) {
          LogStorePolicyDTO productLogStorePolicy =
              iotProductDeviceService.getProductLogStorePolicy(ioTProduct.getProductKey());
          saveLogStorePolicy(productLogStorePolicy, upRequest, ioTProduct);
        }
      } catch (Exception e) {
        log.error("保存设备属性扩展日志报错={}", e);
      }
    }
  }

  @Override
  public void saveDeviceLog(
      IoTDeviceLog ioTDeviceLog, IoTDeviceDTO ioTDeviceDTO, IoTProduct ioTProduct) {
    /** 产品数据存储策略，不为空则保存日志 */
    if (StrUtil.isNotBlank(ioTProduct.getStorePolicy())) {
      try {
        if (enable) {
          ioTDeviceLogShardMapper.insert(ioTDeviceLog);
        }
      } catch (Exception e) {
        log.error("保存设备日志报错={}", e);
      }
    }
  }

  private void saveLogStorePolicy(
      LogStorePolicyDTO logStorePolicyDTO, UPRequest up, IoTProduct ioTProduct) {
    if (MessageType.PROPERTIES.equals(up.getMessageType())
        && up.getProperties() != null
        && CollectionUtil.isNotEmpty(logStorePolicyDTO.getProperties())) {
      up.getProperties()
          .forEach(
              (key, value) -> {
                if (logStorePolicyDTO.getProperties().containsKey(key)) {
                  AbstractPropertyMetadata propertyOrNull =
                      getDeviceMetadata(ioTProduct.getMetadata()).getPropertyOrNull(key);
                  IoTDevicePropertiesBO ioTDevicePropertiesBO = new IoTDevicePropertiesBO();
                  ioTDevicePropertiesBO.withValue(propertyOrNull, value);
                  // TODO event
                  IoTDeviceLogMetadataBuilder IoTDeviceLogMetadataBuilder = builder(up);
                  IoTDeviceLogMetadataBuilder.property(key);
                  IoTDeviceLogMetadataBuilder.content(
                      StrUtil.str(value, CharsetUtil.charset("UTF-8")));
                  IoTDeviceLogMetadataBuilder.ext1(ioTDevicePropertiesBO.getPropertyName());
                  IoTDeviceLogMetadataBuilder.ext2(ioTDevicePropertiesBO.getFormatValue());
                  IoTDeviceLogMetadataBuilder.ext3(ioTDevicePropertiesBO.getSymbol());
                  if (metaEnable) {
                    ioTDeviceLogMetadataShardMapper.insertUseGeneratedKeys(
                        IoTDeviceLogMetadataBuilder.build());
                  }
                }
              });
    }
    if (MessageType.EVENT.equals(up.getMessageType())) {
      int maxStorage = 10;
      if (CollectionUtil.isNotEmpty(logStorePolicyDTO.getEvent())
          && logStorePolicyDTO.getEvent().containsKey(up.getEvent())) {
        maxStorage = logStorePolicyDTO.getEvent().get(up.getEvent()).getMaxStorage();
      }
      IoTDeviceLogMetadataBuilder IoTDeviceLogMetadataBuilder = builder(up);
      IoTDeviceLogMetadataBuilder.event(up.getEvent());
      IoTDeviceLogMetadataBuilder.content(up.getEventName());
      if (metaEnable) {
        ioTDeviceLogMetadataShardMapper.insertUseGeneratedKeys(IoTDeviceLogMetadataBuilder.build());
      }
    }
  }

  @Override
  public IPage<IoTDeviceLogVO> pageList(LogQuery bo) {
    bo.setProductKey(null);
    if (ObjectUtil.isNull(bo.getId())) {
      List<IoTDeviceLogVO> ioTDeviceLogVOS = ioTDeviceLogShardMapper.queryLogPageV2List(bo);
      return PageUtil.initPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(bo.getPageNum(), bo.getPageSize()), ioTDeviceLogVOS, ioTDeviceLogVOS.size()));
    } else {
      List<IoTDeviceLogVO> ioTDeviceLogVOS = ioTDeviceLogShardMapper.queryLogPageV2ByIdList(bo);
      return PageUtil.initPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(bo.getPageNum(), bo.getPageSize()), ioTDeviceLogVOS, ioTDeviceLogVOS.size()));
    }
  }

  @Override
  public IoTDeviceLogVO queryById(LogQuery logQuery) {
    IoTDeviceLogVO ioTDeviceLogVO = ioTDeviceLogMapper.queryLogById(logQuery);
    return ioTDeviceLogVO;
  }

  @Override
  public IPage<IoTDeviceEvents> queryEventTotal(String productKey, String iotId) {
    List<IoTDeviceEvents> list = selectDevEvents(productKey);
    for (IoTDeviceEvents devEvent : list) {
      List<String> events;
      if (metaEnable) {
        events =
            ioTDeviceLogMetadataShardMapper.queryEventTotalByEventAndId(devEvent.getId(), iotId);
      } else {
        events = ioTDeviceLogMetadataMapper.queryEventTotalByEventAndId(devEvent.getId(), iotId);
      }

      int size = events.size();
      if (size > 0) {
        devEvent.setTime(events.get(0));
        devEvent.setQty(size >= 100 ? "99+" : String.valueOf(size));
      }
    }
    return PageUtil.initPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(100, 1), list, list.size());
  }

  @Override
  public IPage<IoTDeviceLogMetadataVO> queryLogMeta(LogQuery logQuery) {
    List<IoTDeviceLogMetadataVO> list;
    if (metaEnable) {
      list = ioTDeviceLogMetadataShardMapper.selectLogMetaList(logQuery);
    } else {
      list = ioTDeviceLogMetadataMapper.selectLogMetaList(logQuery);
    }

    return PageUtil.initPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(logQuery.getPageNum(), logQuery.getPageSize()), list, list.size()));
  }

  @Override
  public JSONObject configMetadata() {
    return null;
  }
}
