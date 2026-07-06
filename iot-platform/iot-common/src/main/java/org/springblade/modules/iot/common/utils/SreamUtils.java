package org.springblade.modules.iot.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SreamUtils {

    /** RTSP账号密码编码拼接 */
    public static String buildAuthRtspUrl(String rawUrl, String user, String pwd) throws UnsupportedEncodingException {
        if(rawUrl.startsWith("rtsp://")){
            String body = rawUrl.substring(7);
            String auth = URLEncoder.encode(user, "UTF-8") + ":" + URLEncoder.encode(pwd, "UTF-8");
            return "rtsp://" + auth + "@" + body;
        }
        return rawUrl;
    }
}
