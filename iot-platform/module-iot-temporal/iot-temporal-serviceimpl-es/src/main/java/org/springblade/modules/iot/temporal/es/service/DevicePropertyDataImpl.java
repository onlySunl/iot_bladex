package org.springblade.modules.iot.temporal.es.service;


import cn.hutool.core.util.StrUtil;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.json.JsonData;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.modules.iot.IDevicePropertyData;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import org.springblade.modules.iot.api.device.dto.DevicePropertyCache;
import org.springblade.modules.iot.api.device.service.RemoteIotDeviceService;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.temporal.es.convert.EsDevicePropertyConvert;
import org.springblade.modules.iot.temporal.es.dao.DocThingModelMessageMapper;
import org.springblade.modules.iot.temporal.es.document.DocDeviceProperty;
import org.springblade.modules.iot.api.device.dto.DeviceProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DevicePropertyDataImpl implements IDevicePropertyData {

    @Autowired
    private ElasticsearchOperations template;
    @Autowired
    private DocThingModelMessageMapper docThingModelMessageMapper;
    @Autowired
    private RemoteIotDeviceService deviceApi;

    private final Set<String> indexSet = new HashSet<>();
    @Override
    public List<DeviceProperty> findDevicePropertyHistory(Long deviceId, String name, long start, long end, int size) {
        DeviceInfo deviceInfo = deviceApi.getDeviceInfoFromCache(deviceId);

        String index = getIndex(deviceInfo.getProductKey(), name);
        NativeQuery query = new NativeQueryBuilder()
                .withQuery(q -> q.bool(b -> b
                        .must(m -> m.term(t -> t.field("deviceId").value(FieldValue.of(deviceId))))
                        .must(m -> m.range(RangeQuery.of(r -> r.untyped(ur -> ur.field("time")
                                .from(JsonData.of(start))
                                .to(JsonData.of(end))))))
                ))
                .withSort(SortOptions.of(s -> s.field(FieldSort.of(f -> f.field("time").order(SortOrder.Asc)))))
                .build();
        SearchHits<DocDeviceProperty> result = template.search(query, DocDeviceProperty.class, IndexCoordinates.of(index));
        return result.getSearchHits().stream()
                .map(h -> EsDevicePropertyConvert.INSTANCE.convert(h.getContent()))
                .collect(Collectors.toList());
    }



    @Override
    public void addProperties(Long deviceId, Map<String, DevicePropertyCache> properties, long time) {
        if (Objects.isNull(deviceId) || CollectionUtil.isEmpty(properties)) {
            return;
        }
        DeviceInfo deviceInfo = deviceApi.getDeviceInfoFromCache(deviceId);
        // TODO:改成批量
        String deviceIdStr = String.valueOf(deviceId);
        properties.forEach((key, val) -> {
            DevicePropertyCache propertyCache = (DevicePropertyCache) val;
            String index = getIndex(deviceInfo.getProductKey(), key);
            long occurred = Objects.nonNull(propertyCache.getOccurred()) ? propertyCache.getOccurred() : time;
            template.save(
                    new DocDeviceProperty(UUID.randomUUID().toString(), deviceIdStr, key, propertyCache.getValue(), occurred),
                    IndexCoordinates.of(index)
            );
        });
    }

    private String getIndex(String pk, String name) {

        String index = String.format("device_property_%s_%s", pk, name).toLowerCase();
        if (!indexSet.contains(index)) {
            IndexCoordinates indexCoordinates = IndexCoordinates.of(index);
            if (!template.indexOps(indexCoordinates).exists()) {
                // 根据索引实体，获取mapping字段
                Document mapping = template.indexOps(indexCoordinates).createMapping(DocDeviceProperty.class);
                template.indexOps(indexCoordinates).create();
                // 创建索引mapping
                template.indexOps(indexCoordinates).putMapping(mapping);
            }
            indexSet.add(index);
        }
        return index;
    }

}
