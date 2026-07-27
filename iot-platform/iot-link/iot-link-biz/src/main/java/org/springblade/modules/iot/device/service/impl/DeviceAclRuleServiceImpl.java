package org.springblade.modules.iot.device.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotdeviceserviceimplDeviceAclRuleServiceImpl.java.mapper.DeviceAclRuleMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.springblade.core.mp.base.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.cache.vo.device.DeviceAclRuleCacheVO;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.entity.DeviceAclRule;
import org.springblade.modules.iot.device.enumeration.ClientAclActionTypeEnum;
import org.springblade.modules.iot.device.enumeration.DeviceAclRuleActionTypeEnum;
import org.springblade.modules.iot.device.enumeration.DeviceAclRuleLevelEnum;
import org.springblade.modules.iot.device.event.publisher.DeviceAclRuleEventPublisher;
import org.springblade.modules.iot.device.event.source.DeviceAclRuleChangedEventSource;
import org.springblade.modules.iot.device.service.DeviceAclRuleService;
import org.springblade.modules.iot.device.vo.query.DeviceAclCheckQuery;
import org.springblade.modules.iot.device.vo.save.DeviceAclRuleSaveVO;
import org.springblade.modules.iot.device.vo.update.DeviceAclRuleUpdateVO;
import org.springblade.modules.iot.protocol.vo.result.DeviceAclCheckResultVO;
import org.springblade.modules.iot.protocol.vo.result.DeviceInfoResultVO;
import org.springblade.modules.iot.utils.acl.AclMatcherUtil;
import org.springblade.modules.iot.utils.acl.AclTopicPatternPlaceholderReplacer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * 璁惧璁块棶鎺у埗(ACL)瑙勫垯琛?
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-11 19:57:46
 * @create [2025-06-11 19:57:46] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
public class DeviceAclRuleServiceImpl extends BaseServiceImpl<DeviceAclRuleMapper, DeviceAclRule> implements DeviceAclRuleService {

    private final LinkCacheDataHelper linkCacheDataHelper;
    private final DeviceAclRuleEventPublisher deviceAclRuleEventPublisher;

    @Override
    protected <UpdateVO> DeviceAclRule updateBefore(UpdateVO vo) {
        DeviceAclRuleUpdateVO updateVO = (DeviceAclRuleUpdateVO) vo;
        DeviceAclRuleLevelEnum level = validateAndNormalize(updateVO);
        requireNoPriorityConflict(level, updateVO);
        return super.updateBefore(updateVO);
    }

    @Override
    protected <SaveVO> DeviceAclRule saveBefore(SaveVO vo) {
        DeviceAclRuleSaveVO saveVO = (DeviceAclRuleSaveVO) vo;
        DeviceAclRuleLevelEnum level = validateAndNormalize(saveVO);
        requireNoPriorityConflict(level, saveVO);
        return super.saveBefore(saveVO);
    }

    @Override
    protected <SaveVO> void saveAfter(SaveVO saveVO, DeviceAclRule entity) {
        publishChanged(entity);
    }

    @Override
    protected <UpdateVO> void updateAfter(UpdateVO updateVO, DeviceAclRule entity) {
        publishChanged(entity);
    }

    /**
     * SuperServiceImpl.removeByIds 娌℃湁 after 閽╁瓙,鏄惧紡 override 鍙戝彉鏇翠簨浠?
     * 璁?{@code DeviceAclRuleCacheEvictListener} 鍦ㄤ簨鍔℃彁浜ゅ悗澶辨晥缂撳瓨銆?
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<Long> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return false;
        }
        List<DeviceAclRule> deleted = superManager.listByIds(idList);
        boolean ok = super.removeByIds(idList);
        if (ok && !deleted.isEmpty()) {
            deleted.forEach(this::publishChanged);
        }
        return ok;
    }

    /**
     * 鍙?ACL 瑙勫垯鍙樻洿浜嬩欢 鈹€鈹€ 鐢?{@code @TransactionalEventListener(AFTER_COMMIT)}
     * 寮傛娑堣垂瑙﹀彂缂撳瓨澶辨晥,浜嬪姟鍥炴粴鍒欎笉瑙﹀彂,淇濊瘉缂撳瓨涓?DB 涓€鑷淬€?
     */
    private void publishChanged(DeviceAclRule rule) {
        if (rule == null || StrUtil.isBlank(rule.getProductIdentification())) {
            return;
        }
        // 闈炴硶 ruleLevel 杞?null,listener fallback 璧?浜у搧绾?evict" 鍏滃簳
        DeviceAclRuleLevelEnum level = DeviceAclRuleLevelEnum.fromValue(rule.getRuleLevel()).orElse(null);
        deviceAclRuleEventPublisher.publishDeviceAclRuleChangedEvent(
            DeviceAclRuleChangedEventSource.builder()
                .ruleId(rule.getId())
                .ruleLevel(level)
                .productIdentification(rule.getProductIdentification())
                .deviceIdentification(rule.getDeviceIdentification())
                .build());
    }

