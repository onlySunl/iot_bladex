package org.springblade.modules.iot.device.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotdeviceserviceimplDeviceCommandServiceImpl.java.mapper.DeviceCommandMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springblade.core.tool.api.R;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.broker.MqttBrokerOpenInnerFacade;
import org.springblade.modules.iot.broker.WebSocketBrokerOpenInnerFacade;
import org.springblade.modules.iot.broker.DeviceDownlinkFacade;
import org.springblade.modules.iot.vo.query.DownlinkCommand;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.enumeration.DeviceCommandStatusEnum;
import org.springblade.modules.iot.device.enumeration.DeviceCommandTypeEnum;
import org.springblade.modules.iot.device.enumeration.DeviceNodeTypeEnum;
import org.springblade.modules.iot.device.enumeration.DeviceStatusEnum;
import org.springblade.modules.iot.device.service.DeviceCommandService;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.query.DeviceCommandPageQuery;
import org.springblade.modules.iot.device.vo.query.DevicePageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceCommandSaveVO;
import org.springblade.modules.iot.enumeration.QosEnum;
import org.springblade.modules.iot.protocol.vo.param.CommandIssueRequestParam;
import org.springblade.modules.iot.protocol.vo.param.DeviceCommandWrapperParam;
import org.springblade.modules.iot.protocol.vo.param.PublishMqttMessageRequestParam;
import org.springblade.modules.iot.protocol.vo.param.PublishWebSocketMessageRequestParam;
import org.springblade.modules.iot.protocol.vo.result.DeviceCommandResultVO;
import org.springblade.modules.iot.vo.query.PublishMessageRequestVO;
import org.springblade.modules.iot.vo.query.PublishWebSocketMessageRequestVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * 璁惧鍛戒护涓嬪彂鍙婂搷搴旇〃
 * </p>
 *
 * @author mqttsnet
 * @date 2023-10-20 17:27:25
 * @create [2023-10-20 17:27:25] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
public class DeviceCommandServiceImpl extends BaseServiceImpl<DeviceCommandMapper, DeviceCommand> implements DeviceCommandService {

    private final LinkCacheDataHelper linkCacheDataHelper;
    private final MqttBrokerOpenInnerFacade mqttBrokerOpenInnerFacade;
    private final WebSocketBrokerOpenInnerFacade webSocketBrokerOpenInnerFacade;
    private final DeviceDownlinkFacade deviceDownlinkFacade;
    private final DeviceService deviceService;
    private final ProtocolMessageAdapter protocolMessageAdapter;

    /**
     * Saves a device command to the database after validation.
     *
     * @param deviceCommandSaveVO The device command data transfer object.
     * @return The saved DeviceCommand entity.
     * @throws IllegalArgumentException if input validation fails.
     */
    @Override
    public DeviceCommand saveDeviceCommand(DeviceCommandSaveVO deviceCommandSaveVO) {
        // Validate the input, build the DeviceCommand object, and save it to the database.
        return Optional.of(deviceCommandSaveVO).filter(this::checkDeviceCommandSaveVO).map(this::buildDeviceCommand).map(deviceCommand -> {
            deviceCommand.setCommandIdentification(SnowflakeIdUtil.nextId());
            return superManager.save(deviceCommand) ? deviceCommand : null;
        }).orElseThrow(() -> new IllegalArgumentException("Invalid DeviceCommandSaveVO input"));
    }

    /**
     * Fetch a list of device command result VOs.
     *
     * @param query the query parameters
     * @return a list of DeviceCommandResultVOs
     */
    @Override
    public List<DeviceCommandResultVO> getDeviceCommandResultVOList(DeviceCommandPageQuery query) {
        return BeanUtil.toBeanList(superManager.getDeviceCommandResultVOList(query), DeviceCommandResultVO.class);
    }

