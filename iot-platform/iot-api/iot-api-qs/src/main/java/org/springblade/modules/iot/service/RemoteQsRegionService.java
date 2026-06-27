package org.springblade.modules.iot.service;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.constants.SecurityConstants;
import org.springblade.modules.iot.common.constants.ServiceNameConstants;
import org.springblade.modules.iot.domain.QsRegion;
import org.springblade.modules.iot.domain.QsRegionTree;
import org.springblade.modules.iot.factory.RemoteQsRegionFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 区域管理 服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteQsRegionService",
        value = ServiceNameConstants.QS_SERVICE,
        fallbackFactory = RemoteQsRegionFallbackFactory.class,
        url= ServiceNameConstants.SERVICE_URL
)
public interface RemoteQsRegionService {

    /**
     * 查询区域树
     *
     * @param parent    父节点
     * @param hasDevice 是否包含设备
     * @param source    请求来源
     * @return
     */
    @GetMapping("/api/region/tree/list")
    R<List<QsRegionTree>> queryForTree(
            @RequestParam(required = false) Integer parent,
            @RequestParam(required = false) Boolean hasDevice,
            @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 查询所有区域
     *
     * @param query  查询条件
     * @param source 请求来源
     * @return
     */
    @GetMapping("/api/region/all/list")
    R<List<QsRegionTree>> queryAllRegions(@RequestParam(required = false) String query, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 查询区域列表
     *
     * @param query  查询条件
     * @param source 请求来源
     * @return
     */
    @GetMapping("/api/region/tree/query")
    R<List<QsRegion>> queryList(@RequestParam(required = false) String query, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
