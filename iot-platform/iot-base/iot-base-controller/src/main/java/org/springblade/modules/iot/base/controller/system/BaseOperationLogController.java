package org.springblade.modules.iot.base.controller.system;

import com.mqttsnet.basic.annotation.log.WebLog;
import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.base.controller.SuperController;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import org.springblade.modules.iot.base.entity.system.BaseOperationLog;
import org.springblade.modules.iot.base.service.system.BaseOperationLogService;
import org.springblade.modules.iot.base.vo.query.system.BaseOperationLogPageQuery;
import org.springblade.modules.iot.base.vo.result.system.BaseOperationLogResultVO;
import org.springblade.modules.iot.base.vo.save.system.BaseOperationLogSaveVO;
import org.springblade.modules.iot.base.vo.update.system.BaseOperationLogUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


/**
 * <p>
 * 前端控制器
 * 操作日志
 * </p>
 *
 * @author mqttsnet
 * @date 2021-11-08
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/baseOperationLog")
@Tag(name = "操作日志")
public class BaseOperationLogController extends SuperController<BaseOperationLogService, Long, BaseOperationLog, BaseOperationLogSaveVO, BaseOperationLogUpdateVO, BaseOperationLogPageQuery, BaseOperationLogResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public R<BaseOperationLogResultVO> getDetail(@RequestParam("id") Long id) {
        return R.success(superService.getDetail(id));
    }

    @Operation(summary = "清空日志", description = "清空日志")
    @DeleteMapping("clear")
    @WebLog("清空日志")
    public R<Boolean> clear(@RequestParam(required = false, defaultValue = "1") Integer type) {
        LocalDateTime clearBeforeTime = null;
        Integer clearBeforeNum = null;
        if (type == 1) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-1);
        } else if (type == 2) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-3);
        } else if (type == 3) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-6);
        } else if (type == 4) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-12);
        } else if (type == 5) {
            // 清理一千条以前日志数据
            clearBeforeNum = 1000;
        } else if (type == 6) {
            // 清理一万条以前日志数据
            clearBeforeNum = 10000;
        } else if (type == 7) {
            // 清理三万条以前日志数据
            clearBeforeNum = 30000;
        } else if (type == 8) {
            // 清理十万条以前日志数据
            clearBeforeNum = 100000;
        }
        return success(superService.clearLog(clearBeforeTime, clearBeforeNum));
    }
}
