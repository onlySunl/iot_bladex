package org.springblade.core.dinger.process;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Lists;
import org.springblade.basic.jackson.JsonCoreUtils;
import org.springblade.basic.utils.StrPool;
import org.springblade.core.dinger.abstraction.NoticeAbstract;
import org.springblade.core.dinger.constant.DingerConstants;
import org.springblade.core.dinger.content.FeiShuInfo;
import org.springblade.core.dinger.content.FeiShuInfoReq;
import org.springblade.core.dinger.content.FeiShuRobotResult;
import org.springblade.core.dinger.properties.FeiShuProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 飞书通知
 */
@Component("feiShuNoticeProcessor")
@Slf4j
public class FeiShuNoticeProcessor extends NoticeAbstract implements INoticeProcessor {


    public FeiShuNoticeProcessor() {
    }

    /**
     * @param obj 消息信息
     */
    @Override
    public Object sendNotice(Object obj) {
        FeiShuInfoReq feiShuInfoReq = (FeiShuInfoReq) obj;
        FeiShuProperties feiShuProperties = feiShuInfoReq.getFeiShuProperties();
        FeiShuInfo feiShuInfo = feiShuInfoReq.getFeiShuInfo();
        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        getRequiredProperty(feiShuProperties::getToken, "token为空,请配置");
        getRequiredProperty(feiShuProperties::getWebHook, "url为空,请配置");
        getRequiredProperty(feiShuProperties::getAppId, "应用ID为空,请配置");
        getRequiredProperty(feiShuProperties::getAppSecret, "应用密钥为空,请配置");
        List<String> userIdByMobile = getUserIdByMobile(feiShuProperties);
        String result = HttpRequest.post(feiShuProperties.getWebHook()).header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue()).body(JSON.toJSONString(convertContent(feiShuInfo, StrPool.YES.equals(feiShuProperties.getIsAtAll()), userIdByMobile))).timeout(2000).execute().body();
        return JSON.parseObject(result, FeiShuRobotResult.class);
    }

    // 处理@指定人消息格式
    public FeiShuInfo convertContent(FeiShuInfo feiShuInfo, Boolean isAtAll, List<String> userIdByMobile) {
        // @ 指定用户
        // <at user_id="ou_xxx">Name</at> //取值须使用 open_id 或 user_id 来 @ 指定人
        // @ 多个指定用户
        // <at user_id="ou_xxx1">Name1</at><at user_id="ou_xxx2">Name2</at> //取值须使用 open_id 或 user_id 来 @ 指定人
        // @ 所有人
        // <at user_id="all">所有人</at>
        if (CollectionUtil.isEmpty(userIdByMobile)) {
            return feiShuInfo;
        }
        StringBuffer appendText = new StringBuffer();
        if (isAtAll) {
            appendText.append("<at user_id=\"all\">所有人</at>");
        } else {
            userIdByMobile.forEach(userId -> {
                appendText.append("<at user_id=\"" + userId + "\">" + userId + "</at>");
            });
        }
        // 拼接换行符号
        appendText.append("\n").append(feiShuInfo.getContent().getText());
        feiShuInfo.getContent().setText(appendText.toString());
        return feiShuInfo;
    }

    // 根据手机号获取用户id
    public List<String> getUserIdByMobile(FeiShuProperties feiShuProperties) {
        String accessToken = getAccessToken(feiShuProperties);
        // 获取人员ID
        JSONObject userBody = new JSONObject();
        // 集合转数组
        String[] array = feiShuProperties.getAtMobiles().toArray(new String[]{});
        userBody.put("mobiles", array);
        userBody.put("include_resigned", true);
        String result = HttpRequest.post(DingerConstants.BATCH_GET_ID_URL)
                .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .header("Authorization", "Bearer " + accessToken)
                .body(JSON.toJSONString(userBody)).timeout(2000).execute().body();
        List list = Lists.newArrayList();
        try {
            Map<String, Object> map = JsonCoreUtils.parse(result, Map.class);
            list = JsonCoreUtils.getValue(map, "data.user_list.{user_id}", List.class);
        } catch (Exception e) {
            log.error("获取用户ID失败：{}", e.getMessage());
        }
        return list;
    }


    // 获取认证token
    public String getAccessToken(FeiShuProperties feiShuProperties) {
        // 获取认证token
        JSONObject tokenBody = new JSONObject();
        tokenBody.put("app_id", feiShuProperties.getAppId());
        tokenBody.put("app_secret", feiShuProperties.getAppSecret());
        String result = HttpRequest.post(DingerConstants.TENANT_ACCESS_TOKEN_URL).header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue()).body(JSON.toJSONString(tokenBody)).timeout(2000).execute().body();
        String token = "";
        try {
            Map<String, Object> map = JsonCoreUtils.parse(result, Map.class);
            int code = JsonCoreUtils.getValue(map, "code", Integer.class);
            if (code == 0) {
                token = JsonCoreUtils.getValue(map, "tenant_access_token", String.class);
            }
        } catch (Exception e) {
            log.error("获取认证失败：{}", e.getMessage());
        }
        return token;
    }

}