    @Override
    public List<DeviceCommandResultVO> listDebugHistory(String deviceIdentification, String topic, Integer limit) {
        int safeLimit = (limit == null || limit <= 0) ? 100 : Math.min(limit, 500);
        String topicKeyword = topic == null ? null : topic.trim();
        boolean hasTopic = topicKeyword != null && !topicKeyword.isEmpty();
        // 浠呭彇鍛戒护涓嬪彂(0)/鍛戒护鍝嶅簲(1),OTA(2)涓嶅叆璋冭瘯鍙?璁惧绌?褰撳墠绉熸埛鍏ㄩ儴;鍊掑簭鍙栬繎 N 鏉?鍛戒腑 idx_device_cmdtype_ctime)銆?
        // topic 瀛樺湪 content/remark 鎶ユ枃涓?鍘熷/鏂扮粨鏋勫寲涓嬪彂鍦?content.topic,鍝嶅簲鏂拌褰曞湪 content.topic銆?
        return BeanUtil.toBeanList(
                superManager.lambdaQuery()
                        .in(DeviceCommand::getCommandType,
                                DeviceCommandTypeEnum.COMMAND_ISSUE.getValue(),
                                DeviceCommandTypeEnum.COMMAND_RESPONSE.getValue())
                        .eq(deviceIdentification != null && !deviceIdentification.isEmpty(), DeviceCommand::getDeviceIdentification, deviceIdentification)
                        .and(hasTopic, query -> query
                                .like(DeviceCommand::getContent, topicKeyword)
                                .or()
                                .like(DeviceCommand::getRemark, topicKeyword))
                        .orderByDesc(DeviceCommand::getCreatedTime)
                        .last("LIMIT " + safeLimit)
                        .list(),
                DeviceCommandResultVO.class);
    }

    /**
     * Processes both serial and parallel device command requests.
     *
     * @param commandWrapper wrapper containing both serial and parallel command requests
     * @return list of device command results
     */
    @Override
    public List<DeviceCommandResultVO> processDeviceCommands(DeviceCommandWrapperParam commandWrapper) {
        List<DeviceCommandResultVO> results = new ArrayList<>();

        // Process serial commands
        Optional.ofNullable(commandWrapper.getSerial()).orElseGet(Collections::emptyList)
                .stream()
                .map(this::processSingleCommand)
                .forEach(results::addAll);

        // Process parallel commands锛堜笉浣跨敤 parallelStream锛岄伩鍏?@DS 鏁版嵁婧愪笂涓嬫枃鍦?ForkJoinPool 绾跨▼涓涪澶憋級
        Optional.ofNullable(commandWrapper.getParallel()).orElseGet(Collections::emptyList)
                .stream()
                .map(this::processSingleCommand)
                .forEach(results::addAll);

        return results;
    }

    @Override
    public void sendMqttCustomMessage(PublishMqttMessageRequestParam publishMqttMessageRequestParam) {
        log.info("鍙戦€丮QTT娑堟伅 - Topic: {}, 绉熸埛: {}, 璐熻浇绫诲瀷: {}, 鏄惁涓築ase64: {}",
                publishMqttMessageRequestParam.getTopic(),
                publishMqttMessageRequestParam.getTenantId(),
                publishMqttMessageRequestParam.getPayload() != null ? publishMqttMessageRequestParam.getPayload().getClass().getSimpleName() : "null",
                publishMqttMessageRequestParam.isPayloadBase64());

        PublishMessageRequestVO publishMessageRequestVO = PublishMessageRequestVO.builder()
                .reqId(Long.valueOf(SnowflakeIdUtil.nextId()))
                .tenantId(publishMqttMessageRequestParam.getTenantId())
                .topic(publishMqttMessageRequestParam.getTopic())
                .qos(publishMqttMessageRequestParam.getQos())
                .clientType("web")
                .payload(publishMqttMessageRequestParam.getPayloadAsSmartString())
                .forceBase64Decode(publishMqttMessageRequestParam.isPayloadBase64())
                .expirySeconds(publishMqttMessageRequestParam.getExpirySeconds())
                .build();

        log.info("鍙戦€丮QTT娑堟伅 - 鏈€缁堣礋杞介暱搴? {}, 寮哄埗瑙ｇ爜: {}", publishMessageRequestVO.getPayload() != null ?
                publishMessageRequestVO.getPayload().length() : 0, publishMessageRequestVO.getForceBase64Decode());

        long startTime = System.currentTimeMillis();

        // 鎵ц鍙戦€?
        R response = mqttBrokerOpenInnerFacade.sendMessage(publishMessageRequestVO);

        long costTime = System.currentTimeMillis() - startTime;

        // 澶勭悊鍝嶅簲缁撴灉
        if (!response.getIsSuccess()) {
            log.error("銆怣QTT娑堟伅鍙戦€佸け璐ャ€戣€楁椂: {}ms, 閿欒淇℃伅: {}", costTime, response.getMsg());
            throw BizException.wrap("MQTT message sending failed. Please try again! Time consumed: {}ms", costTime);
        } else {
            log.info("銆怣QTT娑堟伅鍙戦€佹垚鍔熴€?<< 鑰楁椂: {}ms, 鍝嶅簲淇℃伅: {}", costTime, response.getMsg());
        }
        // 鍘熷涓嬭涔熻惤 device_command(type=0),渚涜皟璇曞彴鍘嗗彶涓庝竴閿噸鍙?璁惧鏍囪瘑鐢卞墠绔紶鍏?璁板綍澶辫触涓嶅奖鍝嶅凡鎴愬姛鐨勫彂閫?
        recordCustomDownlink(publishMqttMessageRequestParam.getDeviceIdentification(),
                publishMqttMessageRequestParam.getTopic(), publishMqttMessageRequestParam.getPayloadAsSmartString());
    }