    // ============================== 鍐呴儴:鏍￠獙 / 瑙勮寖鍖?==============================

    /** save VO 閲嶈浇 鈹€鈹€ 璋冪敤鏂逛竴琛屾悶瀹氥€?*/
    private DeviceAclRuleLevelEnum validateAndNormalize(DeviceAclRuleSaveVO vo) {
        return validateAndNormalize(vo.getRuleLevel(), vo.getProductIdentification(),
            vo::setDeviceIdentification, vo.getDeviceIdentification());
    }

    /** update VO 閲嶈浇 鈹€鈹€ 璋冪敤鏂逛竴琛屾悶瀹氥€?*/
    private DeviceAclRuleLevelEnum validateAndNormalize(DeviceAclRuleUpdateVO vo) {
        return validateAndNormalize(vo.getRuleLevel(), vo.getProductIdentification(),
            vo::setDeviceIdentification, vo.getDeviceIdentification());
    }

    private DeviceAclRuleLevelEnum validateAndNormalize(Integer ruleLevel, String productIdentification,
                                                        Consumer<String> deviceIdSetter,
                                                        String currentDeviceId) {
        if (StrUtil.isBlank(productIdentification)) {
            throw BizException.wrap("浜у搧鏍囪瘑涓嶈兘涓虹┖");
        }
        DeviceAclRuleLevelEnum level = DeviceAclRuleLevelEnum.fromValue(ruleLevel)
            .orElseThrow(() -> BizException.wrap("瑙勫垯绾у埆闈炴硶:浠呮敮鎸?0(浜у搧绾?鎴?1(璁惧绾?"));
        if (level == DeviceAclRuleLevelEnum.PRODUCT_LEVEL) {
            // 浜у搧绾?寮哄埗 null,閬垮厤涓庤澶囩骇"绌?deviceId"娣锋穯 + 鍏煎 IS NULL 鍞竴鎬ф煡璇?
            deviceIdSetter.accept(null);
        } else if (level == DeviceAclRuleLevelEnum.DEVICE_LEVEL) {
            if (StrUtil.isBlank(currentDeviceId)) {
                throw BizException.wrap("璁惧绾ц鍒欏繀椤诲～鍐欒澶囨爣璇?);
            }
        }
        return level;
    }

    /**
     * 妫€鏌ュ悓 (level, productId, deviceId, priority) 缁村害鏄惁宸叉湁瑙勫垯銆?
     *
     * <p>浜у搧绾?deviceIdentification 鍏煎 NULL + 绌哄瓧绗︿覆(闃插巻鍙茶剰鏁版嵁婕忓垽);璁惧绾ц蛋 .eq 绮剧‘鍖归厤銆?
     */
    private boolean existsSamePriorityRule(DeviceAclRuleLevelEnum level, String productId, String deviceId,
                                           Integer priority, Long excludeId) {
        var wrap = Wrappers.<DeviceAclRule>lbQ()
            .eq(DeviceAclRule::getRuleLevel, level.getValue())
            .eq(DeviceAclRule::getProductIdentification, productId)
            .eq(DeviceAclRule::getPriority, priority);
        if (StrUtil.isBlank(deviceId)) {
            // LbQueryWrap.eq 瀵圭┖涓茶嚜鍔ㄥ拷鐣?condition,鍙兘鐢?apply 鍐欒８ SQL 寮哄埗娣诲姞
            wrap.and(w -> w
                .isNull(DeviceAclRule::getDeviceIdentification)
                .or().apply("device_identification = ''"));
        } else {
            wrap.eq(DeviceAclRule::getDeviceIdentification, deviceId);
        }
        if (excludeId != null) {
            wrap.ne(DeviceAclRule::getId, excludeId);
        }
        return superManager.count(wrap) > 0;
    }

    /**
     * 鎶?鍚屼紭鍏堢骇鍐茬獊"閿欒,娑堟伅甯︾淮搴︿笂涓嬫枃銆?
     */
    private void throwSamePriorityConflict(DeviceAclRuleLevelEnum level, String productId, String deviceId, Integer priority) {
        String dimension = level == DeviceAclRuleLevelEnum.DEVICE_LEVEL
            ? StrUtil.format("浜у搧 [{}] 璁惧 [{}]", productId, deviceId)
            : StrUtil.format("浜у搧 [{}] (浜у搧绾?", productId);
        throw BizException.wrap("{} 涓嬪凡瀛樺湪浼樺厛绾?{} 鐨勮鍒?璇疯皟鏁?priority 鎴栫紪杈戠幇鏈夎鍒?,
            dimension, priority);
    }

    /** save VO 閲嶈浇 鈹€鈹€ excludeId 榛樿 null(鏂板鏃犻渶鎺掗櫎鑷韩)銆?*/
    private void requireNoPriorityConflict(DeviceAclRuleLevelEnum level, DeviceAclRuleSaveVO vo) {
        if (existsSamePriorityRule(level, vo.getProductIdentification(),
            vo.getDeviceIdentification(), vo.getPriority(), null)) {
            throwSamePriorityConflict(level, vo.getProductIdentification(),
                vo.getDeviceIdentification(), vo.getPriority());
        }
    }

    /** update VO 閲嶈浇 鈹€鈹€ 鎺掗櫎鑷韩 id 闃叉妸鑷繁褰撳啿绐併€?*/
    private void requireNoPriorityConflict(DeviceAclRuleLevelEnum level, DeviceAclRuleUpdateVO vo) {
        if (existsSamePriorityRule(level, vo.getProductIdentification(),
            vo.getDeviceIdentification(), vo.getPriority(), vo.getId())) {
            throwSamePriorityConflict(level, vo.getProductIdentification(),
                vo.getDeviceIdentification(), vo.getPriority());
        }
    }

    @Override
    public DeviceAclCheckResultVO checkAclPermission(DeviceAclCheckQuery deviceAclCheckQuery) {
        Optional<DeviceCacheVO> deviceCacheVO = linkCacheDataHelper.getDeviceCacheVO(deviceAclCheckQuery.getClientIdentifier());
        if (deviceCacheVO.isEmpty()) {
            return denied("Device Not Found");
        }
        DeviceInfoResultVO deviceInfoResultVO = BeanUtil.toBean(deviceCacheVO.get(), DeviceInfoResultVO.class);
        // 鐩存帴璧?helper,涓?self-call(self-call 缁曡繃 AOP,浠ュ悗鍔犲垏闈細澶辨晥)
        List<DeviceAclRuleCacheVO> rules = linkCacheDataHelper.getDeviceAclRules(
            deviceInfoResultVO.getProductIdentification(), deviceInfoResultVO.getDeviceIdentification());
        if (CollectionUtil.isEmpty(rules)) {
            return denied("Not ACL Rule");
        }

        // client action 鈫?rule action 鏄犲皠;鏄犲皠涓嶅瓨鍦?濡?disconnect)璧伴粯璁?deny
        return ClientAclActionTypeEnum.fromValue(deviceAclCheckQuery.getActionType())
            .flatMap(DeviceAclRuleActionTypeEnum::fromClientType)
            .map(targetAction -> decideTopicAccess(targetAction, deviceAclCheckQuery.getTopic(), deviceInfoResultVO, rules))
            .orElseGet(() -> denied("Unsupported action type for ACL: " + deviceAclCheckQuery.getActionType()));
    }

    /**
     * 鎸?actionType 杩囨护瑙勫垯(targetAction 鎴?ALL 鍛戒腑) + 鍗犱綅绗︽浛鎹?+ matcher 鍐崇瓥銆?
     * <p>enabled filter 宸茬敱缂撳瓨 loader 淇濊瘉;ruleAction 涓?null 鏃舵樉寮忔嫆缁濋槻 DB 鑴忔暟鎹鍛戒腑銆?
     */
    private DeviceAclCheckResultVO decideTopicAccess(DeviceAclRuleActionTypeEnum targetAction,
                                                     String topic,
                                                     DeviceInfoResultVO deviceInfo,
                                                     List<DeviceAclRuleCacheVO> rules) {
        List<DeviceAclRuleCacheVO> filteredRules = rules.stream()
            .filter(rule -> {
                DeviceAclRuleActionTypeEnum ruleAction = DeviceAclRuleActionTypeEnum
                    .fromValue(rule.getActionType()).orElse(null);
                return ruleAction != null && (ruleAction == targetAction || ruleAction == DeviceAclRuleActionTypeEnum.ALL);
            })
            .collect(Collectors.toList());

        AclTopicPatternPlaceholderReplacer.replacePlaceholders(filteredRules, Optional.of(deviceInfo));
        boolean allowed = AclMatcherUtil.isTopicAllowed(topic, filteredRules);
        return DeviceAclCheckResultVO.builder()
            .allowed(allowed)
            .echoMap(MapUtil.newHashMap())
            .build();
    }

    private DeviceAclCheckResultVO denied(String errorMessage) {
        return DeviceAclCheckResultVO.builder()
            .allowed(false)
            .errorMessage(errorMessage)
            .echoMap(MapUtil.newHashMap())
            .build();
    }

    @Override
    public List<DeviceAclRuleCacheVO> getDeviceAclRuleCacheVOList(String productIdentification, String deviceIdentification) {
        return linkCacheDataHelper.getDeviceAclRules(productIdentification, deviceIdentification);
    }

}

