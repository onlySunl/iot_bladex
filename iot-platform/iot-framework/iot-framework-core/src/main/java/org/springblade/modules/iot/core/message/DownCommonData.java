package org.springblade.modules.iot.core.message;

import cn.hutool.json.JSONObject;
import lombok.Data;

@Data
public class DownCommonData {

    private String imei;

    private String imsi;

    private String meterNo;

    private String deviceModel;

    private String deviceName;

    private String companyNo;

    private JSONObject configuration;

    private String longitude;

    private String latitude;
}