    @Override
    public void sendWebSocketCustomMessage(PublishWebSocketMessageRequestParam publishWebSocketMessageRequestParam) {
        PublishWebSocketMessageRequestVO publishMessageRequestVO = new PublishWebSocketMessageRequestVO();
        publishMessageRequestVO.setReqId(Long.valueOf(SnowflakeIdUtil.nextId()));
        publishMessageRequestVO.setTenantId(publishWebSocketMessageRequestParam.getTenantId());
        publishMessageRequestVO.setTopic(publishWebSocketMessageRequestParam.getTopic());
        publishMessageRequestVO.setClientId(publishWebSocketMessageRequestParam.getClientId());
        publishMessageRequestVO.setClientType("web");
        publishMessageRequestVO.setPayload(publishWebSocketMessageRequestParam.getPayload());

        R response = webSocketBrokerOpenInnerFacade.sendMessage(publishMessageRequestVO);
        if (!response.getIsSuccess()) {
            log.warn("Failed to send WebSocket message: {}", response.getMsg());
        }
    }

    /**
     * 鑷畾涔?鍘熷涓嬭钀?device_command(type=0):渚涜皟璇曞彴鍘嗗彶涓庝竴閿噸鍙戙€?
     * 璁惧鏍囪瘑鐢卞墠绔紶鍏?鍘熷 topic 涓嶄竴瀹氬惈璁惧娈?鏁呬笉浠?topic 瑙ｆ瀽);鏃犺澶囨爣璇嗗垯涓嶈褰曘€?
     * 璁板綍澶辫触浠呭憡璀?涓嶅奖鍝嶅凡鎴愬姛鐨勫彂閫併€?
     */
    private void recordCustomDownlink(String deviceIdentification, String topic, String payload) {
        if (deviceIdentification == null || deviceIdentification.isEmpty()) {
            return;
        }
        try {
            DeviceCommandSaveVO saveVO = new DeviceCommandSaveVO();
            saveVO.setDeviceIdentification(deviceIdentification);
            saveVO.setCommandType(DeviceCommandTypeEnum.COMMAND_ISSUE.getValue());
            saveVO.setStatus(DeviceCommandStatusEnum.SUCCESS.getValue());
            // 鍘熷涓嬭 content 瀛?{topic,payload}:topic 鍘熸牱淇濈暀,鏌ヨ鏃惰В鏋?涓嶅崟鐙缓鍒?
            JSONObject raw = new JSONObject();
            raw.put("topic", topic);
            raw.put("payload", payload);
            saveVO.setContent(raw.toJSONString());
            saveDeviceCommand(saveVO);
        } catch (Exception e) {
            log.warn("璁板綍鑷畾涔変笅琛屽埌 device_command 澶辫触(涓嶅奖鍝嶅彂閫?: {}", e.getMessage());
        }
    }

