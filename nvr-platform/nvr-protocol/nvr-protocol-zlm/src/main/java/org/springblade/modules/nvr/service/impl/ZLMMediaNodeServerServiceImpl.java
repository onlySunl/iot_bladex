package org.springblade.modules.nvr.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.nvr.config.UserSetting;
import org.springblade.modules.nvr.config.ZLMServerConfig;
import org.springblade.modules.nvr.common.CommonCallback;
import org.springblade.modules.nvr.domain.*;
import org.springblade.modules.nvr.domain.dto.StreamProxyResult;
import org.springblade.modules.nvr.domain.dto.ZLMResult;
import org.springblade.modules.nvr.service.IMediaNodeServerService;
import org.springblade.modules.nvr.utils.ZLMRESTfulUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * ZLM 媒体节点服务实现
 * <p>
 * 封装所有与 ZLM 媒体服务器交互的业务逻辑，包括流管理、录像控制、截图等。
 * 通过 {@link #executeWithCheck} 统一处理调用-校验-异常链路，降低各业务方法复杂度。
 * </p>
 *
 * @author fengcheng
 * @date 2026-03-31
 */
@Slf4j
@Service("zlm")
public class ZLMMediaNodeServerServiceImpl implements IMediaNodeServerService {

    @Autowired
    private ZLMRESTfulUtils zlmresTfulUtils;

    @Autowired
    private UserSetting userSetting;

    // ==================== 公共执行方法 ====================

    /**
     * 统一执行 ZLM API 调用并校验结果
     * <p>
     * 封装通用链路：执行 Supplier 获取结果 → 判空 → 校验 code → 异常时抛 RuntimeException。
     * 所有需要"调用 + 校验 + 抛异常"的业务方法统一走此方法，降低重复代码。
     * </p>
     *
     * @param action   执行动作（通常为 lambda 调用 zlmresTfulUtils 方法）
     * @param errorMsg 失败时的错误信息前缀
     * @param <T>      返回值泛型
     * @return 校验通过的 ZLMResult
     * @throws RuntimeException 请求失败或 code != 0 时抛出
     */
    private <T> ZLMResult<T> executeWithCheck(Supplier<ZLMResult<T>> action, String errorMsg) {
        ZLMResult<T> result = action.get();
        if (result == null) {
            throw new RuntimeException(errorMsg + "：请求失败，返回为空");
        }
        if (result.getCode() != 0) {
            throw new RuntimeException(errorMsg + "：code=" + result.getCode() + ", msg=" + result.getMsg());
        }
        return result;
    }

    /**
     * 统一执行 ZLM API 调用并校验结果（带 mediaServer 上下文，日志可追踪 secret）
     * <p>
     * 与 {@link #executeWithCheck(Supplier, String)} 功能一致，额外打印 mediaServer 信息用于定位问题。
     * </p>
     *
     * @param mediaServer ZLM 服务器配置（用于日志追踪）
     * @param action      执行动作
     * @param errorMsg    失败时的错误信息前缀
     * @param <T>         返回值泛型
     * @return 校验通过的 ZLMResult
     * @throws RuntimeException 请求失败或 code != 0 时抛出
     */
    private <T> ZLMResult<T> executeWithCheck(ZlmMediaServer mediaServer, Supplier<ZLMResult<T>> action, String errorMsg) {
        log.debug("执行 ZLM API: server={}:{}, secret={}", mediaServer.getIp(), mediaServer.getHttpPort(), mediaServer.getSecret());
        return executeWithCheck(action, errorMsg);
    }

    /**
     * 构建参数 Map（快捷方法）
     *
     * @param keyValues 键值对交替传入
     * @return 参数 Map
     */
    private Map<String, Object> buildParams(Object... keyValues) {
        Map<String, Object> param = new HashMap<>(keyValues.length / 2 + 1);
        for (int i = 0; i < keyValues.length - 1; i += 2) {
            String key = (String) keyValues[i];
            Object value = keyValues[i + 1];
            if (value != null) {
                param.put(key, value);
            }
        }
        return param;
    }

    // ==================== 业务方法 ====================

    /**
     * 获取媒体流列表
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @return 流信息列表，无数据时返回空列表
     */
    @Override
    public List<StreamInfo> getMediaList(ZlmMediaServer mediaServer, String app, String stream) {
        List<StreamInfo> streamInfoList = new ArrayList<>();

        ZLMResult<List<JSONObject>> zlmResult = zlmresTfulUtils.getMediaList(mediaServer, app, stream);
        if (zlmResult == null || zlmResult.getCode() != 0 || zlmResult.getData() == null) {
            return streamInfoList;
        }

        for (int i = 0; i < zlmResult.getData().size(); i++) {
            JSONObject mediaJSON = zlmResult.getData().get(i);
            MediaInfo mediaInfo = MediaInfo.getInstance(mediaJSON, mediaServer, userSetting.getServerId());
            StreamInfo streamInfo = getStreamInfoByAppAndStream(mediaServer, mediaInfo.getApp(), mediaInfo.getStream(), mediaInfo, null, true);
            if (streamInfo != null) {
                streamInfoList.add(streamInfo);
            }
        }
        return streamInfoList;
    }

    /**
     * 根据 app/stream 构建完整的流播放地址信息
     * <p>
     * 生成 RTMP、RTSP、FLV、WS-FLV、FMP4、WS-FMP4、HLS、WS-HLS、TS、WS-TS、RTC 等全协议播放地址。
     * 支持转码后缀自动追加。
     * </p>
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param mediaInfo   媒体信息（可为 null）
     * @param addr        指定地址（为 null 时使用 mediaServer.streamIp）
     * @param isPlay      是否为播放模式（影响 RTC 地址）
     * @return 完整流信息
     */
    @Override
    public StreamInfo getStreamInfoByAppAndStream(ZlmMediaServer mediaServer, String app, String stream, MediaInfo mediaInfo, String addr, boolean isPlay) {
        StreamInfo streamInfoResult = new StreamInfo();
        streamInfoResult.setStream(stream);
        streamInfoResult.setApp(app);

        // 确定 IP 地址
        if (addr == null) {
            addr = mediaServer.getStreamIp();
        }
        streamInfoResult.setIp(addr);
        streamInfoResult.setServerId(mediaInfo != null ? mediaInfo.getServerId() : userSetting.getServerId());
        streamInfoResult.setMediaServer(mediaServer);

        // 构建编码参数
        String callIdParam = buildCallIdParam(mediaInfo);

        // 设置各协议播放地址
        streamInfoResult.setRtmp(addr, mediaServer.getRtmpPort(), mediaServer.getRtmpSslPort(), app, stream, callIdParam);
        streamInfoResult.setRtsp(addr, mediaServer.getRtspPort(), mediaServer.getRtspSslPort(), app, stream, callIdParam);

        String flvFile = String.format("%s/%s.live.flv%s", app, stream, callIdParam);
        streamInfoResult.setFlv(addr, mediaServer.getHttpPort(), mediaServer.getHttpSslPort(), flvFile);
        streamInfoResult.setWsFlv(addr, mediaServer.getHttpPort(), mediaServer.getHttpSslPort(), flvFile);

        String mp4File = String.format("%s/%s.live.mp4%s", app, stream, callIdParam);
        streamInfoResult.setFmp4(addr, mediaServer.getHttpPort(), mediaServer.getHttpSslPort(), mp4File);
        streamInfoResult.setWsMp4(addr, mediaServer.getHttpPort(), mediaServer.getHttpSslPort(), mp4File);

        streamInfoResult.setHls(addr, mediaServer.getHttpPort(), mediaServer.getHttpSslPort(), app, stream, callIdParam);
        streamInfoResult.setWsHls(addr, mediaServer.getHttpPort(), mediaServer.getHttpSslPort(), app, stream, callIdParam);
        streamInfoResult.setTs(addr, mediaServer.getHttpPort(), mediaServer.getHttpSslPort(), app, stream, callIdParam);
        streamInfoResult.setWsTs(addr, mediaServer.getHttpPort(), mediaServer.getHttpSslPort(), app, stream, callIdParam);
        streamInfoResult.setRtc(addr, mediaServer.getHttpPort(), mediaServer.getHttpSslPort(), app, stream, callIdParam, isPlay);
        streamInfoResult.setMediaInfo(mediaInfo);

        // 处理转码后缀
        appendTranscodeStream(mediaServer, app, stream, addr, isPlay, streamInfoResult);

        return streamInfoResult;
    }

    /**
     * 构建 callId 查询参数字符串
     *
     * @param mediaInfo 媒体信息
     * @return 查询参数字符串（如 "?originTypeStr=h264&videoCodec=h264"），无参数时返回空串
     */
    private String buildCallIdParam(MediaInfo mediaInfo) {
        if (mediaInfo == null) {
            return "";
        }

        Map<String, Object> param = new HashMap<>();
        if (!ObjectUtils.isEmpty(mediaInfo.getOriginTypeStr())) {
            param.put("originTypeStr", mediaInfo.getOriginTypeStr());
        }
        if (!ObjectUtils.isEmpty(mediaInfo.getVideoCodec())) {
            param.put("videoCodec", mediaInfo.getVideoCodec());
        }
        if (!ObjectUtils.isEmpty(mediaInfo.getAudioCodec())) {
            param.put("audioCodec", mediaInfo.getAudioCodec());
        }

        if (param.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder("?");
        int i = 0;
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if (i > 0) sb.append("&");
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            i++;
        }
        return sb.toString();
    }

    /**
     * 追加转码流信息（如果配置了转码后缀）
     *
     * @param mediaServer      ZLM 服务器配置
     * @param app              应用名
     * @param stream           流 ID
     * @param addr             地址
     * @param isPlay           是否播放模式
     * @param streamInfoResult 目标流信息
     */
    private void appendTranscodeStream(ZlmMediaServer mediaServer, String app, String stream, String addr, boolean isPlay, StreamInfo streamInfoResult) {
        if ("broadcast".equalsIgnoreCase(app)) {
            return;
        }
        if (ObjectUtils.isEmpty(mediaServer.getTranscodeSuffix()) || "null".equalsIgnoreCase(mediaServer.getTranscodeSuffix())) {
            return;
        }

        String newStream = stream + "_" + mediaServer.getTranscodeSuffix();
        mediaServer.setTranscodeSuffix(null);
        StreamInfo transcodeStreamInfo = getStreamInfoByAppAndStream(mediaServer, app, newStream, null, addr, isPlay);
        streamInfoResult.setTranscodeStream(transcodeStreamInfo);
    }

    /**
     * 创建拉流代理
     * <p>
     * 向 ZLM 发起拉流请求，返回代理 key。失败时打印拉流地址便于定位。
     * </p>
     *
     * @param mediaServer    ZLM 服务器配置
     * @param streamPullPlay 拉流配置（包含 app/stream/url 等）
     * @return 代理 key
     * @throws RuntimeException 创建失败时抛出
     */
    @Override
    public String startProxy(ZlmMediaServer mediaServer, StreamPullPlay streamPullPlay) {
        String pullUrl = streamPullPlay.getUrl();
        log.info("开始创建ZLM拉流代理，app:{},stream:{},拉流地址:{}", streamPullPlay.getApp(), streamPullPlay.getStream(), pullUrl);

        ZLMResult<StreamProxyResult> zlmResult = zlmresTfulUtils.addStreamProxy(
                mediaServer,
                streamPullPlay.getApp(),
                streamPullPlay.getStream(),
                pullUrl,
                streamPullPlay.isEnable_audio(),
                streamPullPlay.isEnable_mp4(),
                streamPullPlay.getRtp_type(),
                streamPullPlay.getTimeOut()
        );

        if (zlmResult == null || zlmResult.getCode() != 0) {
            String errMsg = String.format("ZLM创建代理失败，拉流地址=%s，code=%s,msg=%s,完整返回:%s",
                    pullUrl,
                    zlmResult != null ? zlmResult.getCode() : "null",
                    zlmResult != null ? zlmResult.getMsg() : "返回为空",
                    zlmResult);
            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        StreamProxyResult data = zlmResult.getData();
        if (data == null) {
            String errMsg = "代理返回数据为空，完整返回：" + zlmResult;
            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        log.info("拉流代理创建成功，key={}", data.getKey());
        return data.getKey();
    }

    /**
     * 停止拉流代理
     *
     * @param mediaServer ZLM 服务器配置
     * @param streamKey   代理 key
     * @throws RuntimeException 请求失败时抛出
     */
    @Override
    public void stopProxy(ZlmMediaServer mediaServer, String streamKey) {
        executeWithCheck(mediaServer,
                () -> zlmresTfulUtils.delStreamProxy(mediaServer, streamKey),
                "停止拉流代理失败");
    }

    /**
     * 获取截图（按 app/stream 构建 URL）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param timeoutSec  超时时间（秒）
     * @param expireSec   过期时间（秒）
     * @param path        保存路径
     * @param fileName    文件名
     */
    @Override
    public void getSnap(ZlmMediaServer mediaServer, String app, String stream, int timeoutSec, int expireSec, String path, String fileName) {
        String streamUrl = buildLocalStreamUrl(mediaServer, app, stream);
        getSnap(mediaServer, streamUrl, timeoutSec, expireSec, path, fileName);
    }

    /**
     * 获取截图（指定流 URL）
     *
     * @param mediaServer ZLM 服务器配置
     * @param streamUrl   流地址
     * @param timeoutSec  超时时间（秒）
     * @param expireSec   过期时间（秒）
     * @param path        保存路径
     * @param fileName    文件名
     */
    @Override
    public void getSnap(ZlmMediaServer mediaServer, String streamUrl, int timeoutSec, int expireSec, String path, String fileName) {
        zlmresTfulUtils.getSnap(mediaServer, streamUrl, timeoutSec, expireSec, path, fileName);
    }

    /**
     * 构建本地流地址（优先 RTSP，降级 HTTP）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @return 本地流 URL
     */
    private String buildLocalStreamUrl(ZlmMediaServer mediaServer, String app, String stream) {
        if (mediaServer.getRtspPort() != 0) {
            return String.format("rtsp://127.0.0.1:%s/%s/%s", mediaServer.getRtspPort(), app, stream);
        }
        return String.format("http://127.0.0.1:%s/%s/%s.live.mp4", mediaServer.getHttpPort(), app, stream);
    }

    /**
     * 关闭 RTP 服务器（异步）
     *
     * @param mediaServer ZLM 服务器配置（为 null 时直接回调 false）
     * @param streamId    流 ID（为空时直接返回）
     * @param callback    完成回调，参数表示是否命中
     */
    @Override
    public void closeRtpServer(ZlmMediaServer mediaServer, String streamId, CommonCallback callback) {
        if (mediaServer == null) {
            log.warn("closeRtpServer: mediaServer 为空");
            invokeCallback(callback, false);
            return;
        }
        if (StringUtils.isEmpty(streamId)) {
            log.warn("closeRtpServer: streamId 为空");
            return;
        }

        Map<String, Object> param = buildParams("stream_id", streamId);
        zlmresTfulUtils.closeRtpServer(mediaServer, param, zlmResult -> {
            if (zlmResult.getCode() == 0) {
                invokeCallback(callback, zlmResult.getHit() >= 1);
                return;
            }
            log.error("关闭RTP Server 失败: {}", zlmResult.getMsg());
            invokeCallback(callback, false);
        });
    }

    /**
     * 安全调用回调
     *
     * @param callback 回调（可为 null）
     * @param value    回调参数
     */
    private void invokeCallback(CommonCallback callback, boolean value) {
        if (callback != null) {
            callback.run(value);
        }
    }

    /**
     * 获取媒体流详细信息
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @return MediaInfo，获取失败返回 null
     */
    @Override
    public MediaInfo getMediaInfo(ZlmMediaServer mediaServer, String app, String stream) {
        ZLMResult<JSONObject> zlmResult = zlmresTfulUtils.getMediaInfo(mediaServer, app, "rtsp", stream);

        if (zlmResult == null || zlmResult.getCode() != 0 || zlmResult.getData() == null) {
            return null;
        }
        if (zlmResult.getData().getString("app") == null) {
            return null;
        }
        return MediaInfo.getInstance(zlmResult.getData(), mediaServer, userSetting.getServerId());
    }

    /**
     * 关闭媒体流
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     */
    @Override
    public void closeStreams(ZlmMediaServer mediaServer, String app, String stream) {
        zlmresTfulUtils.closeStreams(mediaServer, app, stream);
    }

    /**
     * 检查 ZLM 服务器连通性并获取完整配置
     * <p>
     * 连接 ZLM 获取服务端配置（端口、代理端口等），填充到 mediaServer 对象中。
     * </p>
     *
     * @param ip     ZLM 服务器 IP
     * @param port   ZLM 服务器 HTTP 端口
     * @param secret ZLM 服务器密钥
     * @return 配置完整的 ZlmMediaServer 对象
     * @throws RuntimeException 连接或读取配置失败时抛出
     */
    @Override
    public ZlmMediaServer checkMediaServer(String ip, int port, String secret) {
        ZlmMediaServer mediaServer = new ZlmMediaServer();
        mediaServer.setServerId(userSetting.getServerId());
        mediaServer.setIp(ip);
        mediaServer.setHttpPort(port);
        mediaServer.setSecret(secret);

        ZLMResult<List<JSONObject>> mediaServerConfigResult =
                executeWithCheck(mediaServer,
                        () -> zlmresTfulUtils.getMediaServerConfig(mediaServer),
                        "连接ZLM失败");

        List<JSONObject> configList = mediaServerConfigResult.getData();
        if (configList == null || configList.isEmpty()) {
            throw new RuntimeException("读取ZLM配置失败：返回数据为空");
        }

        ZLMServerConfig zlmServerConfig = JSON.parseObject(configList.get(0).toJSONString(), ZLMServerConfig.class);
        if (zlmServerConfig == null) {
            throw new RuntimeException("读取ZLM配置失败：解析为空");
        }

        // 填充服务端返回的配置
        mediaServer.setId(zlmServerConfig.getGeneralMediaServerId());
        mediaServer.setHttpSslPort(zlmServerConfig.getHttpSSLport());
        mediaServer.setRtmpPort(zlmServerConfig.getRtmpPort());
        mediaServer.setRtmpSslPort(zlmServerConfig.getRtmpSslPort());
        mediaServer.setRtspPort(zlmServerConfig.getRtspPort());
        mediaServer.setRtspSslPort(zlmServerConfig.getRtspSSlport());
        mediaServer.setRtpProxyPort(zlmServerConfig.getRtpProxyPort());
        mediaServer.setStreamIp(ip);
        mediaServer.setHookIp(zlmServerConfig.getHookIp());
        mediaServer.setSdpIp(ip);
        mediaServer.setType("zlm");

        return mediaServer;
    }

    /**
     * 删除录像目录
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param date        日期（如 2026-07-01）
     * @param fileName    文件名
     * @return true 表示删除成功
     * @throws RuntimeException 删除失败时抛出
     */
    @Override
    public boolean deleteRecordDirectory(ZlmMediaServer mediaServer, String app, String stream, String date, String fileName) {
        log.info("[zlm-deleteRecordDirectory] 删除磁盘文件, server:{} {}:{}->{}/{}", mediaServer.getId(), app, stream, date, fileName);

        executeWithCheck(mediaServer,
                () -> zlmresTfulUtils.deleteRecordDirectory(mediaServer, app, stream, date, fileName),
                "删除磁盘文件失败");

        return true;
    }

    /**
     * 获取录像文件下载路径
     *
     * @param mediaServerItem ZLM 服务器配置
     * @param recordInfo      录像信息
     * @return 下载文件信息（含 HTTP/HTTPS 路径）
     */
    @Override
    public DownloadFileInfo getDownloadFilePath(ZlmMediaServer mediaServerItem, RecordInfo recordInfo) {
        DownloadFileInfo info = new DownloadFileInfo();
        String filePath = recordInfo.getFilePath();

        // HTTP 路径
        info.setHttpPath(String.format("http://%s:%s/index/api/downloadFile?file_path=%s",
                mediaServerItem.getStreamIp(), mediaServerItem.getHttpPort(), filePath));

        // HTTPS 路径（仅当 SSL 端口可用时）
        if (mediaServerItem.getHttpSslPort() > 0) {
            info.setHttpsPath(String.format("https://%s:%s/index/api/downloadFile?file_path=%s",
                    mediaServerItem.getStreamIp(), mediaServerItem.getHttpSslPort(), filePath));
        }

        return info;
    }

    /**
     * 跳转到录像指定时间戳
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param stamp       时间戳（秒）
     * @param schema      协议
     * @throws RuntimeException 请求失败时抛出
     */
    @Override
    public void seekRecordStamp(ZlmMediaServer mediaServer, String app, String stream, Double stamp, String schema) {
        executeWithCheck(mediaServer,
                () -> zlmresTfulUtils.seekRecordStamp(mediaServer, app, stream, stamp, schema),
                "跳转录像时间戳失败");
    }

    /**
     * 设置录像播放倍速
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param speed       倍速
     * @param schema      协议
     * @throws RuntimeException 请求失败时抛出
     */
    @Override
    public void setRecordSpeed(ZlmMediaServer mediaServer, String app, String stream, Integer speed, String schema) {
        executeWithCheck(mediaServer,
                () -> zlmresTfulUtils.setRecordSpeed(mediaServer, app, stream, speed, schema),
                "设置录像倍速失败");
    }

    /**
     * 开始手动录像
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @throws RuntimeException 请求失败时抛出
     */
    @Override
    public void startRecord(ZlmMediaServer mediaServer, String app, String stream) {
        executeWithCheck(mediaServer,
                () -> zlmresTfulUtils.startRecord(mediaServer, app, stream),
                "开始录像失败");
    }

    /**
     * 停止手动录像
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @throws RuntimeException 请求失败时抛出
     */
    @Override
    public void stopRecord(ZlmMediaServer mediaServer, String app, String stream) {
        executeWithCheck(mediaServer,
                () -> zlmresTfulUtils.stopRecord(mediaServer, app, stream),
                "停止录像失败");
    }

    /**
     * 获取线程负载
     *
     * @param mediaServer ZLM 服务器配置
     * @return ZLMResult
     */
    @Override
    public ZLMResult<?> getThreadsLoad(ZlmMediaServer mediaServer) {
        return zlmresTfulUtils.getThreadsLoad(mediaServer);
    }

    /**
     * 获取工作线程负载
     *
     * @param mediaServer ZLM 服务器配置
     * @return ZLMResult
     */
    @Override
    public ZLMResult<?> getWorkThreadsLoad(ZlmMediaServer mediaServer) {
        return zlmresTfulUtils.getWorkThreadsLoad(mediaServer);
    }

    /**
     * 重启 ZLM 流媒体服务器
     *
     * @param mediaServer ZLM 服务器配置
     */
    @Override
    public void restartServer(ZlmMediaServer mediaServer) {
        zlmresTfulUtils.restartServer(mediaServer);
    }

    /**
     * TCP 主动连接 RTP 服务器
     *
     * @param mediaServer ZLM 服务器配置
     * @param address     目标地址
     * @param port        目标端口
     * @param stream      流 ID
     * @return true 表示连接成功
     */
    @Override
    public Boolean connectRtpServer(ZlmMediaServer mediaServer, String address, int port, String stream) {
        ZLMResult<?> zlmResult = zlmresTfulUtils.connectRtpServer(mediaServer, address, port, stream);
        log.info("[TCP主动连接对方] server:{}->{}, result:{}", mediaServer.getIp(), address + ":" + port, zlmResult);
        return zlmResult != null && zlmResult.getCode() == 0;
    }
}