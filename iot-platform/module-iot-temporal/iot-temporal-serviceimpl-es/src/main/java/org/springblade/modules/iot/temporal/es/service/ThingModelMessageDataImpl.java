package org.springblade.modules.iot.temporal.es.service;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.DateHistogramBucket;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.springblade.modules.iot.IThingModelMessageData;
import org.springblade.modules.iot.TimeData;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.temporal.es.convert.EsThingModelMessageConvert;
import org.springblade.modules.iot.temporal.es.dao.ThingModelMessageRepository;
import org.springblade.modules.iot.temporal.es.document.DocThingModelMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThingModelMessageDataImpl implements IThingModelMessageData {

    @Autowired
    private ElasticsearchOperations template;
    @Autowired
    private ThingModelMessageRepository thingModelMessageRepository;

    @Override
    public PageResult<ThingModelMessage> findByTypeAndIdentifier(Long deviceId, String type,
                                                                 String identifier,
                                                                 int page, int size) {
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        boolBuilder.must(Query.of(q -> q.term(t -> t.field("deviceId").value(deviceId))));
        if (StrUtil.isNotBlank(type)) {
            boolBuilder.must(Query.of(q -> q.term(t -> t.field("type").value(type))));
        }
        if (StrUtil.isNotBlank(identifier)) {
            boolBuilder.must(Query.of(q -> q.matchPhrase(m -> m.field("identifier").query(identifier))));
        }

        NativeQuery query = new NativeQueryBuilder()
                .withQuery(q -> q.bool(boolBuilder.build()))
                .withPageable(PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc("time"))))
                .build();

        SearchHits<DocThingModelMessage> result = template.search(query, DocThingModelMessage.class);
        return new PageResult<>(result.getSearchHits().stream()
                .map(m -> EsThingModelMessageConvert.INSTANCE.convert(m.getContent()))
                .collect(Collectors.toList()), result.getTotalHits());
    }

    @Override
    public PageResult<ThingModelMessage> findByTypeAndDeviceIds(List<Long> deviceIds, String type,
                                                             String identifier,
                                                             int page, int size) {
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        boolBuilder.must(Query.of(q -> q.term(t -> t.field("type").value(type))));
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

        SearchHits<DocThingModelMessage> result = template.search(query, DocThingModelMessage.class);
        return new PageResult<>(result.getSearchHits().stream()
                .map(m -> EsThingModelMessageConvert.INSTANCE.convert(m.getContent()))
                .collect(Collectors.toList()), result.getTotalHits());
    }

    @Override
    public List<TimeData> getDeviceMessageStatsWithUid(String uid, long start, long end) {
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        boolBuilder.must(Query.of(q -> q.range(r -> r
                .field("time")
                .gte(co.elastic.clients.json.JsonData.of(start))
                .lte(co.elastic.clients.json.JsonData.of(end)))));
        if (uid != null) {
            boolBuilder.must(Query.of(q -> q.term(t -> t.field("uid").value(uid))));
        }

        NativeQuery query = new NativeQueryBuilder()
                .withQuery(q -> q.bool(boolBuilder.build()))
                .withAggregations("agg", Aggregation.of(a -> a
                        .dateHistogram(dh -> dh
                                .field("time")
                                .calendarInterval(ci -> ci.hours(1)))))
                .build();

        var searchHits = template.search(query, DocThingModelMessage.class);
        var aggregations = searchHits.getAggregations();
        if (aggregations == null) {
            return new ArrayList<>();
        }
        var histogram = aggregations.aggregations().get("agg");
        if (histogram == null) {
            return new ArrayList<>();
        }
        var dateHistogram = histogram.aggregation().getAggregate().dateHistogram();

        List<TimeData> data = new ArrayList<>();
        for (DateHistogramBucket bucket : dateHistogram.buckets().array()) {
            long seconds = bucket.key() / 1000;
            data.add(new TimeData(seconds * 1000, bucket.docCount()));
        }

        return data;
    }

    @Override
    public List<TimeData> getDeviceUpMessageStatsWithUid(String uid, Long start, Long end) {
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        if (ObjectUtil.isNotEmpty(start) && ObjectUtil.isNotEmpty(end)) {
            boolBuilder.must(Query.of(q -> q.range(r -> r
                    .field("time")
                    .gte(co.elastic.clients.json.JsonData.of(start))
                    .lte(co.elastic.clients.json.JsonData.of(end)))));
        }
        if (ObjectUtil.isNotEmpty(uid)) {
            boolBuilder.must(Query.of(q -> q.term(t -> t.field("uid").value(uid))));
        }

        // type='property' and identifier='report', or type='event'
        boolBuilder.must(Query.of(q -> q.bool(b -> b
                .should(Query.of(q2 -> q2.bool(b2 -> b2
                        .must(Query.of(q3 -> q3.term(t -> t.field("type").value("property"))))
                        .must(Query.of(q3 -> q3.term(t -> t.field("identifier").value("report")))))))
                .should(Query.of(q2 -> q2.term(t -> t.field("type").value("event")))))));

        NativeQuery query = new NativeQueryBuilder()
                .withQuery(q -> q.bool(boolBuilder.build()))
                .withAggregations("agg", Aggregation.of(a -> a
                        .dateHistogram(dh -> dh
                                .field("time")
                                .calendarInterval(ci -> ci.hours(1)))))
                .build();

        var searchHits = template.search(query, DocThingModelMessage.class);
        var aggregations = searchHits.getAggregations();
        if (aggregations == null) {
            return new ArrayList<>();
        }
        var histogram = aggregations.aggregations().get("agg");
        if (histogram == null) {
            return new ArrayList<>();
        }
        var dateHistogram = histogram.aggregation().getAggregate().dateHistogram();

        List<TimeData> data = new ArrayList<>();
        for (DateHistogramBucket bucket : dateHistogram.buckets().array()) {
            long seconds = bucket.key() / 1000;
            data.add(new TimeData(seconds * 1000, bucket.docCount()));
        }

        return data;
    }

    @Override
    public List<TimeData> getDeviceDownMessageStatsWithUid(String uid, Long start, Long end) {
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        if (ObjectUtil.isNotEmpty(start) && ObjectUtil.isNotEmpty(end)) {
            boolBuilder.must(Query.of(q -> q.range(r -> r
                    .field("time")
                    .gte(co.elastic.clients.json.JsonData.of(start))
                    .lte(co.elastic.clients.json.JsonData.of(end)))));
        }
        if (ObjectUtil.isNotEmpty(uid)) {
            boolBuilder.must(Query.of(q -> q.term(t -> t.field("uid").value(uid))));
        }

        // type='property' and identifier!='report', or type='service', or type='config'
        boolBuilder.must(Query.of(q -> q.bool(b -> b
                .should(Query.of(q2 -> q2.bool(b2 -> b2
                        .must(Query.of(q3 -> q3.term(t -> t.field("type").value("property"))))
                        .mustNot(Query.of(q3 -> q3.term(t -> t.field("identifier").value("report")))))))
                .should(Query.of(q2 -> q2.term(t -> t.field("type").value("service"))))
                .should(Query.of(q2 -> q2.term(t -> t.field("type").value("config")))))));

        NativeQuery query = new NativeQueryBuilder()
                .withQuery(q -> q.bool(boolBuilder.build()))
                .withAggregations("agg", Aggregation.of(a -> a
                        .dateHistogram(dh -> dh
                                .field("time")
                                .calendarInterval(ci -> ci.hours(1)))))
                .build();

        var searchHits = template.search(query, DocThingModelMessage.class);
        var aggregations = searchHits.getAggregations();
        if (aggregations == null) {
            return new ArrayList<>();
        }
        var histogram = aggregations.aggregations().get("agg");
        if (histogram == null) {
            return new ArrayList<>();
        }
        var dateHistogram = histogram.aggregation().getAggregate().dateHistogram();

        List<TimeData> data = new ArrayList<>();
        for (DateHistogramBucket bucket : dateHistogram.buckets().array()) {
            long seconds = bucket.key() / 1000;
            data.add(new TimeData(seconds * 1000, bucket.docCount()));
        }

        return data;
    }

    @Override
    public void add(ThingModelMessage msg) {
        thingModelMessageRepository.save(EsThingModelMessageConvert.INSTANCE.convertDoc(msg));
    }

    @Override
    public long count() {
        return thingModelMessageRepository.count();
    }
}
