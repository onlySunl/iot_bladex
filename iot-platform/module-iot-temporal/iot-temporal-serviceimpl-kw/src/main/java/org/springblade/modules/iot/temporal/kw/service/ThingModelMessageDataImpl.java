
package org.springblade.modules.iot.temporal.kw.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.iot.IThingModelMessageData;
import org.springblade.modules.iot.TimeData;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.temporal.kw.dao.KwJdbcTemplate;
import org.springblade.modules.iot.temporal.kw.dao.KwThingModelMessageMapper;
import org.springblade.modules.iot.temporal.kw.model.KwThingModelMessage;
import com.kaiwudb.util.KWTimestamp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ThingModelMessageDataImpl implements IThingModelMessageData {

    @Autowired
    private KwJdbcTemplate kwJdbcTemplate;

    @Autowired
    private KwThingModelMessageMapper thingModelMessageMapper;

    @Override
    public PageResult<ThingModelMessage> findByTypeAndIdentifier(Long deviceId, String type,
                                                                 String identifier,
                                                                 int page, int size) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(page);
        pageParam.setPageSize(size);

        IPage<KwThingModelMessage> iPage = thingModelMessageMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                Wrappers.lambdaQuery(KwThingModelMessage.class)
                        .eq(KwThingModelMessage::getDeviceId, deviceId)
                        .eq(StringUtils.isNotBlank(type), KwThingModelMessage::getType, type)
                        .eq(StringUtils.isNotBlank(identifier), KwThingModelMessage::getIdentifier, identifier)
                        .orderByDesc(KwThingModelMessage::getTime)
        );

        return new PageResult<>(iPage.getRecords().stream().map(r ->
                        new ThingModelMessage(r.getTime().toString(), r.getMid(),
                                deviceId, r.getProductKey(), r.getDeviceName(),
                                r.getUid(), r.getType(), r.getIdentifier(), r.getCode(),
                                parseDataSafe(r.getData()),
                                r.getTime().getTime(), r.getReportTime(), null))
                .collect(Collectors.toList()), iPage.getTotal());
    }

    @Override
    public PageResult<ThingModelMessage> findByTypeAndDeviceIds(List<Long> deviceIds, String type,
                                                                String identifier,
                                                                int page, int size) {

        PageParam pageParam = new PageParam();
        pageParam.setPageNo(page);
        pageParam.setPageSize(size);
        IPage<KwThingModelMessage> iPage = thingModelMessageMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                Wrappers.lambdaQuery(KwThingModelMessage.class)
                        .eq(KwThingModelMessage::getType, type)
                        .in(!deviceIds.isEmpty(), KwThingModelMessage::getDeviceId, deviceIds)
                        .eq(StringUtils.isNotBlank(identifier), KwThingModelMessage::getIdentifier, identifier)
                        .orderByDesc(KwThingModelMessage::getTime)
        );

        return new PageResult<>(iPage.getRecords().stream().map(r ->
                        new ThingModelMessage(r.getTime().toString(), r.getMid(),
                                r.getDeviceId(), r.getProductKey(), r.getDeviceName(),
                                r.getUid(), r.getType(), r.getIdentifier(), r.getCode(),
                                parseDataSafe(r.getData()),
                                r.getTime().getTime(), r.getReportTime(), null))
                .collect(Collectors.toList()), iPage.getTotal());
    }

    @Override
    public List<TimeData> getDeviceMessageStatsWithUid(String uid, long start, long end) {
        String sql = "SELECT time,COUNT(*) AS data FROM(" +
                "SELECT TIMETRUNCATE(time,'1h') AS time FROM thing_model_message " +
                "WHERE time>=? AND time<=? " + (uid != null ? "AND uid=?" : "") +
                ") a GROUP BY time ORDER BY time ASC";

        List<Object> args = new ArrayList<>();
        args.add(start);
        args.add(end);
        if (uid != null) {
            args.add(uid);
        }

        return kwJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TimeData.class), args.toArray());
    }

    @Override
    public List<TimeData> getDeviceUpMessageStatsWithUid(String uid, Long start, Long end) {
        String sql = "SELECT time,COUNT(*) AS data FROM(" +
                "SELECT TIMETRUNCATE(time,'1h') AS time FROM thing_model_message " +
                "WHERE (type='property' AND identifier='report') OR type='event' ";
        StringBuilder sqlBuffer = new StringBuilder();
        sqlBuffer.append(sql);

        List<Object> args = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(uid)) {
            sqlBuffer.append(" AND uid=?");
            args.add(uid);
        }

        if (ObjectUtil.isNotEmpty(start) && ObjectUtil.isNotEmpty(end)) {
            sqlBuffer.append(" AND time>=? AND time<=?");
            args.add(start);
            args.add(end);
        }

        sqlBuffer.append(") a GROUP BY time ORDER BY time ASC");

        return kwJdbcTemplate.query(sqlBuffer.toString(), new BeanPropertyRowMapper<>(TimeData.class), args.toArray());
    }

    @Override
    public List<TimeData> getDeviceDownMessageStatsWithUid(String uid, Long start, Long end) {
        String sql = "SELECT time,COUNT(*) AS data FROM(" +
                "SELECT TIMETRUNCATE(time,1h) AS time FROM thing_model_message " +
                "WHERE (type='property' AND identifier='report') OR type='service' OR type= 'config' ";
        StringBuilder sqlBuffer = new StringBuilder();
        sqlBuffer.append(sql);

        List<Object> args = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(uid)) {
            sqlBuffer.append(" AND uid=?");
            args.add(uid);
        }

        if (ObjectUtil.isNotEmpty(start) && ObjectUtil.isNotEmpty(end)) {
            sqlBuffer.append(" AND time>=? AND time<=?");
            args.add(start);
            args.add(end);
        }

        sqlBuffer.append(") a GROUP BY time ORDER BY time ASC");

        return kwJdbcTemplate.query(sqlBuffer.toString(), new BeanPropertyRowMapper<>(TimeData.class), args.toArray());
    }

    @Override
    public void add(ThingModelMessage msg) {
        KwThingModelMessage message = BeanUtil.copyProperties(msg, KwThingModelMessage.class, "time", "data", "reportTime", "deviceName");
        message.setData(msg.getData() == null ? "{}" : JsonUtils.toJsonString(msg.getData()));
        message.setDeviceName(msg.getDn());
        message.setTime(new KWTimestamp(msg.getOccurred()));
        message.setReportTime(msg.getTime());
        thingModelMessageMapper.insert(message);
    }

    @Override
    public long count() {
        return thingModelMessageMapper.selectCount(null);
    }

    private Map<String, Object> parseDataSafe(String rawData) {
        if (StringUtils.isBlank(rawData)) {
            return new LinkedHashMap<>();
        }
        try {
            if (!JsonUtils.isJson(rawData)) {
                Map<String, Object> fallback = new LinkedHashMap<>();
                fallback.put("_raw", rawData);
                return fallback;
            }
            Map<String, Object> parsed = JsonUtils.parseObject(rawData, Map.class);
            return parsed == null ? new LinkedHashMap<>() : parsed;
        } catch (Exception ex) {
            Map<String, Object> fallback = new LinkedHashMap<>();
            fallback.put("_raw", rawData);
            return fallback;
        }
    }
}
