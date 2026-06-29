package org.springblade.modules.iot.qs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsGroup;
import org.springblade.modules.iot.domain.QsGroupTree;
import org.springblade.modules.iot.qs.service.IQsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分组管理
 *
 * @FileName QsGroupController
 * @Description
 * @Author fengcheng
 * @date 2026-04-14
 **/
@Slf4j
@RestController
@RequestMapping("/group")
public class QsGroupController extends BladeController {
    @Autowired
    private IQsGroupService qsGroupService;

    /**
     * 添加分组
     *
     * @param group 分组信息
     */
    @PostMapping("/add")
    public R add(@RequestBody QsGroup group) {
        qsGroupService.add(group);
        return R.success();
    }

    /**
     * 更新分组
     *
     * @param group 分组信息
     */
    @PostMapping("/update")
    public R update(@RequestBody QsGroup group) {
        qsGroupService.update(group);
        return R.success();
    }

    /**
     * 删除分组
     *
     * @param id 分组id
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Long id) {
        Assert.notNull(id, "分组id（deviceId）不需要存在");
        boolean result = qsGroupService.delete(id);
        if (!result) {
            throw new RuntimeException("移除失败");
        }
        return R.success();
    }

    /**
     * 查询分组节点
     *
     * @param query     要搜索的内容
     * @param parent    所属分组编号
     * @param hasDevice 是否查询设备
     * @return
     */
    @GetMapping("/tree/list")
    public R queryForTree(@RequestParam(required = false) String query, @RequestParam(required = false) Long parent, @RequestParam(required = false) Boolean hasDevice) {
        if (ObjectUtils.isEmpty(query)) {
            query = null;
        }
        List<QsGroupTree> list = qsGroupService.queryForTree(query, parent, hasDevice);
        return R.data(list);
    }

    /**
     * 查询分组节点设备
     *
     * @return
     */
    @GetMapping("/device/list")
    public R queryForDevice() {
        List<QsGroupTree> list = qsGroupService.queryForDevice();
        return R.data(list);
    }

    /**
     * 查询分组
     *
     * @param query 要搜索的内容
     * @return
     */
    @GetMapping("/tree/query")
    public IPage<List<QsGroup>> queryTree(String query) {
        List<QsGroup> list = qsGroupService.queryList(query);
        return new Page<>();
    }
}
