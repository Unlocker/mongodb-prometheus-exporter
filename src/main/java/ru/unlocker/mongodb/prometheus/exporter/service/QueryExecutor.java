package ru.unlocker.mongodb.prometheus.exporter.service;

import com.mongodb.async.client.FindIterable;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoIterable;
import lombok.val;
import org.bson.BsonDocument;
import org.bson.Document;
import ru.unlocker.mongodb.prometheus.exporter.model.MetricSetting;
import ru.unlocker.mongodb.prometheus.exporter.model.MetricValue;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueryExecutor implements Function<MetricSetting, List<MetricValue>> {
    private final MongoClient mongoClient;

    @Override
    public List<MetricValue> apply(@NonNull MetricSetting setting) {
        MongoCollection<Document> coll = mongoClient.getDatabase(setting.getDatabase())
                .getCollection(setting.getCollection());

        switch (setting.getQueryType()) {
            case FIND:
                FindIterable<Document> findResult = coll.find(new BsonDocument());
                MongoIterable<Stream<MetricValue>> mapped = findResult
                        .map(document -> setting.getMetrics().stream().map(metricMapping -> {
                            final MetricValue.MetricValueBuilder builder = MetricValue.builder();
                            builder.name(metricMapping.getTarget());
                            builder.value(document.get(metricMapping.getSource(), 0d));
                            setting.getTags().forEach(tagMapping -> {
                                val tagValue = document.get(tagMapping.getSource());
                                if (tagValue != null) {
                                    builder.tag(tagMapping.getTarget(), tagValue.toString());
                                }
                            });
                            return builder.build();
                        }));
                List<Stream<MetricValue>> metricValues = new LinkedList<>();
                // mapped.into(metricValues, (List<Stream<MetricValue>> t, Throwable ex) -> metricValues.add(t));
                // mapped.
                // return
                break;
            case AGGREGATE:
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Query type '%s' is not supported", setting.getQueryType()));
        }
        throw new UnsupportedOperationException();
    }
}