    /**
     * Build DeviceCommand from DeviceCommandSaveVO.
     *
     * @param deviceCommandSaveVO input VO object
     * @return DeviceCommand object
     */
    private DeviceCommand buildDeviceCommand(DeviceCommandSaveVO deviceCommandSaveVO) {
        return BeanUtil.toBeanIgnoreError(deviceCommandSaveVO, DeviceCommand.class);
    }

    /**
     * Validate the DeviceCommandSaveVO object.
     *
     * @param deviceCommandSaveVO the input VO to validate
     * @return true if validation passes
     */
    private Boolean checkDeviceCommandSaveVO(DeviceCommandSaveVO deviceCommandSaveVO) {
        ArgumentAssert.notNull(deviceCommandSaveVO, "deviceCommandSaveVO cannot be null");
        ArgumentAssert.notBlank(deviceCommandSaveVO.getDeviceIdentification(), "deviceIdentification cannot be null");
        // Add other validation checks as required...
        return true;
    }

    /**
     * Processes a single command request for a device or all devices.
     *
     * @param commandRequest The command request parameters.
     * @return List of device command results
     */
    protected List<DeviceCommandResultVO> processSingleCommand(CommandIssueRequestParam commandRequest) {
        List<DeviceCommandResultVO> results = new ArrayList<>();

        // Retrieve the list of devices based on the identification provided.
        String productIdentification = commandRequest.getProductIdentification();
        String deviceIdentification = commandRequest.getDeviceIdentification();

        List<DeviceResultVO> deviceResultVOList;

        if (BizConstant.ALL.equals(deviceIdentification)) {
            // 鑾峰彇鎵€鏈夎澶囩殑缁撴灉鍒楄〃
            deviceResultVOList = getAllDeviceResultVOs(productIdentification);
        } else {
            // 鑾峰彇鍗曚釜璁惧鐨勭粨鏋滃垪琛?
            deviceResultVOList = getSingleDeviceResultVO(deviceIdentification);
        }
        // Process each device command.
        deviceResultVOList.forEach(deviceResultVO -> {
            // Build and send the command message.
            SendOutcome outcome = buildAndSendMessage(deviceResultVO.getDeviceIdentification(), commandRequest);

            DeviceCommandSaveVO deviceCommandSaveVO = createDeviceCommandSaveVO(deviceResultVO, outcome);

            // Save the command for record keeping.
            DeviceCommand savedCommand = saveDeviceCommand(deviceCommandSaveVO);

            // Convert to result VO and add to results
            DeviceCommandResultVO resultVO = BeanUtil.toBean(savedCommand, DeviceCommandResultVO.class);
            results.add(resultVO);
        });

        return results;
    }

    /**
     * Retrieves a list of all device result value objects for a specific product.
     *
     * @param productIdentification a product identification string used to find devices.
     * @return A list of DeviceResultVO objects, each representing a device linked to the product.
     */
    private List<DeviceResultVO> getAllDeviceResultVOs(String productIdentification) {
        DevicePageQuery devicePageQuery = new DevicePageQuery();
        devicePageQuery.setProductIdentification(productIdentification);
        devicePageQuery.setDeviceStatus(DeviceStatusEnum.ACTIVATED.getValue());
        return deviceService.getDeviceResultVOList(devicePageQuery);
    }

    /**
     * Retrieves the device result value object for a single device.
     *
     * @param deviceIdentification The device's unique identifier.
     * @return A list containing the single DeviceResultVO.
     */
    private List<DeviceResultVO> getSingleDeviceResultVO(String deviceIdentification) {
        Optional<DeviceCacheVO> deviceCacheVOOptional = linkCacheDataHelper.getDeviceCacheVO(deviceIdentification);
        return deviceCacheVOOptional.map(deviceCacheVO -> Collections.singletonList(BeanUtil.toBeanIgnoreError(deviceCacheVO, DeviceResultVO.class))).orElse(Collections.emptyList());
    }

