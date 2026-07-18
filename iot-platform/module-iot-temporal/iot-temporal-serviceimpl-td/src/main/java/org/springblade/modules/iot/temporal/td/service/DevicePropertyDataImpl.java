/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot]
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.temporal.td.service;

import org.springblade.modules.iot.IDevicePropertyData;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.temporal.td.config.Constants;
import org.springblade.modules.iot.temporal.td.dao.TdTemplate;
import org.springblade.modules.iot.temporal.td.model.TbDeviceProperty;
import org.springblade.modules.iot.api.device.DeviceApi;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import org.springblade.modules.iot.api.device.dto.DeviceProperty;
import org.springblade.modules.iot.api.device.dto.DevicePropertyCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DevicePropertyDataImpl implements IDevicePropertyData {

    @Autowired
    private TdTemplate tdTemplate;

    @Resource
    private DeviceApi deviceApi;

    @Override
    public List<DeviceProperty> findDevicePropertyHistory(Long deviceId, String name, long start, long end, int size) {
        DeviceInfo device = deviceApi.getDeviceInfoFromCache(deviceId);
        if (device == null) {
            return new ArrayList<>();
        }

        String tableName = Constants.getProductPropertySTableName(device.getProductKey());
        String fieldName = name.contains(".") ? "`" + name + "`" : name;
        List<TbDeviceProperty> deviceProperties = tdTemplate.query(String.format(
                        "select time,%s as `value`,device_id from %s where device_id=? and time>=? and time<=? order by time asc limit 0,%d",
                        fieldName.toLowerCase(), tableName, size),
                new BeanPropertyRowMapper<>(TbDeviceProperty.class),
                deviceId, start, end);
        return deviceProperties.stream().map(property -> new DeviceProperty(
                        property.getTime().toString(),
                        property.getDeviceId(),
                        name,
                        property.getValue(),
                        property.getTime()))
                .collect(Collectors.toList());
    }

    @Override
    public void addProperties(Long deviceId, Map<String, DevicePropertyCache> properties, long time) {
        DeviceInfo device = deviceApi.getDeviceInfoFromCache(deviceId);
        if (device == null) {
            return;
        }

        Map<String, DevicePropertyCache> oldProperties = deviceApi.getPropertiesFromCache(deviceId);
        oldProperties.putAll(properties);

        StringBuilder fieldNames = new StringBuilder();
        StringBuilder fieldPlaceholders = new StringBuilder();
        List<Object> args = new ArrayList<>();
        args.add(time);

        oldProperties.forEach((key, value) -> {
            String fieldName = key.contains(".") ? "`" + key + "`" : key;
            fieldNames.append(fieldName).append(",");
            fieldPlaceholders.append("?,");
            args.add(stringifyValue(value.getValue()));
        });
        fieldNames.deleteCharAt(fieldNames.length() - 1);
        fieldPlaceholders.deleteCharAt(fieldPlaceholders.length() - 1);

        String sql = String.format("INSERT INTO %s (time,%s) USING %s TAGS ('%s') VALUES (?,%s);",
                Constants.getDevicePropertyTableName(deviceId),
                fieldNames,
                Constants.getProductPropertySTableName(device.getProductKey()),
                deviceId,
                fieldPlaceholders);

        tdTemplate.update(sql, args.toArray());
    }

    private Object stringifyValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean boolValue) {
            // TDengine TINYINT expects numeric values for bool columns.
            return boolValue ? 1 : 0;
        }
        if (value instanceof String stringValue) {
            if ("true".equalsIgnoreCase(stringValue)) {
                return 1;
            }
            if ("false".equalsIgnoreCase(stringValue)) {
                return 0;
            }
        }
        if (value instanceof Map || value instanceof List || value.getClass().isArray()) {
            return JsonUtils.toJsonString(value);
        }
        return value;
    }
}
