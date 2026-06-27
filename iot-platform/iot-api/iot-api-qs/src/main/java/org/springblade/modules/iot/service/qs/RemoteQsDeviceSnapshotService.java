package org.springblade.modules.iot.service.qs;

import org.springblade.core.constant.SecurityConstants;
import org.springblade.core.constant.ServiceNameConstants;
import org.springblade.core.domain.R;
import org.springblade.modules.iot.domain.qs.QsDeviceSnapshot;
import org.springblade.modules.iot.factory.qs.RemoteQsDeviceSnapshotFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备抓图 服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteQsDeviceSnapshotService", value = ServiceNameConstants.QS_SERVICE, fallbackFactory = RemoteQsDeviceSnapshotFallbackFactory.class)
public interface RemoteQsDeviceSnapshotService {

    /**
     * 新增设备抓图
     *
     * @param qsDeviceSnapshot 设备抓图
     * @param inner            请求来源
     * @return 抓图记录ID
     */
    @PostMapping("/api/snapshot/add")
    R<Long> add(@RequestBody QsDeviceSnapshot qsDeviceSnapshot, @RequestHeader(SecurityConstants.FROM_SOURCE) String inner);

    /**
     * 查询设备抓图列表
     *
     * @param qsDeviceSnapshot 设备抓图
     * @param inner            请求来源
     * @return 抓图列表
     */
    @PostMapping("/api/snapshot/list")
    R<List<QsDeviceSnapshot>> list(@RequestBody QsDeviceSnapshot qsDeviceSnapshot, @RequestHeader(SecurityConstants.FROM_SOURCE) String inner);

    /**
     * 获取设备抓图详细信息
     *
     * @param id    抓图ID
     * @param inner 请求来源
     * @return 抓图详细信息
     */
    @GetMapping("/api/snapshot/getInfo/{id}")
    R<QsDeviceSnapshot> getInfo(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String inner);
}
