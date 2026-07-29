package org.springblade.core.protocol.factory;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.protocol.model.EncryptionDetailsDTO;
import org.springblade.core.protocol.model.ProtocolDataMessageDTO;
import org.springblade.core.protocol.utils.ProtocolMessageSignatureVerifierUtils;
import org.springblade.core.protocol.utils.ProtocolRegexTopicVariableExtractorUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: thinglinks-cloud
 * @description: 协议信息适配器
 * @packagename: com.mqttsnet.thinglinks.mqtt.handler
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-30 15:08
 **/
@Slf4j
@Component
public class ProtocolMessageAdapter {

    public boolean validateProtocolData(String body) {
        return ProtocolMessageSignatureVerifierUtils.validateProtocolData(body);
    }

    public Map<String, String> extractVariables(String topic) {
        return ProtocolRegexTopicVariableExtractorUtils.extractVariables(topic);
    }

    public ProtocolDataMessageDTO parseProtocolDataMessage(String body) {
        return BeanUtil.toBean(JSON.parseObject(body), ProtocolDataMessageDTO.class);
    }

    public String decryptMessage(String body, EncryptionDetailsDTO encryptionDetailsDTO) throws Exception {
        String signKey = encryptionDetailsDTO.getSignKey();
        String encryptKey = encryptionDetailsDTO.getEncryptKey();
        String encryptVector = encryptionDetailsDTO.getEncryptVector();
        return ProtocolMessageSignatureVerifierUtils.decryptMessage(body, signKey, encryptKey, encryptVector);
    }

    public <T> ProtocolDataMessageDTO<T> buildResponse(ProtocolDataMessageDTO<T> protocolDataMessageDTO,
                                                       String resultDataBody, EncryptionDetailsDTO encryptionDetailsDTO) throws Exception {
        String signKey = encryptionDetailsDTO.getSignKey();
        String encryptKey = encryptionDetailsDTO.getEncryptKey();
        String encryptVector = encryptionDetailsDTO.getEncryptVector();
        Integer cipherFlag = encryptionDetailsDTO.getCipherFlag();
        //数据加密签名处理
        String dataBody = ProtocolMessageSignatureVerifierUtils.encryptMessage(resultDataBody, protocolDataMessageDTO.getHead().getMid(), cipherFlag, signKey, encryptKey,
                encryptVector);
        //JSON字符串转换为对象
        protocolDataMessageDTO = JSON.parseObject(dataBody, new TypeReference<ProtocolDataMessageDTO>() {
        });


        return protocolDataMessageDTO;
    }

    public <T> ProtocolDataMessageDTO buildResponse(String resultDataBody, EncryptionDetailsDTO encryptionDetailsDTO) throws Exception {
        Long mId = encryptionDetailsDTO.getMId();
        String signKey = encryptionDetailsDTO.getSignKey();
        String encryptKey = encryptionDetailsDTO.getEncryptKey();
        String encryptVector = encryptionDetailsDTO.getEncryptVector();
        Integer cipherFlag = encryptionDetailsDTO.getCipherFlag();
        //数据加密签名处理
        String dataBody = ProtocolMessageSignatureVerifierUtils.encryptMessage(resultDataBody, mId, cipherFlag, signKey, encryptKey,
                encryptVector);
        //JSON字符串转换为对象
        ProtocolDataMessageDTO protocolDataMessageDTO = JSON.parseObject(dataBody, new TypeReference<ProtocolDataMessageDTO>() {
        });


        return protocolDataMessageDTO;
    }
}
