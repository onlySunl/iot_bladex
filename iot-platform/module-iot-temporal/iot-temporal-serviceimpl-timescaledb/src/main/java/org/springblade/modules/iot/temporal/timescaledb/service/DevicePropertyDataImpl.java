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
package org.springblade.modules.iot.temporal.timescaledb.service;

import cn.hutool.core.convert.Convert;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PGTimestamp;
import org.springblade.modules.iot.IDevicePropertyData;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import org.springblade.modules.iot.api.device.dto.DeviceProperty;
import org.springblade.modules.iot.api.device.dto.DevicePropertyCache;
import org.springblade.modules.iot.api.device.service.RemoteIotDeviceService;
import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.api.thingmodel.service.RemoteIotThingModelService;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.temporal.timescaledb.config.Constants;
import org.springblade.modules.iot.temporal.timescaledb.dao.PgTemplate;
import org.springblade.modules.iot.temporal.timescaledb.dm.FieldParser;
import org.springblade.modules.iot.temporal.timescaledb.dm.PgField;
import org.springblade.modules.iot.temporal.timescaledb.model.PgDeviceProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DevicePropertyDataImpl implements IDevicePropertyData {

    @Autowired
    private PgTemplate pgTemplate;

    @Resource
    private RemoteIotDeviceService deviceApi;

    @Resource
    private RemoteIotThingModelService thingModelApi;

    @Override
    public List<DeviceProperty> findDevicePropertyHistory(Long deviceId, String name, long start, long end, int size) {
        DeviceInfo device = deviceApi.getDeviceInfoFromCache(deviceId);
        if (device == null) {
            return new ArrayList<>();
        }

        String tableName = Constants.getProductPropertySTableName(device.getProductKey());
        List<PgDeviceProperty> deviceProperties = pgTemplate.query(String.format(
                        "SELECT time,%s as value,device_id FROM %s WHERE device_id=? AND time>=? AND time<=? ORDER BY time ASC LIMIT %d OFFSET 0",
                        name.toLowerCase(), tableName, size),
                new BeanPropertyRowMapper<>(PgDeviceProperty.class),
                deviceId, new PGTimestamp(start), new PGTimestamp(end));
        return deviceProperties.stream().map(property -> new DeviceProperty(
                        property.getTime().toString(),
                        property.getDeviceId().toString(),
                        name,
                        property.getValue(),
                        property.getTime().getTime()))
                .collect(Collectors.toList());
    }

    @Override
    public void addProperties(Long deviceId, Map<String, DevicePropertyCache> properties, long time) {
        DeviceInfo device = deviceApi.getDeviceInfoFromCache(deviceId);
        if (device == null) {
            return;
        }

        ThingModel thingModel = thingModelApi.getThingModelByProductKeyFromCache(device.getProductKey());
        List<PgField> fieldList = FieldParser.parse(thingModel);
        Map<String, String> fieldTypeMap = fieldList.stream().collect(Collectors.toMap(PgField::getName, PgField::getType));
        Map<String, DevicePropertyCache> oldProperties = deviceApi.getPropertiesFromCache(deviceId);
        oldProperties.putAll(properties);

        StringBuilder fieldNames = new StringBuilder();
        StringBuilder fieldPlaceholders = new StringBuilder();
        List<Object> args = new ArrayList<>();
        args.add(new PGTimestamp(time));

        oldProperties.forEach((key, value) -> {
            fieldNames.append(key).append(",");
            fieldPlaceholders.append("?,");
            switch (fieldTypeMap.get(key)) {
                case "INTEGER":
                    args.add(Convert.toInt(value.getValue()));
                    break;
                case "BIGINT":
                    args.add(Convert.toLong(value.getValue()));
                    break;
                case "SMALLINT":
                    args.add(Convert.toShort(value.getValue()));
                    break;
                case "DOUBLE PRECISION":
                    args.add(Convert.toDouble(value.getValue()));
                    break;
                case "BOOLEAN":
                    args.add(Convert.toBool(value.getValue()));
                    break;
                case "VARCHAR":
                    args.add(stringifyValue(value.getValue()));
                    break;
                default:
                    args.add(stringifyValue(value.getValue()));
                    break;
            }
        });
        args.add(deviceId);

        fieldNames.deleteCharAt(fieldNames.length() - 1);
        fieldPlaceholders.deleteCharAt(fieldPlaceholders.length() - 1);

        String sql = String.format("INSERT INTO %s (time,%s,device_id) VALUES (?,%s,?);",
                Constants.getProductPropertySTableName(device.getProductKey()),
                fieldNames,
                fieldPlaceholders);

        pgTemplate.update(sql, args.toArray());
    }

    private String stringifyValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Map || value instanceof List || value.getClass().isArray()) {
            return JsonUtils.toJsonString(value);
        }
        return Convert.toStr(value);
    }
}
