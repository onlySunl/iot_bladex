package org.springblade.modules.iot.sdk.param;


import org.springblade.modules.iot.sdk.request.DemoFileDownloadRequest;
import org.springblade.modules.iot.sdk.request.DemoFileUploadRequest;
import org.springblade.modules.iot.sdk.response.GetProductResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

/**
 * @author 六如
 */
public class DemoFileDownloadParam extends BaseParam<DemoFileDownloadRequest, Object> {
    @Override
    protected String method() {
        return "openapi.download";
    }
}
