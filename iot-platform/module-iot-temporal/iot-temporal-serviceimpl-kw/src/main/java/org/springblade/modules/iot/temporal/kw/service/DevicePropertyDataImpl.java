
package org.springblade.modules.iot.temporal.kw.service;

import cn.hutool.core.convert.Convert;
import org.springblade.modules.iot.IDevicePropertyData;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.temporal.kw.config.Constants;
import org.springblade.modules.iot.temporal.kw.dao.KwJdbcTemplate;
import org.springblade.modules.iot.temporal.kw.dm.FieldParser;
import org.springblade.modules.iot.temporal.kw.dm.KwField;
import org.springblade.modules.iot.temporal.kw.model.KwDeviceProperty;
import org.springblade.modules.iot.api.device.DeviceApi;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import org.springblade.modules.iot.api.device.dto.DeviceProperty;
import org.springblade.modules.iot.api.device.dto.DevicePropertyCache;
import org.springblade.modules.iot.api.thingmodel.ThingModelApi;
import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import com.kaiwudb.util.KWTimestamp;
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
    private KwJdbcTemplate kwJdbcTemplate;

    @Resource
    private DeviceApi deviceApi;

    @Resource
    private ThingModelApi thingModelApi;

    @Override
    public List<DeviceProperty> findDevicePropertyHistory(Long deviceId, String name, long start, long end, int size) {
        DeviceInfo device = deviceApi.getDeviceInfoFromCache(deviceId);
        if (device == null) {
            return new ArrayList<>();
        }

        String tableName = Constants.getProductPropertyTableName(device.getProductKey());
        List<KwDeviceProperty> deviceProperties = kwJdbcTemplate.query(String.format(
                        "SELECT time,%s as value,device_id FROM %s WHERE device_id=? AND time>=? AND time<=? ORDER BY time ASC LIMIT %d OFFSET 0",
                        name.toLowerCase(), tableName, size),
                new BeanPropertyRowMapper<>(KwDeviceProperty.class),
                deviceId, new KWTimestamp(start), new KWTimestamp(end));
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
        List<KwField> fieldList = FieldParser.parse(thingModel);
        Map<String, String> fieldTypeMap = fieldList.stream().collect(Collectors.toMap(KwField::getName, KwField::getType));
        Map<String, DevicePropertyCache> oldProperties = deviceApi.getPropertiesFromCache(deviceId);
        oldProperties.putAll(properties);

        StringBuilder fieldNames = new StringBuilder();
        StringBuilder fieldPlaceholders = new StringBuilder();
        List<Object> args = new ArrayList<>();
        args.add(new KWTimestamp(time));

        oldProperties.forEach((key, value) -> {
            fieldNames.append(key).append(",");
            fieldPlaceholders.append("?,");
            switch (fieldTypeMap.get(key)) {
                case "INT4":
                    args.add(Convert.toInt(value.getValue()));
                    break;
                case "INT2":
                    args.add(Convert.toShort(value.getValue()));
                    break;
                case "INT8":
                    args.add(Convert.toLong(value.getValue()));
                    break;
                case "FLOAT4":
                case "FLOAT8":
                    args.add(Convert.toDouble(value.getValue()));
                    break;
                case "NCHAR":
                case "VARCHAR":
                case "NVARCHAR":
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
                Constants.getProductPropertyTableName(device.getProductKey()),
                fieldNames,
                fieldPlaceholders);

        kwJdbcTemplate.update(sql, args.toArray());
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
