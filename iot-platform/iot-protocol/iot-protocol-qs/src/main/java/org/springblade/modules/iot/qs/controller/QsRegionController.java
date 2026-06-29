package org.springblade.modules.iot.qs.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsRegion;
import org.springblade.modules.iot.domain.QsRegionTree;
import org.springblade.modules.iot.qs.service.IQsRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 区域管理
 *
 * @FileName QsRegionController
 * @Description
 * @Author fengcheng
 * @date 2026-04-13
 **/
@Slf4j
@RestController
@RequestMapping("/region")
public class QsRegionController extends BladeController {

    @Autowired
    private IQsRegionService qsRegionService;

    /**
     * 添加区域
     *
     * @param region 区域信息
     */
    @PostMapping("/add")
    public R add(@RequestBody QsRegion region) {
        qsRegionService.add(region);
        return R.success();
    }

    /**
     * 更新区域
     *
     * @param region
     */
    @PostMapping("/update")
    public R update(@RequestBody QsRegion region) {
        qsRegionService.update(region);
        return R.success();
    }

    /**
     * 删除区域
     *
     * @param id 区域ID
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Long id) {
        Assert.notNull(id, "区域ID需要存在");
        boolean result = qsRegionService.deleteByDeviceId(id);
        if (!result) {
            throw new RuntimeException("移除失败");
        }
        return R.success();
    }

    /**
     * 查询区域节点
     *
     * @param parent    所属行政区划编号
     * @param hasDevice 是否查询设备
     * @return
     */
    @GetMapping("/tree/list")
    public R queryForTree(@RequestParam(required = false) Long parent, @RequestParam(required = false) Boolean hasDevice) {
        List<QsRegionTree> list = qsRegionService.queryForTree(parent, hasDevice);
        return R.data(list);
    }

    /**
     * 查询区域节点设备
     *
     * @return
     */
    @GetMapping("/device/list")
    public R queryForDevice() {
        List<QsRegionTree> list = qsRegionService.queryForDevice();
        return R.data(list);
    }

    /**
     * 查询区域
     *
     * @param query 要搜索的内容
     * @return
     */

    @GetMapping("/tree/query")
    public IPage<List<QsRegion>> queryTree(Integer pageNum, Integer pageSize, String query) {
        List<QsRegion> list = qsRegionService.queryList(pageNum, pageSize, query);
        return new Page<>();
    }

    /**
     * 获取所属的行政区划下的行政区划
     *
     * @param parent 所属的行政区划
     * @return
     */
    @GetMapping("/base/child/list")
    public R getAllChild(@RequestParam(required = false) String parent) {
        if (ObjectUtils.isEmpty(parent)) {
            parent = null;
        }
        List<QsRegion> list = qsRegionService.getAllChild(parent);
        return R.data(list);
    }
}
