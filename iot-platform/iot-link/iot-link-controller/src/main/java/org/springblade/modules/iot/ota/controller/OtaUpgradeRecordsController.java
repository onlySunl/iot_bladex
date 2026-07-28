package org.springblade.modules.iot.ota.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.common.annotation.log.WebLog;
import org.springblade.core.tool.api.R;
import org.springblade.core.mvc.controller.BaseController;
import org.springblade.common.base.request.PageParams;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.common.interfaces.echo.EchoService;
import org.springblade.common.utils.BeanUtil;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import org.springblade.modules.iot.ota.entity.OtaUpgradeRecords;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.modules.iot.ota.service.OtaUpgradeTasksService;
import org.springblade.modules.iot.ota.service.OtaUpgradesService;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeRecordsPageQuery;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsSummaryResultVO;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeTasksResultVO;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradesResultVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeRecordsSaveVO;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradeRecordsUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * OTA升级记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:42:04
 * @create [2024-01-12 22:42:04] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/otaUpgradeRecords")
@Tag(name = "OTA升级记录")
public class OtaUpgradeRecordsController extends BaseController<OtaUpgradeRecordsService, Long, OtaUpgradeRecords, OtaUpgradeRecordsSaveVO,
        OtaUpgradeRecordsUpdateVO, OtaUpgradeRecordsPageQuery, OtaUpgradeRecordsResultVO> {
    private final EchoService echoService;
    private final OtaUpgradeTasksService otaUpgradeTasksService;
    private final OtaUpgradesService otaUpgradesService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<OtaUpgradeRecords> handlerWrapper(OtaUpgradeRecords model, PageParams<OtaUpgradeRecordsPageQuery> params) {
        QueryWrap<OtaUpgradeRecords> queryWrap = super.handlerWrapper(model, params);
        // 开启数据权限
        DataScopeHelper.startDataScope("ota_upgrade_records");
        return queryWrap;
    }

    /**
     * 在上下文中执行异步任务
     *
     * @param task 需要执行的任务
     * @param <T>  返回类型
     * @return {@link CompletableFuture <T>} 任务执行结果
     */
    private <T> CompletableFuture<T> executeWithContext(Supplier<T> task) {
        Map<String, String> localMap = ContextUtil.getLocalMap();
        return CompletableFuture.supplyAsync(() -> {
            ContextUtil.setLocalMap(localMap);
            try {
                return task.get();
            } finally {
                ContextUtil.remove();
            }
        });
    }

    @Override
    public void handlerResult(IPage<OtaUpgradeRecordsResultVO> page) {
        // 调用父类方法处理分页结果
        super.handlerResult(page);
        // 对分页结果进行数据填充
        enrichOtaUpgradeRecordsResultVO(page.getRecords());
    }

    /**
     * 通用方法：填充OTA升级记录结果VO的关联数据
     *
     * @param records OTA升级记录结果VO列表
     */
    private void enrichOtaUpgradeRecordsResultVO(List<OtaUpgradeRecordsResultVO> records) {
        if (CollUtil.isEmpty(records)) {
            return;
        }

        // 提取任务ID和升级包ID
        List<Long> taskIds = records.stream()
                .map(OtaUpgradeRecordsResultVO::getTaskId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        List<Long> upgradeIds = records.stream()
                .map(OtaUpgradeRecordsResultVO::getUpgradeId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        if (CollUtil.isEmpty(taskIds) && CollUtil.isEmpty(upgradeIds)) {
            return;
        }

        CompletableFuture<Map<Long, OtaUpgradeTasksResultVO>> tasksFuture =
                CollUtil.isEmpty(taskIds) ?
                        CompletableFuture.completedFuture(Collections.emptyMap()) :
                        executeWithContext(() ->
                                otaUpgradeTasksService.selectListByIds(taskIds)
                        ).thenApply(tasks ->
                                Optional.ofNullable(tasks)
                                        .orElseGet(Collections::emptyList)
                                        .stream()
                                        .filter(Objects::nonNull)
                                        .map(dto -> BeanPlusUtil.toBeanIgnoreError(dto, OtaUpgradeTasksResultVO.class))
                                        .filter(vo -> vo.getId() != null)
                                        .collect(Collectors.toMap(
                                                OtaUpgradeTasksResultVO::getId,
                                                Function.identity(),
                                                (first, second) -> first
                                        ))
                        );

        CompletableFuture<Map<Long, OtaUpgradesResultVO>> upgradesFuture =
                CollUtil.isEmpty(upgradeIds) ?
                        CompletableFuture.completedFuture(Collections.emptyMap()) :
                        executeWithContext(() ->
                                otaUpgradesService.selectListByIds(upgradeIds)
                        ).thenApply(upgrades ->
                                Optional.ofNullable(upgrades)
                                        .orElseGet(Collections::emptyList)
                                        .stream()
                                        .filter(Objects::nonNull)
                                        .map(dto -> BeanPlusUtil.toBeanIgnoreError(dto, OtaUpgradesResultVO.class))
                                        .filter(vo -> vo.getId() != null)
                                        .collect(Collectors.toMap(
                                                OtaUpgradesResultVO::getId,
                                                Function.identity(),
                                                (first, second) -> first
                                        ))
                        );

        CompletableFuture.allOf(tasksFuture, upgradesFuture).join();
        Map<Long, OtaUpgradeTasksResultVO> tasksMap = tasksFuture.join();
        Map<Long, OtaUpgradesResultVO> upgradesMap = upgradesFuture.join();

        records.forEach(record -> {
            // 填充任务信息
            Optional.ofNullable(record.getTaskId())
                    .map(tasksMap::get)
                    .ifPresent(record::setOtaUpgradeTasksResult);

            // 填充升级包信息
            Optional.ofNullable(record.getUpgradeId())
                    .map(upgradesMap::get)
                    .ifPresent(record::setOtaUpgradesResult);
        });
    }

    @Operation(summary = "保存OTA升级记录", description = "保存一个新的OTA升级记录")
    @PostMapping("/saveOtaUpgradeRecord")
    @WebLog(value = "保存OTA升级记录")
    public R<OtaUpgradeRecordsSaveVO> saveOtaUpgradeRecord(@Valid @RequestBody OtaUpgradeRecordsSaveVO saveVO) {
        OtaUpgradeRecordsSaveVO savedRecord = superService.saveOtaUpgradeRecord(saveVO);
        return R.success(savedRecord);
    }

    @Operation(summary = "更新OTA升级记录", description = "更新一个现有的OTA升级记录")
    @PutMapping("/updateOtaUpgradeRecord")
    @WebLog(value = "更新OTA升级记录")
    public R<OtaUpgradeRecordsUpdateVO> updateOtaUpgradeRecord(@Valid @RequestBody OtaUpgradeRecordsUpdateVO updateVO) {
        OtaUpgradeRecordsUpdateVO updatedRecord = superService.updateOtaUpgradeRecord(updateVO);
        return R.success(updatedRecord);
    }

    @Operation(summary = "获取OTA升级记录统计信息", description = "根据任务ID返回OTA升级记录的统计信息")
    @Parameters({
            @Parameter(name = "taskId", description = "OTA升级任务ID", required = true)
    })
    @GetMapping("/getOtaUpgradeRecordsSummary/{taskId}")
    public R<OtaUpgradeRecordsSummaryResultVO> getOtaUpgradeRecordsSummary(@PathVariable Long taskId) {
        log.info("Fetching OTA upgrade records for task ID: {}", taskId);
        OtaUpgradeRecordsSummaryResultVO records = superService.getOtaUpgradeRecordsSummary(taskId);
        return R.success(records);
    }

    @Operation(summary = "获取OTA升级记录详情", description = "根据ID返回OTA升级记录的详细信息，包含关联的任务和升级包信息")
    @Parameters({
            @Parameter(name = "id", description = "OTA升级记录ID", required = true)
    })
    @GetMapping("/details/{id}")
    public R<OtaUpgradeRecordsResultVO> getUpgradeRecordDetails(@PathVariable Long id) {
        log.info("获取OTA升级记录详情 - 记录ID: {}", id);
        // 获取基础升级记录信息
        OtaUpgradeRecordsResultVO upgradeRecordDetails = superService.getUpgradeRecordDetails(id);
        // 使用通用方法填充关联数据
        enrichOtaUpgradeRecordsResultVO(Collections.singletonList(upgradeRecordDetails));
        // 处理echo数据
        echoService.action(upgradeRecordDetails);
        return R.success(upgradeRecordDetails);
    }


}