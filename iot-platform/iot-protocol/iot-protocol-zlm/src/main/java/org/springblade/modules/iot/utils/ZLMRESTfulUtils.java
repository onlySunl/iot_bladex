package org.springblade.modules.iot.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springblade.modules.iot.domain.ZlmMediaServer;
import org.springblade.modules.iot.domain.dto.StreamProxyResult;
import org.springblade.modules.iot.domain.dto.ZLMResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * ZLMediaKit RESTful API 工具类
 * <p>
 * 封装所有与 ZLM 服务器的 HTTP 交互，包括媒体流管理、RTP 服务、录像控制等。
 * 所有业务方法保持原有签名不变，内部通过统一的底层方法完成网络请求和 JSON 解析。
 * </p>
 *
 * @author IoT Platform
 */
@Slf4j
@Component
public class ZLMRESTfulUtils {

    // ==================== 常量定义 ====================

    /** 默认 VHOST */
    private static final String DEFAULT_VHOST = "__defaultVhost__";
    /** 录像类型：手动录像 */
    private static final String RECORD_TYPE_MANUAL = "1";
    /** API 路径模板 */
    private static final String API_URL_TEMPLATE = "http://%s:%s/index/api/%s";
    /** 默认连接超时（秒） */
    private static final int DEFAULT_CONNECT_TIMEOUT = 8;
    /** 默认读取超时（秒） */
    private static final int DEFAULT_READ_TIMEOUT = 10;
    /** 连接池最大空闲连接数 */
    private static final int MAX_IDLE_CONNECTIONS = 16;
    /** 连接池保活时间（分钟） */
    private static final int KEEP_ALIVE_DURATION = 5;
    /** 拉流重试次数 */
    private static final int RETRY_COUNT = 3;
    /** 强制关闭流 */
    private static final int FORCE_CLOSE = 1;

    // ==================== 静态 TypeReference 复用 ====================

    private static final TypeReference<ZLMResult<Void>> TYPE_ZLM_VOID = new TypeReference<ZLMResult<Void>>() {};

    private static final TypeReference<ZLMResult<StreamProxyResult>> TYPE_ZLM_RESULT = new TypeReference<ZLMResult<StreamProxyResult>>() {};
    private static final TypeReference<ZLMResult<List<JSONObject>>> TYPE_ZLM_LIST_JSON = new TypeReference<ZLMResult<List<JSONObject>>>() {};
    private static final TypeReference<ZLMResult<List<Map<String, Object>>>> TYPE_ZLM_LIST_MAP = new TypeReference<ZLMResult<List<Map<String, Object>>>>() {};

    // ==================== 回调接口 ====================

    /**
     * 通用请求回调（返回原始 JSON 字符串）
     */
    public interface RequestCallback {
        void run(String response);
    }

    /**
     * 结果回调（返回解析后的 ZLMResult）
     */
    public interface ResultCallback {
        void run(ZLMResult response);
    }

    // ==================== OkHttp Client 构建 ====================

    /**
     * 根据读取超时时间构建独立的 OkHttpClient 实例
     * <p>
     * 修复原单例缺陷：原全局 client 只初始化一次，不同接口传入不同 readTimeout 不会生效。
     * 现在每次根据超时参数构建独立 Client，共享连接池配置。
     * </p>
     *
     * @param readTimeout 读取超时时间（秒），为 null 时使用默认值
     * @return 配置好的 OkHttpClient 实例
     */
    private OkHttpClient buildClient(Integer readTimeout) {
        if (readTimeout == null) {
            readTimeout = DEFAULT_READ_TIMEOUT;
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(readTimeout, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MINUTES));

        if (log.isDebugEnabled()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> log.debug("http请求参数：" + message));
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(logging);
        }

