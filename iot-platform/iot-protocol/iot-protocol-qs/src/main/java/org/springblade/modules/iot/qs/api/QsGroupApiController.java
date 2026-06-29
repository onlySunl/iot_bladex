package org.springblade.modules.iot.qs.api;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsGroup;
import org.springblade.modules.iot.domain.QsGroupTree;
import org.springblade.modules.iot.qs.service.IQsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分组管理API Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/api/group")
public class QsGroupApiController {

    @Autowired
    private IQsGroupService qsGroupService;

    /**
     * 查询分组树（内部接口）
     */
    @GetMapping("/tree/list")
    public R<List<QsGroupTree>> queryForTree(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Long parentId,
            @RequestParam(required = false) Boolean hasDevice) {
        if (ObjectUtils.isEmpty(query)) {
            query = null;
        }
        return R.data(qsGroupService.queryForTree(query, parentId, hasDevice));
    }

    /**
     * 查询所有分组（内部接口）
     */
    @GetMapping("/all/list")
    public R<List<QsGroupTree>> queryAllGroups(@RequestParam(required = false) String query) {
        if (ObjectUtils.isEmpty(query)) {
            query = null;
        }
        return R.data(qsGroupService.queryAllGroups(query));
    }

    /**
     * 查询分组列表（内部接口）
     */
    @GetMapping("/tree/query")
    public R<List<QsGroup>> queryList(@RequestParam(required = false) String query) {
        return R.data(qsGroupService.queryList(query));
    }
}
