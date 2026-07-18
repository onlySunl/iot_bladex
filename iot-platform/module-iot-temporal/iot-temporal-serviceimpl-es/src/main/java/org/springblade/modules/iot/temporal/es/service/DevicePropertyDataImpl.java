package org.springblade.modules.iot.temporal.es.service;


import cn.hutool.core.util.StrUtil;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.springblade.modules.iot.IDevicePropertyData;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.temporal.es.convert.EsDevicePropertyConvert;
import org.springblade.modules.iot.temporal.es.dao.DocThingModelMessageMapper;
import org.springblade.modules.iot.temporal.es.document.DocDeviceProperty;
import org.springblade.modules.iot.DeviceProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevicePropertyDataImpl implements IDevicePropertyData {

    @Autowired
    private ElasticsearchOperations template;
    @Autowired
    private DocThingModelMessageMapper docThingModelMessageMapper;

    @Override
    public PageResult<DeviceProperty> findByDeviceIdAndIdentifier(Long deviceId, String identifier, int page, int size) {
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        boolBuilder.must(Query.of(q -> q.term(t -> t.field("deviceId").value(deviceId))));
        if (StrUtil.isNotBlank(identifier)) {
            boolBuilder.must(Query.of(q -> q.matchPhrase(m -> m.field("identifier").query(identifier))));
        }

        NativeQuery query = new NativeQueryBuilder()
                .withQuery(q -> q.bool(boolBuilder.build()))
                .withPageable(PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc("time"))))
                .build();

        SearchHits<DocDeviceProperty> result = template.search(query, DocDeviceProperty.class);
        return new PageResult<>(result.getSearchHits().stream()
                .map(m -> EsDevicePropertyConvert.INSTANCE.convert(m.getContent()))
                .collect(Collectors.toList()), result.getTotalHits());
    }

    @Override
    public PageResult<DeviceProperty> findByDeviceIdsAndIdentifier(List<Long> deviceIds, String identifier, int page, int size) {
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        if (deviceIds.size() > 0) {
            boolBuilder.must(Query.of(q -> q.terms(t -> t
                    .field("deviceId")
                    .terms(tv -> tv.value(deviceIds.stream()
                            .map(id -> co.elastic.clients.elasticsearch._types.FieldValue.of(id))
                            .collect(Collectors.toList()))))));
        }
        if (StrUtil.isNotBlank(identifier)) {
            boolBuilder.must(Query.of(q -> q.matchPhrase(m -> m.field("identifier").query(identifier))));
        }

        NativeQuery query = new NativeQueryBuilder()
                .withQuery(q -> q.bool(boolBuilder.build()))
                .withPageable(PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc("time"))))
                .build();

        SearchHits<DocDeviceProperty> result = template.search(query, DocDeviceProperty.class);
        return new PageResult<>(result.getSearchHits().stream()
                .map(m -> EsDevicePropertyConvert.INSTANCE.convert(m.getContent()))
                .collect(Collectors.toList()), result.getTotalHits());
    }

    @Override
    public void add(DeviceProperty property) {
        // TODO: 待实现
    }

    @Override
    public long count() {
        // TODO: 待实现
        return 0;
    }
}
