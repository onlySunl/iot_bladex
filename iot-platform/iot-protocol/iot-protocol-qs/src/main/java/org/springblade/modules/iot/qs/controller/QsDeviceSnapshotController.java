package org.springblade.modules.iot.qs.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.constants.SecurityConstants;
import org.springblade.modules.iot.domain.QsDevice;
import org.springblade.modules.iot.domain.QsDeviceSnapshot;
import org.springblade.modules.iot.qs.service.IQsDeviceSnapshotService;
import org.springblade.modules.iot.service.RemoteQsDeviceService;
import org.springblade.modules.iot.service.RemoteZlmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 设备抓图Controller
 *
 * @author ruoyi
 * @date 2026-05-17
 */
@Slf4j
@RestController
@RequestMapping("/snapshot")
public class QsDeviceSnapshotController extends BladeController {
    @Autowired
    private IQsDeviceSnapshotService qsDeviceSnapshotService;

    @Autowired
    private RemoteZlmService remoteZlmService;

    @Autowired
    private RemoteQsDeviceService remoteQsDeviceService;

    @Value("${file.path}")
    private String filePath;

    /**
     * 查询设备抓图列表
     */
    @GetMapping("/list")
    public IPage<List<QsDeviceSnapshot>> list(QsDeviceSnapshot qsDeviceSnapshot) {

        List<QsDeviceSnapshot> list = qsDeviceSnapshotService.selectQsDeviceSnapshotList(qsDeviceSnapshot);
        return new Page<>();
    }

    /**
     * 导出设备抓图列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, QsDeviceSnapshot qsDeviceSnapshot) {
        List<QsDeviceSnapshot> list = qsDeviceSnapshotService.selectQsDeviceSnapshotList(qsDeviceSnapshot);
        //ExcelUtil<QsDeviceSnapshot> util = new ExcelUtil<QsDeviceSnapshot>(QsDeviceSnapshot.class);
        //util.exportExcel(response, list, "设备抓图数据");
    }

    /**
     * 获取设备抓图详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id) {
        return R.data(qsDeviceSnapshotService.selectQsDeviceSnapshotById(id));
    }

    /**
     * 新增设备抓图
     */
    @PostMapping
    public R add(@RequestBody QsDeviceSnapshot qsDeviceSnapshot) {
        return R.data(qsDeviceSnapshotService.insertQsDeviceSnapshot(qsDeviceSnapshot));
    }

    /**
     * 修改设备抓图
     */
    @PutMapping
    public R edit(@RequestBody QsDeviceSnapshot qsDeviceSnapshot) {
        return R.data(qsDeviceSnapshotService.updateQsDeviceSnapshot(qsDeviceSnapshot));
    }

    /**
     * 删除设备抓图
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids) {
        return R.data(qsDeviceSnapshotService.deleteQsDeviceSnapshotByIds(ids));
    }

    /**
     * 从流中抓图并保存
     *
     * @param deviceId 设备ID
     * @param app 应用名
     * @param stream 流名
     * @param snapshotType 抓图类型
     * @return 抓图结果
     */
    @PostMapping("/captureFromStream")
    public R captureFromStream(
            @RequestParam Long deviceId,
            @RequestParam String app,
            @RequestParam String stream,
            @RequestParam(defaultValue = "manual") String snapshotType
    ) {
        try {
            log.info("开始从流中抓图，deviceId: {}, app: {}, stream: {}", deviceId, app, stream);

            // 获取设备信息
            R<QsDevice> deviceR = remoteQsDeviceService.getQsDeviceInfo(deviceId, SecurityConstants.INNER);
            if (R.isNotSuccess(deviceR) || deviceR.getData() == null) {
                log.error("获取设备信息失败，deviceId: {}", deviceId);
                return fail("获取设备信息失败");
            }
            QsDevice device = deviceR.getData();

            // 调用ZLM服务抓图
            R<String> snapR = remoteZlmService.snap(app, stream, SecurityConstants.INNER);
            if (R.isNotSuccess(snapR) || snapR.getData() == null) {
                log.error("抓图失败，deviceId: {}", deviceId);
                return fail("抓图失败");
            }
            String fileUrl = snapR.getData();
            log.info("抓图成功，fileUrl: {}", fileUrl);

            // 从URL中提取文件名
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            String localFilePath = filePath + "/snap/" + fileName;
            File savedFile = new File(localFilePath);
            long fileSize = savedFile.exists() ? savedFile.length() : 0;

            // 构造抓图记录
            QsDeviceSnapshot snapshot = new QsDeviceSnapshot();
            snapshot.setDeviceId(device.getId());
            snapshot.setDeviceCode(device.getDeviceCode());
            snapshot.setDeviceName(device.getDeviceName());
            snapshot.setFileUrl(fileUrl);
            snapshot.setFilePath(localFilePath);
            snapshot.setFileSize(fileSize);
            snapshot.setFileName(fileName);
            snapshot.setFileType("jpg");
            snapshot.setSnapshotType(snapshotType);
            snapshot.setSdkType("stream");
            snapshot.setChannel(device.getChannel() != null ? device.getChannel() : 0L);
            snapshot.setCaptureTime(new Date());

            // 保存到数据库
            int result = qsDeviceSnapshotService.insertQsDeviceSnapshot(snapshot);
            if (result > 0) {
                log.info("抓图记录保存成功，snapshotId: {}", snapshot.getId());
                return R.data(snapshot);
            } else {
                log.error("保存抓图记录失败");
                return R.fail("保存抓图记录失败");
            }

        } catch (Exception e) {
            log.error("从流中抓图异常", e);
            return R.fail("抓图异常：" + e.getMessage());
        }
    }
}
