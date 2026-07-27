package org.springblade.modules.iot.mqs.transform;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.cache.CacheKeyModular;
import org.springblade.common.utils.StrPool;
import org.springblade.modules.iot.common.utils.MqttTopicMatcher;
import org.springblade.modules.iot.common.utils.TopicPlaceholders;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.modules.iot.cache.vo.product.ProductCacheVO;
import org.springblade.modules.iot.cache.vo.product.ProductModelCacheVO;
import org.springblade.common.cache.rule.groovy.GroovyScriptCacheKeyBuilder;
import org.springblade.modules.iot.common.cache.rule.groovy.TransformScriptEntry;
import org.springblade.common.constant.CommonIotConstants;
import org.springblade.modules.iot.entity.device.CommonDeviceEvent;
import org.springblade.modules.iot.entity.uplink.source.UplinkMessageEventSource;
import org.springblade.modules.iot.product.enumeration.ProtocolTypeEnum;
import org.springblade.modules.iot.rule.facade.RuleOpenInnerFacade;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptDirectCompileParam;
import org.springblade.modules.iot.vo.result.script.GroovyScriptEngineExecutorResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 鐠佹儳顦稉濠咁攽閸樼喎顫愰幎銉︽瀮閵嗗苯澧犵純顔挎祮閹诡潿鈧秮鏀㈤埞鈧?閸?topic 鐠侯垳鏁?{@code TopicHandlerFactory})娑斿澧犻幍褑顢?
 * 閹稿鈧奔楠囬崫?+ 娴溠冩惂閸欐垵绔烽悧鍫熸拱 + topic 濡€崇础閵嗗秴鎳℃稉顓犳暏閹寸兘鍘ょ純顔炬畱 Groovy 閼存碍婀?閹跺﹤宸堕崯鍡欘潌閺?topic/閹躲儲鏋?
 * 鏉烆剚宕查幋鎰挬閸欑増鐖ｉ崙?{@code /v1/devices/{deviceId}/datas} 閹躲儲鏋?閸愬秳姘﹂崥搴ｇ敾 handler 鐠ф澘甯張澶愭懠鐠侯垬鈧?
 *
 * <p>闁氨鏁ゆ禍搴㈠閺堝绗傜悰?topic(娑撳秵顒?datas):閺堫亜鎳℃稉顓犵拨鐎规俺鍓奸張顒€鍨崢鐔哥壉闁繋绱?閸涙垝鑵戦幍宥堟祮閹?
 * 娴犺缍嶅鍌氱埗娑撯偓瀵板妾风痪褌璐熼崢鐔哥壉闁繋绱?缂佹繀绗夐梼缁樻焽娑撳﹨顢戞稉濠氭懠鐠侯垬鈧?
 *
 * <p>缂傛挸鐡?{@code HGETALL(娴溠冩惂+閻楀牊婀板?} 娑撯偓濞嗏€冲絿閸ョ偠顕氭禍褍鎼х拠銉у閺堫剙鍙忛柈?{@code topic 濡€崇础 閳?閼存碍婀伴崘鍛啇},
 * 閸愬懎鐡ㄩ柅鎰蒋閸栧綊鍘?閺冪姷绮︾€规碍妞傚鏈佃礋缁?闂嗗爼顤傛径鏍х磻闁库偓(娑撳秴褰?Feign)閵?
 *
 * <p>閼存碍婀?I/O 婵傛垹瀹?
 * <ul>
 *   <li>閸忋儱寮?executeParams JSON):{@code originTopic / originBody / clientId / deviceIdentification / productIdentification};
 *       閸欙附鏁為崗?{@code device}(鐠佹儳顦崺铏诡攨娣団剝浼?DTO,娑撳秴鎯?password)娑?{@code product}(娴溠冩惂閸╄櫣顢呮穱鈩冧紖 DTO),
 *       閼存碍婀伴崣?{@code device.signKey} / {@code device.encryptMethod} / {@code product.protocolType} 閸斻劍鈧礁褰囬崐?/li>
 *   <li>閸戝搫寮?閼存碍婀?return,閸?context):{@code {"topic":"/v1/devices/.../datas","payload":{楠炲啿褰撮弽鍥у櫙缂佹挻鐎瘆}};
 *       缂?{@code topic} 娣囨繃瀵旈崢?topic;context 闂堢偟瀹崇€规氨绮ㄩ弸鍕灟閺佺繝缍嬭ぐ鎾茬稊 payload閵?/li>
 * </ul>
 *
 * @author mqttsnet
 * @since 2026-06-03
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InboundScriptTransformer {

    private static final String CTX_TOPIC = CommonIotConstants.TOPIC;
    private static final String CTX_PAYLOAD = CommonIotConstants.PAYLOAD;
    private static final String CHANNEL_MQTT = "mqtt";
    private static final String CHANNEL_WEBSOCKET = "webSocket";

    private final CachePlusOps cachePlusOps;
    private final LinkCacheDataHelper linkCacheDataHelper;
    private final RuleOpenInnerFacade ruleOpenInnerFacade;
    private final ScriptBindingAssembler bindingAssembler;

    /**
     * 鐟欙絾鐎介崙鍝勫讲鐠侯垳鏁遍惃?{@link UplinkMessageEventSource} 閳光偓閳光偓 閸涙垝鑵戦崜宥囩枂鏉烆剚宕查懘姘拱閸掓瑨绻戦崶鐐舵祮閹广垹鎮楅惃?
     * (閺€鐟板晸 topic + 閺嶅洤鍣幎銉︽瀮),閸氾箑鍨幐澶婂斧婵绨ㄦ禒鑸电€鎭掆偓?
     *
     * @param event 鐠佹儳顦柅姘辨暏娴滃娆?PUBLISH)
     * @return 閸欘垯姘?{@code TopicHandlerFactory} 鐠侯垳鏁遍惃鍕Х閹垱绨?
     */
    public UplinkMessageEventSource resolveEventSource(CommonDeviceEvent event) {
        // 娴兼ê鍘涢悽?bus 闂冭埖顔屽鑼缎掗弸鎰扳偓蹇庣炊閻ㄥ嫯顔曟径鍥╃处鐎?閸忓秹鍣搁崣?;缂傚搫銇?婵″倹婀紒?bus 鐎靛苯瀵查惃鍕珶缂傛ê婧€閺?閸忔粌绨抽懛顏勫絿閵?
        // 鐟欙絾鐎芥稉鈧▎锟犫偓蹇庣炊閸?source(娑撳鐖?handler 閸忓秹鍣搁崣?+ 娓氭稖鍓奸張顒€鎳℃稉顓炲灲閺傤厼顦查悽銊ｂ偓?
        DeviceCacheVO deviceVO = Optional.ofNullable(event.getDeviceCache()).orElseGet(() -> resolveDevice(event));
        try {
            Transformed t = tryTransform(event, deviceVO);
            if (t != null) {
                log.info("[InboundTransform] applied clientId={} topic {} -> {}", event.getClientId(), event.getTopic(), t.topic());
                return buildTransformedSource(event, t.topic(), t.payload(), deviceVO);
            }
        } catch (Exception e) {
            log.warn("[InboundTransform] failed (passthrough) clientId={} topic={} err={}",
                event.getClientId(), event.getTopic(), e.getMessage());
        }
        return buildOriginalSource(event, deviceVO);
    }

    /**
     * 閸涙垝鑵戦獮鑸靛⒔鐞涘矁鍓奸張顒€鍨潻鏂挎礀鏉烆剚宕茬紒鎾寸亯,閸氾箑鍨潻鏂挎礀 {@code null}(鐠ф澘甯弽鐑解偓蹇庣炊)閵?
     */
    private Transformed tryTransform(CommonDeviceEvent event, DeviceCacheVO deviceVO) {
        String topic = event.getTopic();
        String product = event.getProductIdentification();
        if (StrUtil.hasBlank(topic, product)) {
            return null;
        }
        String channel = resolveChannel(event.getProtocolType());
        if (channel == null) {
            return null;
        }
        String version = Optional.ofNullable(deviceVO).map(DeviceCacheVO::getBoundProductVersionNo).orElse(null);
        if (StrUtil.isBlank(version)) {
            return null;
        }
        CacheKey bucketKey = GroovyScriptCacheKeyBuilder.transformHashKey(channel, product, version);
        Map<String, CacheResult<String>> bucket =
            cachePlusOps.hGetAll(bucketKey, k -> Collections.<String, String>emptyMap(), false);
        if (CollUtil.isEmpty(bucket)) {
            return null;
        }
        MatchedScript matched = matchScript(bucket, topic);
        if (matched == null || StrUtil.isBlank(matched.entry().getContent())) {
            return null;
        }
        // 閸涙垝鑵戦崥搴㈠閸欐牔楠囬崫浣虹处鐎?閺堫亜鎳℃稉顓濈瑝閺?;鐠佹儳顦紓鎾崇摠娑撳﹪娼板鎻掑絿,閻╁瓨甯存径宥囨暏濞夈劌鍙嗛懘姘拱
        ProductCacheVO productVO = linkCacheDataHelper.getProductCacheVO(product).orElse(null);
        // 閼存碍婀伴崬顖欑闁?娑撳氦顕涢幆?RuleGroovyScriptResultVO#buildOnlyKey 娑撯偓閼?:閼存碍婀扮猾璇茬€?濞撶娀浜?娴溠冩惂:娑撳顣藉Ο鈥崇础 閳光偓閳光偓 娓?rule 鐠佺増澧界悰宀€绮虹拋?
        String scriptUniqueKey = String.join(StrPool.COLON, GroovyScriptCacheKeyBuilder.TRANSFORM_SCRIPT_TYPE, channel, product, matched.topicPattern());
        return executeScript(event, topic, matched.entry(), scriptUniqueKey, deviceVO, productVO);
    }

    /**
     * 濡楄泛鍞撮幐?topic 濡€崇础闁劖娼崠褰掑帳,閸涙垝鑵戞潻鏃囶嚉閺?{@link TransformScriptEntry}(閼存碍婀伴崘鍛啇 + 閹碘晛鐫嶉崣鍌涙殶)閵?
     *
     * <p>娑撳孩藟閹?{@code TopicMatchStrategy} / ACL 闁村瓨娼堥崥灞肩婵傛顕㈡稊?閸忓牏鏁?
     * {@link TopicPlaceholders#replaceWithWildcard} 閹?{@code ${...}} 閸楃姳缍呯粭锕佹祮閹?MQTT 閸楁洖鐪伴柅姘跺帳 {@code +},
     * 閸愬秳姘?{@link MqttTopicMatcher} 娑撳海婀＄€圭偘绗傜悰?topic 濮ｆ柨顕?閳光偓閳光偓 閻╁瓨甯撮幏?{@code ${...}} 閸樼喍瑕嗛崠褰掑帳娴兼俺顫﹁ぐ鎾崇摟闂堛垽鍣?濮樻瓕绻欐稉宥呮嚒娑擃厹鈧?
     */
    private MatchedScript matchScript(Map<String, CacheResult<String>> bucket, String topic) {
        for (Map.Entry<String, CacheResult<String>> entry : bucket.entrySet()) {
            String mqttPattern = TopicPlaceholders.replaceWithWildcard(entry.getKey());
            if (MqttTopicMatcher.match(mqttPattern, topic)) {
                TransformScriptEntry scriptEntry = parseBucketValue(entry.getValue() == null ? null : entry.getValue().getRawValue());
                // entry.getKey() 閸楀啿鎳℃稉顓犳畱娑撳顣藉Ο鈥崇础(topicPattern,濡?field),閸ョ偘绱堕悽銊ょ艾閹疯壈鍓奸張顒€鏁稉鈧柨?
                return scriptEntry == null ? null : new MatchedScript(entry.getKey(), scriptEntry);
            }
        }
        return null;
    }

    /**
     * 閸涙垝鑵戦惃鍕壖閺?閳光偓閳光偓 閸栧綊鍘ら崚鎵畱娑撳顣藉Ο鈥崇础(topicPattern,濡?field) + 閼存碍婀伴弶锛勬窗閵?
     */
    private record MatchedScript(String topicPattern, TransformScriptEntry entry) {
    }

    /**
     * 鐟欙絾鐎藉璺衡偓?JSON 閳?{@link TransformScriptEntry}閵嗗倸鍚嬬€硅妫弽鐓庣础(缁绢垵鍓奸張顒€鍞寸€圭懓鐡х粭锔胯):闂?JSON 閸掓瑦鏆ｆ稉鎻掔秼 content閵?
     */
    private TransformScriptEntry parseBucketValue(String raw) {
        if (StrUtil.isBlank(raw)) {
            return null;
        }
        try {
            TransformScriptEntry entry = JSON.parseObject(raw, TransformScriptEntry.class);
            if (entry != null && StrUtil.isNotBlank(entry.getContent())) {
                return entry;
            }
        } catch (Exception ignore) {
            // 閺冄勭壐瀵?缁绢垵鍓奸張顒€鍞寸€?鐠ч绗呴棃銏犲幑鎼?
        }
        return new TransformScriptEntry(raw, null);
    }

    /**
     * Feign 鐠?rule 閹稿鍓奸張顒€鍞寸€硅澧界悰?鐟欙絾鐎?context 閳?{閺€鐟板晸 topic, 閺嶅洤鍣幎銉︽瀮 payload}閵?
     * 濞夈劌鍙?{@code device}(閻欘剛鐝?DTO,娑撳秴鎯?password)+ {@code product}(閻欘剛鐝?DTO)+ {@code config}(閼存碍婀?extend_params),
     * 閼存碍婀伴崣?{@code device.signKey} / {@code device.encryptMethod} / {@code product.protocolType} / {@code config.xxx} 閸斻劍鈧礁褰囬崐绗衡偓?
     */
    private Transformed executeScript(CommonDeviceEvent event, String topic, TransformScriptEntry entry,
                                      String scriptUniqueKey, DeviceCacheVO deviceVO, ProductCacheVO productVO) {
        // 鏉╂劘顢戦弮?閹稿顔曟径鍥╃拨鐎规氨澧楅張顒冃掗弸鎰⒖濡€崇€?缁岃櫣澧楅張顒€娲栭柅鈧粙鍐茬暰閻?閻忔澘瀹抽幀?previousFullVersionNo,閸氾箑鍨?activeVersionNo),
        // 娑撳孩鏆熼幑顔荤瑐閹躲儴鐭惧鍕經瀵板嫪绔撮懛?閳ユ柡鈧?閺堫亜鍙嗛悘鏉垮缂佸嫮娈戠粚铏瑰閺堫剝顔曟径鍥︾瑝鎼存梹瀵滈悘鏉垮閻楀牐袙閺嬫劖濮ら弬?
        String modelVersionNo = deviceVO == null ? null : deviceVO.getBoundProductVersionNo();
        if (StrUtil.isBlank(modelVersionNo) && productVO != null) {
            modelVersionNo = StrUtil.blankToDefault(
                productVO.getPreviousFullVersionNo(), productVO.getActiveVersionNo());
        }
        ProductModelCacheVO productModel = StrUtil.isBlank(modelVersionNo) ? null
            : linkCacheDataHelper.resolveProductModelByVersionNo(event.getProductIdentification(), modelVersionNo).orElse(null);

        // 娑撳簺鈧苯婀痪鑳殶鐠囨洏鈧秴鍙￠悽銊ユ倱娑撯偓婵傛绮︾€规氨绮嶇憗?闂嗚埖绱撶粔?:originTopic / originBody / clientId / device / product / productModel / config 閳?
        Map<String, Object> params = bindingAssembler.assemble(deviceVO, productVO, productModel,
            topic, decodeBody(event), event.getPayloadHex(),
            event.getClientId(), event.getDeviceIdentification(), event.getProductIdentification(),
            entry.getExtendParams());

        RuleGroovyScriptDirectCompileParam param = new RuleGroovyScriptDirectCompileParam();
        param.setScriptContent(entry.getContent());
        param.setExecuteParams(JSON.toJSONString(params));
        // 閼存碍婀伴崬顖欑闁款噣鈧繋绱剁紒?rule,娓氭稒瀵滈懘姘拱缂佹潙瀹崇拋鐗堝⒔鐞涘瞼绮虹拋?total/success/fail)
        param.setScriptUniqueKey(scriptUniqueKey);

        R<GroovyScriptEngineExecutorResultVO> r = ruleOpenInnerFacade.executeScriptContent(param);
        if (r == null || !Boolean.TRUE.equals(R.isSuccess(r)) || r.getData() == null) {
            log.warn("[InboundTransform] script exec non-success clientId={} topic={} r={}", event.getClientId(), topic, JSON.toJSONString(r));
            return null;
        }
        Object context = r.getData().getContext();
        if (context == null) {
            return null;
        }
        JSONObject ctx = toJsonObject(context);
        String newTopic = ctx == null ? null : ctx.getString(CTX_TOPIC);
        String payload;
        if (ctx != null && ctx.containsKey(CTX_PAYLOAD)) {
            Object payloadVal = ctx.get(CTX_PAYLOAD);
            payload = payloadVal instanceof String ? (String) payloadVal : JSON.toJSONString(payloadVal);
        } else {
            // 闂堢偟瀹崇€规氨绮ㄩ弸?閺佺繝缍嬭ぐ鎾茬稊楠炲啿褰撮弽鍥у櫙閹躲儲鏋?
            payload = context instanceof String ? (String) context : JSON.toJSONString(context);
        }
        if (StrUtil.isBlank(payload)) {
            return null;
        }
        return new Transformed(StrUtil.isBlank(newTopic) ? topic : newTopic, payload);
    }

    private static JSONObject toJsonObject(Object context) {
        try {
            if (context instanceof JSONObject) {
                return (JSONObject) context;
            }
            if (context instanceof Map) {
                return JSON.parseObject(JSON.toJSONString(context));
            }
            if (context instanceof String) {
                return JSON.parseObject((String) context);
            }
        } catch (Exception ignore) {
            // 鐟欙絾鐎芥径杈Е閹?null 婢跺嫮鎮?娴溿倗鏁辩拫鍐暏閺傜懓鍘规惔鏇炵秼娴ｆ粍鏆ｆ担?payload
        }
        return null;
    }

    /**
     * 閸欐牞顔曟径鍥╃处鐎?VO 閳光偓閳光偓 閺冦垻鏁ゆ禍搴ば掗弸鎰拨鐎规氨娈戞禍褍鎼ч崣鎴濈閻楀牊婀?閻楀牊婀扮紒鏉戝鐎规矮缍呭?,娑旂喍缍旀稉楦垮壖閺?{@code device} 缂佹垵鐣鹃惃鍕降濠ф劑鈧?
     */
    private DeviceCacheVO resolveDevice(CommonDeviceEvent event) {
        String key = StrUtil.blankToDefault(event.getDeviceIdentification(), event.getClientId());
        if (StrUtil.isBlank(key)) {
            return null;
        }
        return linkCacheDataHelper.getDeviceCacheVO(key).orElse(null);
    }

    /**
     * 閸楀繗顔呯猾璇茬€?閳?濞撶娀浜剧紓鏍垳(娑撳氦鍓奸張?channelCode 鐎涙鍚€鐎靛綊缍?mqtt / webSocket)閵?
     */
    private String resolveChannel(String protocolType) {
        if (StrUtil.isBlank(protocolType)) {
            return null;
        }
        if (ProtocolTypeEnum.MQTT.getValue().equalsIgnoreCase(protocolType)) {
            return CHANNEL_MQTT;
        }
        if (ProtocolTypeEnum.WEBSOCKET.getValue().equalsIgnoreCase(protocolType)) {
            return CHANNEL_WEBSOCKET;
        }
        return null;
    }

    private String decodeBody(CommonDeviceEvent event) {
        if (StrUtil.isBlank(event.getPayload())) {
            return StrPool.EMPTY;
        }
        return new String(Base64.decode(event.getPayload()), StandardCharsets.UTF_8);
    }

    /**
     * 閹稿娴嗛幑銏犳倵閻?topic + 閺嶅洤鍣幎銉︽瀮閺嬪嫬缂撳☉鍫熶紖濠?payload 闁插秵鏌?Base64,娓氭稑鎮楃紒?handler 鐟欙絿鐖?閵?
     */
    private UplinkMessageEventSource buildTransformedSource(CommonDeviceEvent event, String topic, String payload, DeviceCacheVO deviceVO) {
        byte[] bytes = payload.getBytes(StandardCharsets.UTF_8);
        return UplinkMessageEventSource.builder()
            .topic(topic)
            .qos(event.getQos() == null ? null : String.valueOf(event.getQos()))
            .payloadBytes(bytes)
            .payload(Base64.encode(bytes))
            .encoding(StandardCharsets.UTF_8.name())
            .originalSize(String.valueOf(bytes.length))
            .timestamp(event.getTs() == null ? null : String.valueOf(event.getTs()))
            .deviceCacheVO(deviceVO)
            .build();
    }

    /**
     * 閸樼喐鐗遍柅蹇庣炊:閹稿甯慨瀣╃皑娴犺埖鐎鐑樼Х閹垱绨?娑撳孩婀崥顖滄暏閸撳秶鐤嗘潪顒佸床閺冩儼顢戞稉杞扮閼?閵?
     */
    private UplinkMessageEventSource buildOriginalSource(CommonDeviceEvent event, DeviceCacheVO deviceVO) {
        byte[] payloadBytes = StrUtil.isBlank(event.getPayload())
            ? new byte[0]
            : Base64.decode(event.getPayload());
        return UplinkMessageEventSource.builder()
            .topic(event.getTopic())
            .qos(event.getQos() == null ? null : String.valueOf(event.getQos()))
            .payloadBytes(payloadBytes)
            .payload(event.getPayload())
            .payloadHex(event.getPayloadHex())
            .originalSize(event.getOriginalSize() == null ? null : String.valueOf(event.getOriginalSize()))
            .encoding(event.getEncoding())
            .timestamp(event.getTs() == null ? null : String.valueOf(event.getTs()))
            .deviceCacheVO(deviceVO)
            .build();
    }

    /**
     * 鏉烆剚宕茬紒鎾寸亯 閳光偓閳光偓 閺€鐟板晸閸氬海娈?topic + 楠炲啿褰撮弽鍥у櫙閹躲儲鏋?payload閵?
     */
    private record Transformed(String topic, String payload) {
    }
}
