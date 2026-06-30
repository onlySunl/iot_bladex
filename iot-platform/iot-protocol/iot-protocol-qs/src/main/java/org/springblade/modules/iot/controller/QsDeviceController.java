package org.springblade.modules.iot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.*;
import org.springblade.modules.iot.service.IQsDeviceService;
import org.springblade.modules.iot.utils.VideoSnapshotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 视频监控设备Controller
 *
 * @author fengcheng
 * @date 2026-03-27
 */
@Slf4j
@RestController
@RequestMapping("/device/haik")
public class QsDeviceController extends BladeController {
    @Autowired
    private IQsDeviceService qsDeviceService;

    @Autowired
    private VideoSnapshotUtil videoSnapshotUtil;

    @Value("${file.domain}")
    private String fileDomain;

    @Value("${file.path}")
    private String filePath;

    @Value("${file.prefix}")
    private String filePrefix;

    /**
     * 查询视频监控设备列表
     */
    @GetMapping("/list")
    public IPage<List<QsDevice>> list(QsDevice qsDevice) {
        List<QsDevice> list = qsDeviceService.selectQsDeviceList(qsDevice);
        return new Page<>();
    }

    /**
     * 根据行政区域获取视频监控设备列表
     */
    @GetMapping("/queryListByCivilCode")
    public IPage<List<QsDevice>> queryListByCivilCode(QsDevice qsDevice) {
        List<QsDevice> list = qsDeviceService.queryListByCivilCode(qsDevice);
        return new Page<>();
    }

    /**
     * 获取视频监控设备详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id) {
        return R.data(qsDeviceService.selectQsDeviceById(id));
    }

    /**
     * 新增视频监控设备
     */
    @PostMapping
    public R add(@RequestBody QsDevice qsDevice) {
        return R.data(qsDeviceService.insertQsDevice(qsDevice));
    }

    /**
     * 修改视频监控设备
     */
    @PutMapping
    public R edit(@RequestBody QsDevice qsDevice) {
        return R.data(qsDeviceService.updateQsDevice(qsDevice));
    }

    /**
     * 删除视频监控设备
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids) {
        return R.data(qsDeviceService.deleteQsDeviceByIds(ids));
    }

    /**
     * 状态修改
     */
    @PutMapping("/changeStatus")
    public R changeStatus(@RequestBody QsDevice qsDevice) {
        return R.data(qsDeviceService.updateQsDeviceStatus(qsDevice));
    }

    /**
     * 获取计划记录对应的视频监控设备
     */
    @GetMapping("/listPlanRecord")
    public IPage<List<QsDevice>> listPlanRecordQsDevice(QsDevice qsDevice) {
        List<QsDevice> list = qsDeviceService.listPlanRecordQsDevice(qsDevice);
        return new Page<>();
    }

    /**
     * 设备关联录制计划
     *
     * @param param
     * @return
     */
    @PostMapping("/link")
    public R link(@RequestBody RecordPlanParam param) {
        if (param.getAllLink() != null) {
            if (param.getAllLink()) {
                qsDeviceService.linkAll(param.getPlanId());
            } else {
                qsDeviceService.cleanAll(param.getPlanId());
            }
            return R.success();
        }

        if (param.getDeviceIds() == null) {
            throw new RuntimeException("设备ID不可都为NULL");
        }

        qsDeviceService.link(param.getDeviceIds(), param.getPlanId());

        return R.success();
    }


    /**
     * 设备设置行政区划
     *
     * @param param
     */
    @PostMapping("/region/add")
    public R addChannelToRegion(@RequestBody DeviceToRegionParam param) {
        Assert.notEmpty(param.getDeviceIds(), "设备ID不可为空");
        Assert.hasLength(param.getCivilCode(), "未添加行政区划");
        qsDeviceService.addDeviceToRegion(param.getCivilCode(), param.getDeviceIds());
        return R.success();
    }

    /**
     * 设备删除行政区划
     *
     * @param param
     */
    @PostMapping("/region/delete")
    public R deleteDeviceToRegion(@RequestBody DeviceToRegionParam param) {
        Assert.isTrue(!param.getDeviceIds().isEmpty() || !ObjectUtils.isEmpty(param.getCivilCode()), "参数异常");
        qsDeviceService.deleteDeviceToRegion(param.getCivilCode(), param.getDeviceIds());
        return R.success();
    }


    /**
     * 存在行政区划但无法挂载的设备列表
     */
    @GetMapping("/civilCode/unusual/list")
    public IPage<List<QsDevice>> queryListByCivilCodeForUnusual(QsDevice qsDevice) {
        List<QsDevice> list = qsDeviceService.queryListByCivilCodeForUnusual(qsDevice);
        return new Page<>();
    }

