package org.springblade.modules.iot.controller.admin.sip;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelation;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelationPageReqVO;
import org.springblade.modules.iot.service.sip.ISipRelationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 监控设备关联Controller
 *
 * @author kerwincui
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/eiot/iot/relation")
@Tag(name = "监控设备关联")
public class SipRelationController extends BladeController {
    @Resource
    private ISipRelationService sipRelationService;

    /**
     * 查询监控设备关联列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询监控设备关联列表")
    public R<PageResult<SipRelation>> list(SipRelationPageReqVO reqVO) {
//      List<SipRelation> list = sipRelationService.selectSipRelationList(sipRelation);
        PageResult<SipRelation> list = sipRelationService.selectSipRelationPage(reqVO);
        return data(list);
    }

    /**
     * 获取监控设备关联详细信息
     */
    @GetMapping(value = "/{id}")
    @Operation(summary = "获取监控设备关联详细信息")
    public R<SipRelation> getInfo(@PathVariable("id") Long id) {
        return data(sipRelationService.selectSipRelationById(id));
    }
    @GetMapping(value = "/dev/{deviceId}")
    @Operation(summary = "根据设备id获取关联通道详细信息")
    public R<List<SipRelation>> getInfoByDeviceId(@PathVariable("deviceId") Long deviceId) {
        return data(sipRelationService.selectSipRelationByDeviceId(deviceId));
    }

    /**
     * 新增或更新监控设备关联
     */
    @PostMapping("/addOrUp")
    @Operation(summary = "新增或更新监控设备关联")
    public R<Integer> addOrUp(@RequestBody SipRelation sipRelation) {
        return data(sipRelationService.addOrUpdateSipRelation(sipRelation));
    }

    /**
     * 修改监控设备关联
     */
    @PutMapping
    @Operation(summary = "修改监控设备关联")
    public R<Integer> edit(@RequestBody SipRelation sipRelation) {
        return data(sipRelationService.updateSipRelation(sipRelation));
    }

    /**
     * 删除监控设备关联
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除监控设备关联")
    public R<Integer> remove(@PathVariable Long[] ids) {
        return data(sipRelationService.deleteSipRelationByIds(ids));
    }
}
