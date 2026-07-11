package org.springblade.modules.nvr.service;


import org.springblade.core.tool.api.R;
import org.springblade.modules.nvr.common.constants.SecurityConstants;
import org.springblade.modules.nvr.common.constants.ServiceNameConstants;
import org.springblade.modules.nvr.domain.QsGroup;
import org.springblade.modules.nvr.domain.QsGroupTree;
import org.springblade.modules.nvr.factory.RemoteQsGroupFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分组管理 服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteQsGroupService",
        value = ServiceNameConstants.QS_SERVICE,
        fallbackFactory = RemoteQsGroupFallbackFactory.class,
        url= ServiceNameConstants.SERVICE_URL
)
public interface RemoteQsGroupService {

    /**
     * 查询分组树
     *
     * @param query     查询条件
     * @param parentId  父节点
     * @param hasDevice 是否包含设备
     * @param source    请求来源
     * @return
     */
    @GetMapping("/api/group/tree/list")
    R<List<QsGroupTree>> queryForTree(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Integer parentId,
            @RequestParam(required = false) Boolean hasDevice);

    /**
     * 查询所有分组
     *
     * @param query  查询条件
     * @param source 请求来源
     * @return
     */
    @GetMapping("/api/group/all/list")
    R<List<QsGroupTree>> queryAllGroups(@RequestParam(required = false) String query);

    /**
     * 查询分组列表
     *
     * @param query  查询条件
     * @param source 请求来源
     * @return
     */
    @GetMapping("/api/group/tree/query")
    R<List<QsGroup>> queryList(@RequestParam(required = false) String query);
}
