package org.springblade.modules.iot.api;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsRegion;
import org.springblade.modules.iot.domain.QsRegionTree;
import org.springblade.modules.iot.service.IQsRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 区域管理API Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/api/region")
public class QsRegionApiController {

    @Autowired
    private IQsRegionService qsRegionService;

    /**
     * 查询区域树（内部接口）
     */
    @GetMapping("/tree/list")
    public R<List<QsRegionTree>> queryForTree(
            @RequestParam(required = false) Long parent,
            @RequestParam(required = false) Boolean hasDevice) {
        return R.data(qsRegionService.queryForTree(parent, hasDevice));
    }

    /**
     * 查询所有区域（内部接口）
     */
    @GetMapping("/all/list")
    public R<List<QsRegionTree>> queryAllRegions(@RequestParam(required = false) String query) {
        if (ObjectUtils.isEmpty(query)) {
            query = null;
        }
        return R.data(qsRegionService.queryAllRegions(query));
    }

    /**
     * 查询区域列表（内部接口）
     */
    @GetMapping("/tree/query")
    public R<List<QsRegion>> queryList(@RequestParam(required = false) String query) {
        return R.data(qsRegionService.queryList(null, null, query));
    }
}