    /**
     * Creates a DeviceCommandSaveVO object based on the command request and response.
     *
     * @param deviceResultVO The device result value object.
     * @param response       The response from the MQTT broker.
     * @return A populated DeviceCommandSaveVO object.
     */
    private DeviceCommandSaveVO createDeviceCommandSaveVO(DeviceResultVO deviceResultVO, SendOutcome outcome) {
        R response = outcome.response();
        DeviceCommandSaveVO deviceCommandSaveVO = new DeviceCommandSaveVO();
        deviceCommandSaveVO.setDeviceIdentification(deviceResultVO.getDeviceIdentification());
        deviceCommandSaveVO.setCommandType(DeviceCommandTypeEnum.COMMAND_ISSUE.getValue());
        deviceCommandSaveVO.setStatus(response.getIsSuccess()
                ? DeviceCommandStatusEnum.SUCCESS.getValue()
                : DeviceCommandStatusEnum.FAILURE.getValue());
        // content 瀛樸€屽疄闄呭彂鍑虹殑鍛戒护鎶ユ枃銆?cloudReq,鍚?serviceCode/cmd/params/versionNo/topic),渚涘巻鍙插睍绀恒€乼opic 鏌ヨ涓庝竴閿噸鍙?
        // 娲惧彂缁撴灉鐣欑棔鍒?remark銆俿erviceCode/cmd/鐗堟湰/topic 鏌ヨ鏃剁敱 content 瑙ｆ瀽,涓嶅崟鐙缓鍒椼€?
        deviceCommandSaveVO.setContent(buildCommandRecordContent(outcome));
        deviceCommandSaveVO.setRemark(response.toString());
        return deviceCommandSaveVO;
    }

    private String buildCommandRecordContent(SendOutcome outcome) {
        try {
            JSONObject content = JSON.parseObject(outcome.sentPayload());
            content.put("topic", outcome.topic());
            return content.toJSONString();
        } catch (Exception e) {
            JSONObject content = new JSONObject();
            content.put("topic", outcome.topic());
            content.put("payload", outcome.sentPayload());
            return content.toJSONString();
        }
    }

    /**
     * Builds and sends a command message to the device.
     *
     * @param deviceIdentification The deviceIdentification value object.
     * @param commandRequest       The command issue request parameters.
     * @return The response from the MQTT Or WebSocket broker.
     */
    private SendOutcome buildAndSendMessage(String deviceIdentification, CommandIssueRequestParam commandRequest) {
        // Retrieve the device cache VO from the cache
        Optional<DeviceCacheVO> deviceCacheVOOptional = linkCacheDataHelper.getDeviceCacheVO(deviceIdentification);
        ArgumentAssert.isTrue(deviceCacheVOOptional.isPresent(), "Device does not exist!");
        DeviceCacheVO deviceCacheVO = deviceCacheVOOptional.get();
        // Build the encryption details if all necessary information is present
        Optional<EncryptionDetailsDTO> encryptionDetailsOpt = Optional.of(deviceCacheVO).map(drv -> EncryptionDetailsDTO.builder().mId(Long.valueOf(SnowflakeIdUtil.nextId())).signKey(drv.getSignKey()).encryptKey(drv.getEncryptKey()).encryptVector(drv.getEncryptVector()).cipherFlag(drv.getEncryptMethod()).build());

        // 鏋勯€犲懡浠や笟鍔′綋 JSON 涓层€俠uildCommandMessage 鍐呴儴宸?JSON.toJSONString 涓€娆?
        // 杩欓噷涓嶈兘鍐?.map(JSON::toJSONString)(浼氭妸 JSON 涓插綋瀵硅薄鍐嶅簭鍒楀寲 鈫?dataBody 澶氶噸杞箟)銆?
        // 鍗曟搴忓垪鍖栫殑 JSON 涓蹭氦缁?buildResponse,鏄庢枃鏃跺叾鍐呴儴浼氳繕鍘熸垚瀵硅薄濉炶繘 dataBody銆?
        String commandMessageJson = Optional.ofNullable(commandRequest).map(cr -> buildCommandMessage(deviceCacheVO, cr)).orElse("{}");

        // Try to build the response using the encryption details
        Optional<ProtocolDataMessageDTO> handleResultOpt = encryptionDetailsOpt.flatMap(encryptionDetails -> {
            log.info("澶勭悊鎶ユ枃鍔犲瘑....commandMessageJson:{},encryptionDetails:{}", commandMessageJson, JSON.toJSONString(encryptionDetails));
            try {
                // Attempt to build the response with encryption details and return as an Optional
                return Optional.ofNullable(protocolMessageAdapter.buildResponse(commandMessageJson, encryptionDetails));
            } catch (Exception e) {
                // Log and handle any exceptions that occur during response building
                log.error("Failed to build the response due to an exception....commandMessageJson:{},encryptionDetails:{}", commandMessageJson, JSON.toJSONString(encryptionDetails), e);
                return Optional.empty();
            }
        });

        // Prepare the MQTT message content with a default response if handleResult is absent
        String messageContent = handleResultOpt.map(JSON::toJSONString).orElseGet(() -> {
            // Log the absence of handleResult and use a default empty message
            log.warn("No response object was constructed; using default empty message.");
            return "{}";
        });

        // Generate the response topic string
        String responseTopic = generateResponseTopic(deviceCacheVO);

        // 鎸変骇鍝佸崗璁被鍨嬪垎娴佷笅琛?鏀舵暃鍒板叡浜淳鍙戝櫒;鍗忚瑙ｆ瀽涓嶅嚭鐢辨淳鍙戝櫒鍏滃簳 MQTT銆?
        // protocolType 鍙栧€煎悓 ProtocolTypeEnum.getValue():MQTT 璧?topic,WebSocket 璧?clientId銆?
        String protocolType = linkCacheDataHelper
                .resolveProtocolType(deviceCacheVO.getProductIdentification(),
                        deviceCacheVO.getBoundProductVersionNo())
                .orElse(null);
        R response = deviceDownlinkFacade.dispatch(DownlinkCommand.builder()
                .protocolType(protocolType)
                .tenantId(String.valueOf(AuthUtil.getTenantId()))
                .clientId(deviceCacheVO.getClientId())
                .deviceIdentification(deviceCacheVO.getDeviceIdentification())
                .topic(responseTopic)
                .qos(QosEnum.EXACTLY_ONCE.getValue().toString())
                .payload(messageContent)
                .build());
        return new SendOutcome(response, commandMessageJson, responseTopic);
    }