    /**
     * 清除存在行政区划但无法挂载的设备列表
     *
     * @param param
     */
    @PostMapping("/civilCode/unusual/clear")
    public R clearDeviceCivilCode(@RequestBody DeviceToRegionParam param) {
        qsDeviceService.clearDeviceCivilCode(param.getAll(), param.getDeviceIds());
        return R.success();
    }

    /**
     * 获取编码列表
     *
     * @return
     */
    @GetMapping("/network/identification/list")
    public R getNetworkIdentificationTypeList() {
        List<NetworkIdentificationType> list = qsDeviceService.getNetworkIdentificationTypeList();
        return R.success();
    }

    /**
     * 获取编码列表
     *
     * @return
     */
    @GetMapping("/type/list")
    public R getDeviceTypeList() {
        List<DeviceType> list = qsDeviceService.getDeviceTypeList();
        return R.data(list);
    }

    /**
     * 获取行业编码列表
     *
     * @return
     */
    @GetMapping("/industry/list")
    public R getIndustryCodeList() {
        List<IndustryCodeType> list = qsDeviceService.getIndustryCodeList();
        return R.data(list);
    }

    /**
     * 获取关联业务分组设备列表
     */
    @GetMapping("/parent/list")
    public IPage<List<QsDevice>> queryListByParentId(QsDevice qsDevice) {
        List<QsDevice> list = qsDeviceService.queryListByParentId(qsDevice);
        return new Page<>();
    }

    /**
     * 设备设置业务分组
     *
     * @param param
     */
    @PostMapping("/group/add")
    public R addChannelToGroup(@RequestBody DeviceToGroupParam param) {
        Assert.notEmpty(param.getDeviceIds(), "设备ID不可为空");
        Assert.hasLength(param.getParentId(), "未添加上级分组编号");
        Assert.hasLength(param.getBusinessGroup(), "未添加业务分组");
        qsDeviceService.addChannelToGroup(param.getParentId(), param.getBusinessGroup(), param.getDeviceIds());
        return R.success();
    }

    /**
     * 删除业务分组设备
     *
     * @param param
     */
    @PostMapping("/group/delete")
    public R deleteDeviceToGroup(@RequestBody DeviceToGroupParam param) {
        Assert.isTrue(!param.getDeviceIds().isEmpty() || (!ObjectUtils.isEmpty(param.getParentId()) && !ObjectUtils.isEmpty(param.getBusinessGroup())), "参数异常");
        qsDeviceService.deleteDeviceToGroup(param.getParentId(), param.getBusinessGroup(), param.getDeviceIds());
        return R.success();
    }

    /**
     * 存在父节点编号但无法挂载的设备列表
     */
    @GetMapping("/parent/unusual/list")
    public IPage<List<QsDevice>> queryListByParentForUnusual(QsDevice qsDevice) {
        List<QsDevice> list = qsDeviceService.queryListByParentForUnusual(qsDevice);
        return new Page<>();
    }

    /**
     * 清除存在分组节点但无法挂载的设备列表
     *
     * @param param
     */
    @PostMapping("/parent/unusual/clear")
    public R clearDeviceParent(@RequestBody DeviceToGroupParam param) {
        qsDeviceService.clearDeviceParent(param.getAll(), param.getDeviceIds());
        return R.success();
    }

    /**
     * 获取本地mp4截图
     */
    @PutMapping("/getVideoSnapshot/{id}")
    public R getVideoSnapshot(@PathVariable("id") Long id) {
        QsDevice device = qsDeviceService.selectQsDeviceById(id);

        if (device == null) {
            return R.fail("设备不存在");
        }

        if (device.getLiveAddress() == null || device.getLiveAddress().isEmpty()) {
            return R.fail("设备直播地址为空");
        }

        try {
            String videoPath = convertUrlToPath(device.getLiveAddress(), this.fileDomain, this.filePrefix, this.filePath);

            String fileName = "/video_file-" + device.getDeviceCode() + ".jpg";

            String savePath = this.filePath + "/snap" + fileName;

            // 截取第 1 秒的画面
            videoSnapshotUtil.takeSnapshot(videoPath, savePath, 1.0);

            String filePath = fileDomain + filePrefix + "/snap/" + fileName;

            QsDevice qsDevice = new QsDevice();
            qsDevice.setId(id);
            qsDevice.setSnap(filePath);
            qsDeviceService.updateQsDevice(qsDevice);
        } catch (Exception e) {
            log.error("[获取视频截图失败] 设备ID: {}, 错误: {}", id, e.getMessage());
            return R.fail("获取视频截图失败: " + e.getMessage());
        }

        return R.success();
    }

