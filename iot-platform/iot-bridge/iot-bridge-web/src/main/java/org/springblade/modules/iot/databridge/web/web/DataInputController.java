

package org.springblade.modules.iot.databridge.web.web;

import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.modules.iot.pojo.bridge.entity.DataBridgeConfig;
import org.springblade.modules.iot.pojo.bridge.entity.DataInputLog;
import org.springblade.modules.iot.databridge.core.manager.DataInputManager;
import org.springblade.modules.iot.databridge.core.service.DataBridgeConfigService;
import org.springblade.modules.iot.databridge.core.service.DataInputLogService;
import org.springblade.modules.iot.persistence.page.PageUtils;
import org.springblade.modules.iot.persistence.page.TableDataInfo;
import org.springblade.core.tool.api.R;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 数据输入控制器
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@RestController
@RequestMapping("/databridge/input")
@Slf4j
public class DataInputController extends BladeController {

  @Resource private DataInputManager dataInputManager;

  @Resource private DataBridgeConfigService dataBridgeConfigService;

  @Resource private DataInputLogService dataInputLogService;

  /** 启动数据输入任务 */
  @PostMapping("/start/{configId}")
  public R<Void> startInputTask(@PathVariable Long configId) {
    try {
      dataInputManager.startInputTask(configId);
      return R.success("数据输入任务启动成功");
    } catch (Exception e) {
      log.error("启动数据输入任务失败: {}", e.getMessage(), e);
      return R.fail("启动数据输入任务失败: " + e.getMessage());
    }
  }

  /** 停止数据输入任务 */
  @PostMapping("/stop/{configId}")
  public R<Void> stopInputTask(@PathVariable Long configId) {
    try {
      dataInputManager.stopInputTask(configId);
      return R.success("数据输入任务停止成功");
    } catch (Exception e) {
      log.error("停止数据输入任务失败: {}", e.getMessage(), e);
      return R.fail("停止数据输入任务失败: " + e.getMessage());
    }
  }

  /** 检查任务运行状态 */
  @GetMapping("/status/{configId}")
  public R<Boolean> getTaskStatus(@PathVariable Long configId) {
    try {
      Boolean isRunning = dataInputManager.isTaskRunning(configId);
      return R.data(isRunning);
    } catch (Exception e) {
      log.error("查询任务状态失败: {}", e.getMessage(), e);
      return R.fail("查询任务状态失败: " + e.getMessage());
    }
  }

  /** 获取输入配置列表 */
  @GetMapping("/configs")
  public TableDataInfo<DataBridgeConfig> getInputConfigs(DataBridgeConfig config) {
    try {
      PageUtils.startPage();
      // TODO: 需要重新实现输入配置查询逻辑，因为Direction枚举已被移除
      List<DataBridgeConfig> list = dataBridgeConfigService.getAllConfigs();
      return PageUtils.<DataBridgeConfig>getDataTable(list);
    } catch (Exception e) {
      log.error("查询输入配置列表失败: {}", e.getMessage(), e);
      return PageUtils.getDataTable(List.of());
    }
  }

  /** 获取输入日志列表 */
  @GetMapping("/logs")
  public TableDataInfo<DataInputLog> getInputLogs(@RequestParam(required = false) Long configId) {
    try {
      PageUtils.startPage();
      List<DataInputLog> logs;
      if (configId != null) {
        logs = dataInputLogService.getByConfigId(configId);
      } else {
        // TODO: 实现查询所有日志的方法
        logs = List.of();
      }
      return PageUtils.getDataTable(logs);
    } catch (Exception e) {
      log.error("查询输入日志失败: {}", e.getMessage(), e);
      return PageUtils.getDataTable(List.of());
    }
  }

  /** 获取最近的输入日志 */
  @GetMapping("/logs/recent/{configId}")
  public R<List<DataInputLog>> getRecentLogs(
      @PathVariable Long configId, @RequestParam(defaultValue = "10") int limit) {
    try {
      List<DataInputLog> logs = dataInputLogService.getRecentLogs(configId, limit);
      return R.data(logs);
    } catch (Exception e) {
      log.error("查询最近日志失败: {}", e.getMessage(), e);
      return R.fail("查询最近日志失败: " + e.getMessage());
    }
  }

  /** 获取成功率统计 */
  @GetMapping("/stats/success-rate/{configId}")
  public R<Double> getSuccessRate(
      @PathVariable Long configId,
      @RequestParam(required = false) LocalDateTime startTime,
      @RequestParam(required = false) LocalDateTime endTime) {
    try {
      if (startTime == null) {
        startTime = LocalDateTime.now().minusDays(7); // 默认查询最近7天
      }
      if (endTime == null) {
        endTime = LocalDateTime.now();
      }

      Double successRate = dataInputLogService.getSuccessRate(configId, startTime, endTime);
      return R.data(successRate != null ? successRate : 0.0);
    } catch (Exception e) {
      log.error("查询成功率失败: {}", e.getMessage(), e);
      return R.fail("查询成功率失败: " + e.getMessage());
    }
  }

  /** 批量启动输入任务 */
  @PostMapping("/batch/start")
  public R<Void> batchStartInputTasks(@RequestBody List<Long> configIds) {
    try {
      int successCount = 0;
      int failCount = 0;

      for (Long configId : configIds) {
        try {
          dataInputManager.startInputTask(configId);
          successCount++;
        } catch (Exception e) {
          failCount++;
          log.error("启动输入任务失败，配置ID: {}, 错误: {}", configId, e.getMessage());
        }
      }

      return R.success(String.format("批量启动完成，成功: %d, 失败: %d", successCount, failCount));
    } catch (Exception e) {
      log.error("批量启动输入任务失败: {}", e.getMessage(), e);
      return R.fail("批量启动输入任务失败: " + e.getMessage());
    }
  }

  /** 批量停止输入任务 */
  @PostMapping("/batch/stop")
  public R<Void> batchStopInputTasks(@RequestBody List<Long> configIds) {
    try {
      int successCount = 0;
      int failCount = 0;

      for (Long configId : configIds) {
        try {
          dataInputManager.stopInputTask(configId);
          successCount++;
        } catch (Exception e) {
          failCount++;
          log.error("停止输入任务失败，配置ID: {}, 错误: {}", configId, e.getMessage());
        }
      }

      return R.success(String.format("批量停止完成，成功: %d, 失败: %d", successCount, failCount));
    } catch (Exception e) {
      log.error("批量停止输入任务失败: {}", e.getMessage(), e);
      return R.fail("批量停止输入任务失败: " + e.getMessage());
    }
  }

  /** 获取输入任务概览统计 */
  @GetMapping("/overview")
  public R<Object> getInputOverview() {
    try {
      // TODO: 实现输入任务概览统计
      // 包括：总任务数、运行中任务数、今日处理消息数、成功率等

      Map<String, Object> overview = new HashMap<>();
      overview.put("totalTasks", 0);
      overview.put("runningTasks", 0);
      overview.put("todayMessages", 0);
      overview.put("successRate", 0.0);

      return R.data(overview);
    } catch (Exception e) {
      log.error("查询输入任务概览失败: {}", e.getMessage(), e);
      return R.fail("查询输入任务概览失败: " + e.getMessage());
    }
  }
}
