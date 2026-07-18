

package org.springblade.modules.iot.task.model;

import org.springblade.modules.iot.common.thing.ThingModelMessage;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DeviceActionMsg {

    public static final String TYPE = "device";

    private String type;

    private List<Service> services;

    @Data
    public static class Service {

        private String device;

        private String identifier;

        private String type;

        public String getType() {
            //identifier为set固定为属性设置，其它为服务调用
            if ("set".equals(identifier) ||
                    "get".equals(identifier)) {
                return ThingModelMessage.TYPE_PROPERTY;
            }
            return ThingModelMessage.TYPE_SERVICE;
        }

        private List<Parameter> inputData;

        public Map<String, Object> parseInputData() {
            Map<String, Object> data = new HashMap<>();
            for (Parameter p : inputData) {
                data.put(p.getIdentifier(), p.getValue());
            }
            return data;
        }

        @Data
        public static class Parameter {
            private String identifier;
            private Object value;
        }
    }
}
