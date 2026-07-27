package org.springblade.modules.iot.sdk.param;

import org.springblade.modules.iot.sdk.request.GetBaseEmployeeRequest;
import org.springblade.modules.iot.sdk.response.GetBaseEmployeeResponse;
import org.springblade.modules.iot.sdkcore.param.BaseParam;

public class GetBaseEmployeeParam extends BaseParam<GetBaseEmployeeRequest, GetBaseEmployeeResponse> {
    @Override
    protected String method() {
        return "openapi.employee.get";
    }

}
