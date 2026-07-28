package org.springblade.modules.iot.bridge.controller;

import com.mqttsnet.basic.annotation.log.WebLog;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.service.bridge.SubscriptionSourceService;
import org.springblade.modules.iot.vo.result.bridge.SubscriptionSourceResultVO;
import org.springblade.modules.iot.vo.save.bridge.SubscriptionSourceSaveVO;
import org.springblade.modules.iot.vo.update.bridge.SubscriptionSourceUpdateVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

/**
 * 数据桥接-订阅源前端控制器。
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/subscriptionSource")
@Tag(name = "数据桥接-订阅源")
public class SubscriptionSourceController extends BladeController {

    private final EchoService echoService;


    public EchoService getEchoService() {
        return echoService;
    }

    private final  SubscriptionSourceService superService;



    @Operation(summary = "保存订阅源", description = "默认 enable=false,必须手动启用")
    @PostMapping("/saveSubscriptionSource")
    @WebLog(value = "保存订阅源", request = false)
    public R<SubscriptionSourceSaveVO> saveSubscriptionSource(@RequestBody @Validated SubscriptionSourceSaveVO saveVO) {
        return wrap("保存订阅源", () -> superService.saveSubscriptionSource(saveVO));
    }

    @Operation(summary = "修改订阅源", description = "配置变更后 enable 自动重置为 false")
    @PutMapping("/updateSubscriptionSource")
    @WebLog(value = "修改订阅源", request = false)
    public R<SubscriptionSourceUpdateVO> updateSubscriptionSource(@RequestBody @Validated SubscriptionSourceUpdateVO updateVO) {
        return wrap("修改订阅源", () -> superService.updateSubscriptionSource(updateVO));
    }

    @Operation(summary = "订阅源详情")
    @GetMapping("/getSubscriptionSourceDetail/{id}")
    @Parameters({@Parameter(name = "id", description = "订阅源 ID", required = true)})
    public R<SubscriptionSourceResultVO> getSubscriptionSourceDetail(@PathVariable("id") Long id) {
        return wrap("查询订阅源详情 id=" + id, () -> {
            SubscriptionSourceResultVO result = superService.getSubscriptionSourceDetail(id);
            echoService.action(result);
            return result;
        });
    }

    @Operation(summary = "启停订阅源", description = "启用时启动 KafkaConsumer/MqttClient subscribe;禁用时 stop")
    @PutMapping("/changeStatus/{id}")
    @WebLog(value = "启停订阅源", request = false)
    @Parameters({
            @Parameter(name = "id", description = "订阅源 ID", required = true),
            @Parameter(name = "enable", description = "true=启用 / false=禁用", required = true)
    })
    public R<Boolean> changeStatus(@PathVariable("id") Long id, @RequestParam("enable") Boolean enable) {
        return wrap("启停订阅源 id=" + id, () -> superService.changeStatus(id, enable));
    }

    @Operation(summary = "删除订阅源")
    @DeleteMapping("/deleteSubscriptionSource/{id}")
    @WebLog(value = "删除订阅源", request = false)
    @Parameters({@Parameter(name = "id", description = "订阅源 ID", required = true)})
    public R<Boolean> deleteSubscriptionSource(@PathVariable("id") Long id) {
        return wrap("删除订阅源 id=" + id, () -> superService.deleteSubscriptionSource(id));
    }

    /**
     * 统一 try/catch:业务异常返 BizException,其它异常 log + R.fail()。
     *
     * @param opDesc 操作描述,用于失败日志
     * @param action 业务动作
     * @param <T>    返回数据类型
     * @return 统一响应
     */
    private <T> R<T> wrap(String opDesc, Supplier<T> action) {
        try {
            return R.data(action.get());
        } catch (Exception e) {
            log.error("{} 失败: {}", opDesc, e.getMessage(), e);
            return R.fail("");
        }
    }
}