        return builder.build();
    }

    // ==================== 底层统一方法 ====================

    /**
     * 统一 POST 请求（同步）
     * <p>
     * 封装 URL 拼接、secret 自动填充、表单参数组装、异常日志。
     * 所有同步业务方法通过此方法发起请求。
     * </p>
     *
     * @param mediaServer ZLM 服务器配置
     * @param api         API 接口名称
     * @param param       请求参数
     * @param readTimeout 读取超时（秒），可为 null
     * @return 响应 JSON 字符串，失败返回 null
     */
    private String doPost(ZlmMediaServer mediaServer, String api, Map<String, Object> param, Integer readTimeout) {
        if (mediaServer == null) {
            log.warn("mediaServer 为空，无法发起请求");
            return null;
        }

        String url = buildApiUrl(mediaServer, api);
        OkHttpClient client = buildClient(readTimeout);
        FormBody body = buildFormBody(mediaServer.getSecret(), param);
        Request request = new Request.Builder().post(body).url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    return responseBody.string();
                }
            } else {
                log.error("[{}] 请求失败: {} {}", url, response.code(), response.message());
            }
        } catch (SocketTimeoutException e) {
            log.error("读取ZLM数据超时: {}, {}", url, e.getMessage());
        } catch (ConnectException e) {
            log.error("连接ZLM失败: {}, {}", url, e.getMessage());
        } catch (IOException e) {
            log.error("[{}] 请求失败: {}", url, e.getMessage());
        } catch (Exception e) {
            log.error("访问ZLM异常: {}, {}", url, e.getMessage());
        }
        return null;
    }

    /**
     * 统一 POST 请求（异步）
     * <p>
     * 封装 URL 拼接、secret 自动填充、表单参数组装、异常日志。
     * 所有异步业务方法通过此方法发起请求。
     * </p>
     *
     * @param mediaServer ZLM 服务器配置
     * @param api         API 接口名称
     * @param param       请求参数
     * @param callback    请求回调
     */
    private void doPostAsync(ZlmMediaServer mediaServer, String api, Map<String, Object> param, RequestCallback callback) {
        if (mediaServer == null) {
            log.warn("mediaServer 为空，无法发起异步请求");
            if (callback != null) {
                callback.run(null);
            }
            return;
        }

        String url = buildApiUrl(mediaServer, api);
        OkHttpClient client = buildClient(null);
        FormBody body = buildFormBody(mediaServer.getSecret(), param);
        Request request = new Request.Builder().post(body).url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try (response) {
                    if (response.isSuccessful()) {
                        String responseStr = Objects.requireNonNull(response.body()).string();
                        if (callback != null) {
                            callback.run(responseStr);
                        }
                    } else {
                        log.error("[{}] 异步请求失败: {} {}", url, response.code(), response.message());
                        if (callback != null) {
                            callback.run(null);
                        }
                    }
                } catch (IOException e) {
                    log.error("[{}] 异步请求失败: {}", url, e.getMessage());
                    if (callback != null) {
                        callback.run(null);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("连接ZLM失败: {}, {}", call.request().url(), e.getMessage());
                if (e instanceof SocketTimeoutException) {
                    log.error("读取ZLM数据超时: {}", call.request().url());
                }
                if (e instanceof ConnectException) {
                    log.error("连接ZLM失败: {}", call.request().url());
                }
                if (callback != null) {
                    callback.run(null);
                }
            }
        });
    }

    /**
     * 统一 JSON 解析方法
     * <p>
     * 解决 TypeReference 重复 new、解析逻辑冗余的问题。
     * 解析失败统一返回 ZLMResult.getFailForMediaServer()。
     * </p>
     *
     * @param response      响应 JSON 字符串
     * @param typeReference 目标类型
     * @param <T>           泛型类型
     * @return 解析结果，失败返回失败的 ZLMResult
     */
    private <T> ZLMResult<T> parseResult(String response, TypeReference<ZLMResult<T>> typeReference) {
        if (response == null) {
            return ZLMResult.getFailForMediaServer();
        }
        ZLMResult<T> result = JSON.parseObject(response, typeReference);
        return result != null ? result : ZLMResult.getFailForMediaServer();
    }

    /**
     * 构建 API URL
     *
     * @param mediaServer ZLM 服务器配置
     * @param api         API 接口名称
     * @return 完整 URL
     */
    private String buildApiUrl(ZlmMediaServer mediaServer, String api) {
        return String.format(API_URL_TEMPLATE, mediaServer.getIp(), mediaServer.getHttpPort(), api);
    }

    /**
     * 构建表单请求体（自动填充 secret）
     *
     * @param secret ZLM 服务器密钥
     * @param param  请求参数
     * @return FormBody
     */
    private FormBody buildFormBody(String secret, Map<String, Object> param) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("secret", secret);
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                if (entry.getValue() != null) {
                    builder.add(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return builder.build();
    }

    /**
     * 构建请求参数 Map
     *
     * @param keyValues 键值对（交替传入 key, value, key, value...）
     * @return 参数 Map
     */
    private Map<String, Object> buildParams(Object... keyValues) {
        Map<String, Object> map = new HashMap<>(keyValues.length / 2 + 1);
        for (int i = 0; i < keyValues.length - 1; i += 2) {
            String key = (String) keyValues[i];
            Object value = keyValues[i + 1];
            if (value != null) {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * URL 安全编码
     * <p>
     * 统一处理 URLEncoder，消除受检异常到处 try-catch。
     * </p>
     *
     * @param value 待编码字符串
     * @return 编码后的字符串
     */
    private String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URL编码失败: " + value, e);
        }
    }

    /**
     * 统一 GET 请求下载图片/文件
     * <p>
     * 修复原缺陷：增加 mediaServer/secret 判空、URL 空指针防护、response.body 空强转。
     * </p>
     *
     * @param mediaServer ZLM 服务器配置
     * @param api         API 接口名称
     * @param params      请求参数
     * @param targetPath  目标保存路径
     * @param fileName    文件名
     */
    private void doGetForFile(ZlmMediaServer mediaServer, String api, Map<String, Object> params, String targetPath, String fileName) {
        if (mediaServer == null) {
            log.warn("mediaServer 为空，无法发起 GET 请求");
            return;
        }
        if (mediaServer.getSecret() == null || mediaServer.getSecret().isEmpty()) {
            log.warn("mediaServer secret 为空，无法发起 GET 请求");
            return;
        }

        String url = buildApiUrl(mediaServer, api);
        HttpUrl parseUrl = HttpUrl.parse(url);
        if (parseUrl == null) {
            log.error("URL 解析失败: {}", url);
            return;
        }

        HttpUrl.Builder httpBuilder = parseUrl.newBuilder();
        httpBuilder.addQueryParameter("secret", mediaServer.getSecret());
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() != null) {
                    httpBuilder.addQueryParameter(entry.getKey(), entry.getValue().toString());
                }
            }
        }

        Request request = new Request.Builder().url(httpBuilder.build()).build();

        if (log.isDebugEnabled()) {
            log.debug("GET请求: {}", request);
        }

        OkHttpClient client = buildClient(null);
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error("[{}] 请求失败: {} {}", url, response.code(), response.message());
                return;
            }
            if (targetPath == null) {
                log.error("目标路径为空，无法保存文件");
                return;
            }

            ResponseBody body = response.body();
            if (body == null) {
                log.error("[{}] 响应体为空", url);
                return;
            }

            File snapFolder = new File(targetPath);
            if (!snapFolder.exists() && !snapFolder.mkdirs()) {
                log.warn("{}路径创建失败", snapFolder.getAbsolutePath());
                return;
            }

            File snapFile = new File(targetPath + File.separator + fileName);
            try (FileOutputStream outStream = new FileOutputStream(snapFile)) {
                outStream.write(body.bytes());
                outStream.flush();
            }
        } catch (ConnectException e) {
            log.error("连接ZLM失败: {}, {}", e.getCause() != null ? e.getCause().getMessage() : "", e.getMessage());
            log.info("请检查media配置并确认ZLM已启动...");
        } catch (IOException e) {
            log.error("[{}] 请求失败: {}", url, e.getMessage());
        }
    }

    // ==================== 业务方法 ====================

    /**
     * 检查媒体流是否在线（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param schema      协议（rtsp/rtmp/hls 等）
     * @return ZLMResult，code=0 表示成功
     */
    public ZLMResult<Void> isMediaOnline(ZlmMediaServer mediaServer, String app, String stream, String schema) {
        Map<String, Object> param = new HashMap<>();
        if (app != null) param.put("app", app);
        if (stream != null) param.put("stream", stream);
        if (schema != null) param.put("schema", schema);
        param.put("vhost", DEFAULT_VHOST);

        String response = doPost(mediaServer, "isMediaOnline", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 获取媒体流列表（支持异步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param schema      协议
     * @param callback    异步回调，为 null 时同步返回
     * @return 同步模式下返回 ZLMResult；异步模式下返回 null（结果通过回调返回）
     */
    public ZLMResult<List<JSONObject>> getMediaList(ZlmMediaServer mediaServer, String app, String stream, String schema, ResultCallback callback) {
        Map<String, Object> param = new HashMap<>();
        if (app != null) param.put("app", app);
        if (stream != null) param.put("stream", stream);
        if (schema != null) param.put("schema", schema);
        param.put("vhost", DEFAULT_VHOST);

        if (callback != null) {
            doPostAsync(mediaServer, "getMediaList", param, responseStr -> {
                if (responseStr == null) {
                    callback.run(ZLMResult.getFailForMediaServer());
                } else {
                    ZLMResult<List<JSONObject>> result = parseResult(responseStr, TYPE_ZLM_LIST_JSON);
                    callback.run(result);
                }
            });
            return null;
        }

        String response = doPost(mediaServer, "getMediaList", param, null);
        return parseResult(response, TYPE_ZLM_LIST_JSON);
    }

    /**
     * 获取媒体流列表（简化版，同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @return ZLMResult
     */
    public ZLMResult<List<JSONObject>> getMediaList(ZlmMediaServer mediaServer, String app, String stream) {
        return getMediaList(mediaServer, app, stream, null, null);
    }

    /**
     * 获取媒体流详细信息（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param schema      协议
     * @param stream      流 ID
     * @return ZLMResult，data 字段包含详细信息
     */
    public ZLMResult<JSONObject> getMediaInfo(ZlmMediaServer mediaServer, String app, String schema, String stream) {
        Map<String, Object> param = buildParams("app", app, "schema", schema, "stream", stream, "vhost", DEFAULT_VHOST);
        String response = doPost(mediaServer, "getMediaInfo", param, null);

        if (response == null) {
            return ZLMResult.getFailForMediaServer();
        }
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject == null) {
            return ZLMResult.getFailForMediaServer();
        }
        ZLMResult<JSONObject> result = new ZLMResult<>();
        result.setCode(0);
        result.setData(jsonObject);
        return result;
    }

    /**
     * 获取 RTP 流信息（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param streamId    流 ID
     * @return ZLMResult
     */
    public ZLMResult<Void> getRtpInfo(ZlmMediaServer mediaServer, String streamId) {
        Map<String, Object> param = buildParams("stream_id", streamId);
        String response = doPost(mediaServer, "getRtpInfo", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 添加 FFmpeg 拉流源（同步）
     *
     * @param mediaServer   ZLM 服务器配置
     * @param srcUrl        源地址
     * @param dstUrl        目标地址
     * @param timeoutSec    超时时间（秒）
     * @param enableAudio   是否启用音频
     * @param enableMp4     是否录制
     * @param ffmpegCmdKey  FFmpeg 命令 key
     * @return ZLMResult
     */
    public ZLMResult<Void> addFFmpegSource(ZlmMediaServer mediaServer, String srcUrl, String dstUrl, Integer timeoutSec, boolean enableAudio, boolean enableMp4, String ffmpegCmdKey) {
        if (!"ffmpeg.cmd_shout".equals(ffmpegCmdKey)) {
            srcUrl = urlEncode(srcUrl);
        }
        Map<String, Object> param = buildParams(
                "src_url", srcUrl,
                "dst_url", dstUrl,
                "timeout_ms", timeoutSec * 1000,
                "enable_mp4", enableMp4 ? 1 : 0,
                "ffmpeg_cmd_key", ffmpegCmdKey
        );
        String response = doPost(mediaServer, "addFFmpegSource", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 删除 FFmpeg 拉流源（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param key         拉流 key
     * @return ZLMResult
     */
    public ZLMResult<Void> delFFmpegSource(ZlmMediaServer mediaServer, String key) {
        Map<String, Object> param = buildParams("key", key);
        String response = doPost(mediaServer, "delFFmpegSource", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 删除流代理（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param key         代理 key
     * @return ZLMResult
     */
    public ZLMResult<Void> delStreamProxy(ZlmMediaServer mediaServer, String key) {
        Map<String, Object> param = buildParams("key", key);
        String response = doPost(mediaServer, "delStreamProxy", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 获取 ZLM 服务器配置（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @return ZLMResult，data 为配置列表
     */
    public ZLMResult<List<JSONObject>> getMediaServerConfig(ZlmMediaServer mediaServer) {
        String response = doPost(mediaServer, "getServerConfig", null, null);
        return parseResult(response, TYPE_ZLM_LIST_JSON);
    }

    /**
     * 设置 ZLM 服务器配置（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param param       配置参数
     * @return ZLMResult
     */
    public ZLMResult<Void> setServerConfig(ZlmMediaServer mediaServer, Map<String, Object> param) {
        String response = doPost(mediaServer, "setServerConfig", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 开启 RTP 服务器（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param param       参数（stream_id, port 等）
     * @return ZLMResult
     */
    public ZLMResult<Void> openRtpServer(ZlmMediaServer mediaServer, Map<String, Object> param) {
        String response = doPost(mediaServer, "openRtpServer", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 关闭 RTP 服务器（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param param       参数（stream_id）
     * @return ZLMResult
     */
    public ZLMResult<Void> closeRtpServer(ZlmMediaServer mediaServer, Map<String, Object> param) {
        String response = doPost(mediaServer, "closeRtpServer", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 关闭 RTP 服务器（异步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param param       参数
     * @param callback    异步回调
     */
    public void closeRtpServer(ZlmMediaServer mediaServer, Map<String, Object> param, ResultCallback callback) {
        doPostAsync(mediaServer, "closeRtpServer", param, response -> {
            if (response == null) {
                callback.run(ZLMResult.getFailForMediaServer());
            } else {
                callback.run(parseResult(response, TYPE_ZLM_VOID));
            }
        });
    }

    /**
     * 列出所有 RTP 服务器（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @return ZLMResult，data 为 RTP 服务器列表
     */
    public ZLMResult<List<Map<String, Object>>> listRtpServer(ZlmMediaServer mediaServer) {
        String response = doPost(mediaServer, "listRtpServer", null, null);
        return parseResult(response, TYPE_ZLM_LIST_MAP);
    }

    /**
     * 开始发送 RTP（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param param       参数
     * @return ZLMResult
     */
    public ZLMResult<Void> startSendRtp(ZlmMediaServer mediaServer, Map<String, Object> param) {
        String response = doPost(mediaServer, "startSendRtp", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 开始被动发送 RTP（同步/异步）
     * <p>
     * 修复双请求 BUG：有回调时仅发起异步请求，不再同步发起。
     * </p>
     *
     * @param mediaServer ZLM 服务器配置
     * @param param       参数
     * @param callback    异步回调，为 null 时同步返回
     * @return 同步模式下返回 ZLMResult；异步模式下返回 null
     */
    public ZLMResult<Void> startSendRtpPassive(ZlmMediaServer mediaServer, Map<String, Object> param, ResultCallback callback) {
        if (callback != null) {
            doPostAsync(mediaServer, "startSendRtpPassive", param, responseStr -> {
                if (responseStr == null) {
                    callback.run(ZLMResult.getFailForMediaServer());
                } else {
                    callback.run(parseResult(responseStr, TYPE_ZLM_VOID));
                }
            });
            return null;
        }

        String response = doPost(mediaServer, "startSendRtpPassive", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 开始对讲 RTP（异步）
     * <p>
     * 修复双请求 BUG：仅发起异步请求。
     * </p>
     *
     * @param mediaServer ZLM 服务器配置
     * @param param       参数
     * @param callback    异步回调
     */
    public void startSendRtpTalk(ZlmMediaServer mediaServer, Map<String, Object> param, ResultCallback callback) {
        doPostAsync(mediaServer, "startSendRtpTalk", param, responseStr -> {
            if (responseStr == null) {
                callback.run(ZLMResult.getFailForMediaServer());
            } else {
                callback.run(parseResult(responseStr, TYPE_ZLM_VOID));
            }
        });
    }

    /**
     * 停止发送 RTP（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param param       参数
     * @return ZLMResult
     */
    public ZLMResult<Void> stopSendRtp(ZlmMediaServer mediaServer, Map<String, Object> param) {
        String response = doPost(mediaServer, "stopSendRtp", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 重启 ZLM 服务器（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @return ZLMResult
     */
    public ZLMResult<Void> restartServer(ZlmMediaServer mediaServer) {
        String response = doPost(mediaServer, "restartServer", null, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 添加流代理（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param url         拉流地址
     * @param enableAudio 是否启用音频
     * @param enableMp4   是否录制
     * @param rtpType     RTP 类型
     * @param timeOut     超时时间（秒）
     * @return ZLMResult
     */
    public ZLMResult<StreamProxyResult> addStreamProxy(ZlmMediaServer mediaServer, String app, String stream, String url, boolean enableAudio, boolean enableMp4, String rtpType, Integer timeOut) {
        Map<String, Object> param = buildParams(
                "vhost", DEFAULT_VHOST,
                "app", app,
                "stream", stream,
                "url", url,
                "enable_mp4", enableMp4 ? 1 : 0,
                "enable_audio", enableAudio ? 1 : 0,
                "rtp_type", rtpType,
                "timeout_sec", timeOut,
                "retry_count", RETRY_COUNT
        );
        String response = doPost(mediaServer, "addStreamProxy", param, null);
        return parseResult(response, TYPE_ZLM_RESULT);
    }

    /**
     * 关闭流（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @return ZLMResult
     */
    public ZLMResult<Void> closeStreams(ZlmMediaServer mediaServer, String app, String stream) {
        Map<String, Object> param = buildParams("vhost", DEFAULT_VHOST, "app", app, "stream", stream, "force", FORCE_CLOSE);
        String response = doPost(mediaServer, "close_streams", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 获取所有会话（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @return ZLMResult，data 为会话列表
     */
    public ZLMResult<List<Map<String, Object>>> getAllSession(ZlmMediaServer mediaServer) {
        String response = doPost(mediaServer, "getAllSession", null, null);
        return parseResult(response, TYPE_ZLM_LIST_MAP);
    }

    /**
     * 踢出会话
     *
     * @param mediaServer    ZLM 服务器配置
     * @param localPortSStr  本地端口
     * @return ZLMResult，上层可感知调用结果
     */
    public ZLMResult<Void> kickSessions(ZlmMediaServer mediaServer, String localPortSStr) {
        Map<String, Object> param = buildParams("local_port", localPortSStr);
        String response = doPost(mediaServer, "kick_sessions", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 获取截图（异步，结果保存到文件）
     *
     * @param mediaServer ZLM 服务器配置
     * @param streamUrl   流地址
     * @param timeoutSec  超时时间（秒）
     * @param expireSec   过期时间（秒）
     * @param targetPath  保存路径
     * @param fileName    文件名
     */
    public void getSnap(ZlmMediaServer mediaServer, String streamUrl, int timeoutSec, int expireSec, String targetPath, String fileName) {
        Map<String, Object> param = buildParams("url", streamUrl, "timeout_sec", timeoutSec, "expire_sec", expireSec, "async", 1);
        doGetForFile(mediaServer, "getSnap", param, targetPath, fileName);
    }

    /**
     * 暂停 RTP 检查（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param streamId    流 ID
     * @return ZLMResult
     */
    public ZLMResult<Void> pauseRtpCheck(ZlmMediaServer mediaServer, String streamId) {
        Map<String, Object> param = buildParams("stream_id", streamId);
        String response = doPost(mediaServer, "pauseRtpCheck", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 恢复 RTP 检查（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param streamId    流 ID
     * @return ZLMResult
     */
    public ZLMResult<Void> resumeRtpCheck(ZlmMediaServer mediaServer, String streamId) {
        Map<String, Object> param = buildParams("stream_id", streamId);
        String response = doPost(mediaServer, "resumeRtpCheck", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 连接 RTP 服务器（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param dstUrl      目标地址
     * @param dstPort     目标端口
     * @param streamId    流 ID
     * @return ZLMResult
     */
    public ZLMResult<Void> connectRtpServer(ZlmMediaServer mediaServer, String dstUrl, int dstPort, String streamId) {
        Map<String, Object> param = buildParams("dst_url", dstUrl, "dst_port", dstPort, "stream_id", streamId);
        String response = doPost(mediaServer, "connectRtpServer", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 更新 RTP 服务器 SSRC（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param streamId    流 ID
     * @param ssrc        SSRC
     * @return ZLMResult
     */
    public ZLMResult<Void> updateRtpServerSSRC(ZlmMediaServer mediaServer, String streamId, String ssrc) {
        Map<String, Object> param = buildParams("ssrc", ssrc, "stream_id", streamId);
        String response = doPost(mediaServer, "updateRtpServerSSRC", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 删除录像目录（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param date        日期（如 2026-07-01）
     * @param fileName    文件名
     * @return ZLMResult
     */
    public ZLMResult<Void> deleteRecordDirectory(ZlmMediaServer mediaServer, String app, String stream, String date, String fileName) {
        Map<String, Object> param = buildParams(
                "vhost", DEFAULT_VHOST,
                "app", app,
                "stream", stream,
                "period", date,
                "name", fileName
        );
        String response = doPost(mediaServer, "deleteRecordDirectory", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 加载 MP4 文件（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param datePath    文件路径
     * @return ZLMResult
     */
    public ZLMResult<Void> loadMP4File(ZlmMediaServer mediaServer, String app, String stream, String datePath) {
        Map<String, Object> param = buildParams(
                "vhost", DEFAULT_VHOST,
                "app", app,
                "stream", stream,
                "file_path", datePath,
                "file_repeat", "0"
        );
        String response = doPost(mediaServer, "loadMP4File", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 设置录像播放倍速（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param speed       倍速
     * @param schema      协议
     * @return ZLMResult
     */
    public ZLMResult<Void> setRecordSpeed(ZlmMediaServer mediaServer, String app, String stream, int speed, String schema) {
        Map<String, Object> param = buildParams(
                "vhost", DEFAULT_VHOST,
                "app", app,
                "stream", stream,
                "speed", speed,
                "schema", schema
        );
        String response = doPost(mediaServer, "setRecordSpeed", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 跳转到录像指定时间戳（同步）
     * <p>
     * 修复 BigDecimal 精度隐患：使用 BigDecimal.valueOf(stamp) 替代 new BigDecimal(stamp)。
     * </p>
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @param stamp       时间戳（秒）
     * @param schema      协议
     * @return ZLMResult
     */
    public ZLMResult<Void> seekRecordStamp(ZlmMediaServer mediaServer, String app, String stream, Double stamp, String schema) {
        Map<String, Object> param = buildParams(
                "vhost", DEFAULT_VHOST,
                "app", app,
                "stream", stream,
                "stamp", BigDecimal.valueOf(stamp),
                "schema", schema
        );
        String response = doPost(mediaServer, "seekRecordStamp", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 开始录像（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @return ZLMResult
     */
    public ZLMResult<Void> startRecord(ZlmMediaServer mediaServer, String app, String stream) {
        Map<String, Object> param = buildParams("vhost", DEFAULT_VHOST, "app", app, "stream", stream, "type", RECORD_TYPE_MANUAL);
        String response = doPost(mediaServer, "startRecord", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 停止录像（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @return ZLMResult
     */
    public ZLMResult<Void> stopRecord(ZlmMediaServer mediaServer, String app, String stream) {
        Map<String, Object> param = buildParams("vhost", DEFAULT_VHOST, "app", app, "stream", stream, "type", RECORD_TYPE_MANUAL);
        String response = doPost(mediaServer, "stopRecord", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 查询是否正在录像（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @param app         应用名
     * @param stream      流 ID
     * @return ZLMResult
     */
    public ZLMResult<Void> isRecording(ZlmMediaServer mediaServer, String app, String stream) {
        Map<String, Object> param = buildParams("vhost", DEFAULT_VHOST, "app", app, "stream", stream, "type", RECORD_TYPE_MANUAL);
        String response = doPost(mediaServer, "isRecording", param, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 获取线程负载（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @return ZLMResult
     */
    public ZLMResult<Void> getThreadsLoad(ZlmMediaServer mediaServer) {
        String response = doPost(mediaServer, "getThreadsLoad", null, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    /**
     * 获取工作线程负载（同步）
     *
     * @param mediaServer ZLM 服务器配置
     * @return ZLMResult
     */
    public ZLMResult<Void> getWorkThreadsLoad(ZlmMediaServer mediaServer) {
        String response = doPost(mediaServer, "getWorkThreadsLoad", null, null);
        return parseResult(response, TYPE_ZLM_VOID);
    }

    // ==================== 保留原有兼容方法签名 ====================

    /**
     * 发送 POST 请求（兼容旧接口，内部转发到新实现）
     *
     * @deprecated 请使用具体业务方法
     */
    @Deprecated
    public String sendPost(ZlmMediaServer mediaServer, String api, Map param, RequestCallback callback) {
        return sendPost(mediaServer, api, param, callback, null);
    }

    /**
     * 发送 POST 请求（兼容旧接口，内部转发到新实现）
     *
     * @deprecated 请使用具体业务方法
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public String sendPost(ZlmMediaServer mediaServer, String api, Map param, RequestCallback callback, Integer readTimeOut) {
        if (callback == null) {
            return doPost(mediaServer, api, param, readTimeOut);
        } else {
            doPostAsync(mediaServer, api, param, callback);
            return null;
        }
    }

    /**
     * 获取截图（兼容旧接口）
     *
     * @deprecated 请使用 getSnap
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public void sendGetForImg(ZlmMediaServer mediaServer, String api, Map params, String targetPath, String fileName) {
        doGetForFile(mediaServer, api, params, targetPath, fileName);
    }
}