package org.springblade.modules.iot.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.base.R;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.core.annotation.log.WebLog;
import org.springblade.core.database.mybatis.conditions.query.QueryWrap;
import org.springblade.core.mvc.controller.SuperController;
import org.springblade.core.mvc.request.PageParams;
import org.springblade.modules.iot.entity.alarm.RuleAlarmChannel;
import org.springblade.modules.iot.service.alarm.RuleAlarmChannelService;
import org.springblade.modules.iot.vo.query.alarm.RuleAlarmChannelPageQuery;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmChannelDetailsResultVO;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmChannelResultVO;
import org.springblade.modules.iot.vo.save.alarm.RuleAlarmChannelSaveVO;
import org.springblade.modules.iot.vo.update.alarm.RuleAlarmChannelUpdateVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * 告警规则渠道表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:58
 * @create [2023-09-09 21:14:58] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/ruleAlarmChannel")
@Tag(name = "告警规则渠道")
public class RuleAlarmChannelController extends SuperController<RuleAlarmChannelService, Long, RuleAlarmChannel, RuleAlarmChannelSaveVO,
        RuleAlarmChannelUpdateVO, RuleAlarmChannelPageQuery, RuleAlarmChannelResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    /**
     * 保存告警渠道
     *
     * @param saveVO 保存参数
     * @return 实体
     */
    @Operation(summary = "保存告警渠道", description = "保存告警渠道")
    @PostMapping("/saveAlarmChannel")
    @WebLog(value = "保存告警渠道", request = false)
    public R<RuleAlarmChannelSaveVO> saveAlarmChannel(@RequestBody RuleAlarmChannelSaveVO saveVO) {
        return R.success(superService.saveAlarmChannel(saveVO));
    }

    /**
     * 修改告警渠道
     *
     * @param updateVO 更新参数
     * @return 实体
     */
    @Operation(summary = "修改告警渠道", description = "修改告警渠道")
    @PutMapping("/updateAlarmChannel")
    @WebLog(value = "修改告警渠道", request = false)
    public R<RuleAlarmChannelUpdateVO> updateAlarmChannel(@RequestBody RuleAlarmChannelUpdateVO updateVO) {
        return R.success(superService.updateAlarmChannel(updateVO));
    }

    /**
     * 删除告警渠道
     *
     * @param id 告警渠道ID
     * @return 删除结果
     */
    @Operation(summary = "删除告警渠道", description = "根据告警渠道ID删除告警渠道")
    @Parameters({
            @Parameter(name = "id", description = "告警渠道ID", required = true)
    })
    @DeleteMapping("/deleteAlarmChannel/{id}")
    @WebLog(value = "删除告警渠道", request = false)
    public R<Boolean> deleteAlarmChannel(@PathVariable("id") Long id) {
        return R.success(superService.deleteAlarmChannel(id));
    }

    /**
     * 获取告警渠道详情
     *
     * @param id 告警渠道ID
     * @return 告警渠道详情
     */
    @Operation(summary = "获取告警渠道详情", description = "根据告警渠道ID获取告警渠道详情")
    @Parameters({
            @Parameter(name = "id", description = "告警渠道ID", required = true)
    })
    @GetMapping("/getAlarmChannelDetails/{id}")
    public R<RuleAlarmChannelDetailsResultVO> getAlarmChannelDetails(@PathVariable("id") Long id) {
        RuleAlarmChannelDetailsResultVO result = superService.getAlarmChannelDetails(id);
        echoService.action(result);
        return R.success(result);
    }

}


