package org.springblade.modules.iot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tenant.TenantId;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.Protocol;
import org.springblade.modules.iot.pojo.vo.ProtocolVO;
import org.springblade.modules.iot.service.IProtocolService;
import org.springblade.modules.iot.wrapper.ProtocolWrapper;
import org.springframework.web.bind.annotation.*;

/**
 * 协议管理 Controller
 *
 * @author blade-iot
 */
@Tag(name = "协议管理")
@RestController
@RequestMapping("/protocol")
@RequiredArgsConstructor
public class ProtocolController extends BladeController {

    private final IProtocolService protocolService;
    private final ProtocolWrapper protocolWrapper;

    @Operation(summary = "分页列表")
    @GetMapping("/list")
    public R<IPage<ProtocolVO>> list(Protocol protocol, QueryWrapper<Protocol> queryWrapper) {
        IPage<Protocol> pages = protocolService.page(Condition.getPage(queryWrapper));
        return R.data(protocolWrapper.wrap().wrapListVO(pages));
    }

    @Operation(summary = "详情")
    @GetMapping("/detail")
    public R<ProtocolVO> detail(@RequestParam String id) {
        Protocol detail = protocolService.getById(id);
        return R.data(protocolWrapper.wrap().wrapVO(detail));
    }

    @Operation(summary = "新增或修改")
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Protocol protocol) {
        return R.data(protocolService.saveOrUpdate(protocol));
    }

    @Operation(summary = "删除")
    @PostMapping("/remove")
    public R<Boolean> remove(@RequestParam String ids) {
        return R.data(protocolService.removeByIds(Func.toLongList(ids)));
    }
}