    /** 涓嬪彂缁撴灉:dispatch 鍝嶅簲 + 瀹為檯鍙戝嚭鐨勫懡浠ゆ姤鏂?cloudReq),钀藉簱鍒?content 渚涘睍绀轰笌閲嶅彂銆?*/
    private record SendOutcome(R response, String sentPayload, String topic) {}

    /**
     * Generates a response topic string using the provided version and device ID.
     *
     * @param deviceCacheVO The device result value object.
     * @return A complete response topic string.
     */
    protected String generateResponseTopic(DeviceCacheVO deviceCacheVO) {
        // Determine the device node type using Optional and the enum's fromValue method
        DeviceNodeTypeEnum deviceNodeTypeEnum = Optional.ofNullable(deviceCacheVO.getNodeType())
                .flatMap(DeviceNodeTypeEnum::fromValue)
                .orElse(DeviceNodeTypeEnum.ORDINARY);

        // Get the SDK version, defaulting to "defaultSdkVersion" if not present
        String sdkVersion = Optional.ofNullable(deviceCacheVO.getDeviceSdkVersion()).orElse("defaultSdkVersion");

        // Determine the device ID based on the node type
        String deviceId;
        if (DeviceNodeTypeEnum.SUBDEVICE.equals(deviceNodeTypeEnum)) {
            // Use gatewayId if the device is a subdevice or gateway
            deviceId = Optional.ofNullable(deviceCacheVO.getGatewayId()).orElse("defaultGatewayId");
        } else {
            // Use deviceIdentification for ordinary devices
            deviceId = Optional.ofNullable(deviceCacheVO.getDeviceIdentification()).orElse("defaultDeviceIdentification");
        }

        // Construct the response topic string
        return String.format("/%s/devices/%s%s", sdkVersion, deviceId, "/command");
    }

    /**
     * Build command message.
     *
     * @param deviceCacheVO  device result VO
     * @param commandRequest command request
     * @return command message
     */
    private String buildCommandMessage(DeviceCacheVO deviceCacheVO, CommandIssueRequestParam commandRequest) {
        // Adapter logic to build the command message should be placed here.
        commandRequest.setDeviceIdentification(deviceCacheVO.getDeviceIdentification());
        return JSON.toJSONString(commandRequest);
    }

}