    /**
     * 开始云台控制
     *
     * @param id           设备id
     * @param direction    方向
     * @param controlSpeed 控制速度
     * @return 结果
     */

    @GetMapping("/startPtz/{id}")
    public R startPtz(@PathVariable Long id, @RequestParam String direction, @RequestParam Integer controlSpeed) {
        qsDeviceService.startPtz(id, direction, controlSpeed);
        return R.success();
    }

    /**
     * 结束云台控制
     *
     * @param id           设备id
     * @param direction    方向
     * @param controlSpeed 控制速度
     * @return 结果
     */

    @GetMapping("/endPtz/{id}")
    public R endPtz(@PathVariable Long id, @RequestParam String direction, @RequestParam Integer controlSpeed) {
        qsDeviceService.endPtz(id, direction, controlSpeed);
        return R.success();
    }

    /**
     * 获取预置点列表
     *
     * @param id        设备id
     * @param channelId 通道id
     * @return 预置点列表
     */

    @GetMapping("/preset/list/{id}")
    public R getPresetList(@PathVariable Long id, @RequestParam(required = false) Integer channelId) {
        List<Preset> presetList = qsDeviceService.getPresetList(id, channelId);
        return R.data(presetList);
    }

    /**
     * 设置预置点
     *
     * @param id          设备id
     * @param channelId   通道id
     * @param presetIndex 预置点索引
     * @param presetName  预置点名称
     * @return 结果
     */
    @GetMapping("/preset/set/{id}")
    public R setPreset(@PathVariable Long id, @RequestParam(required = false) Integer channelId, @RequestParam Integer presetIndex, @RequestParam(required = false) String presetName) {
        qsDeviceService.setPreset(id, channelId, presetIndex, presetName);
        return R.success();
    }

    /**
     * 调用预置点
     *
     * @param id          设备id
     * @param channelId   通道id
     * @param presetIndex 预置点索引
     * @param speed       速度
     * @return 结果
     */

    @GetMapping("/preset/goto/{id}")
    public R gotoPreset(@PathVariable Long id, @RequestParam(required = false) Integer channelId, @RequestParam Integer presetIndex, @RequestParam(required = false) Integer speed) {
        qsDeviceService.gotoPreset(id, channelId, presetIndex, speed);
        return R.success();
    }

    /**
     * 删除预置点
     *
     * @param id          设备id
     * @param channelId   通道id
     * @param presetIndex 预置点索引
     * @return 结果
     */

    @GetMapping("/preset/delete/{id}")
    public R deletePreset(@PathVariable Long id, @RequestParam(required = false) Integer channelId, @RequestParam Integer presetIndex) {
        qsDeviceService.deletePreset(id, channelId, presetIndex);
        return R.success();
    }

    /**
     * 灯光控制
     *
     * @param id        设备id
     * @param channelId 通道id
     * @param isOn      true-开, false-关
     * @return 结果
     */

    @GetMapping("/light/{id}")
    public R controlLight(@PathVariable Long id, @RequestParam(required = false) Integer channelId, @RequestParam Boolean isOn) {
        qsDeviceService.controlLight(id, channelId, isOn);
        return R.success();
    }

    /**
     * 雨刷控制
     *
     * @param id        设备id
     * @param channelId 通道id
     * @param isOn      true-开, false-关
     * @return 结果
     */

    @GetMapping("/wiper/{id}")
    public R controlWiper(@PathVariable Long id, @RequestParam(required = false) Integer channelId, @RequestParam Boolean isOn) {
        qsDeviceService.controlWiper(id, channelId, isOn);
        return R.success();
    }

    /**
     * 将网络访问路径转换为本地文件物理路径
     */
    public String convertUrlToPath(String url, String domain, String prefix, String localBasePath) {
        // 步骤 A: 去除域名部分
        // 例如：http://127.0.0.1:9300/statics/...  ->  /statics/...
        if (url.startsWith(domain)) {
            url = url.substring(domain.length());
        }

        // 步骤 B: 去除前缀部分
        // 例如：/statics/2026/...  ->  /2026/...
        if (url.startsWith(prefix)) {
            url = url.substring(prefix.length());
        }

        // 步骤 C: 拼接本地路径
        // 注意：防止路径分隔符重复或缺失
        // localBasePath: D:/ruoyi/uploadPath
        // url: /2026/03/27/...

        if (url.startsWith("/")) {
            return localBasePath + url;
        } else {
            return localBasePath + "/" + url;
        }
    }
}
