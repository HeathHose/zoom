package com.zoom.mongo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liangbo.zhou on 18-4-24.
 */
public abstract class AbstractMongoDBRoutingMongoTemplate implements InitializingBean {

    private Map<Object, Object> targetMongoTemplates;

    private Object defaultTargetMongoTemplate;

    private Map<Object, MongoTemplate> resolvedMongoTemplates;

    private MongoTemplate resolvedDefaultMongoTemplate;

    public void setTargetMongoTemplates(Map<Object, Object> targetMongoTemplates) {
        this.targetMongoTemplates = targetMongoTemplates;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.targetMongoTemplates == null) {
            throw new IllegalArgumentException("Property 'targetMongoTemplates' is required");
        }
        this.resolvedMongoTemplates = new HashMap<Object, MongoTemplate>(this.targetMongoTemplates.size());
        for (Map.Entry<Object, Object> entry : this.targetMongoTemplates.entrySet()) {
            Object lookupKey = resolveSpecifiedLookupKey(entry.getKey());
            MongoTemplate mongoTemplate = resolveSpecifiedMongoTemplate(entry.getValue());
            this.resolvedMongoTemplates.put(lookupKey, mongoTemplate);
        }

        if (this.defaultTargetMongoTemplate != null) {
            this.resolvedDefaultMongoTemplate = resolveSpecifiedMongoTemplate(this.defaultTargetMongoTemplate);
        }
    }

    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        return lookupKey;
    }

    protected MongoTemplate resolveSpecifiedMongoTemplate(Object mongoTemplate) throws IllegalArgumentException {
        if (mongoTemplate instanceof MongoTemplate) {
            return (MongoTemplate) mongoTemplate;
        } else {
            throw new IllegalArgumentException(
                    "Illegal data source value - only [org.springframework.data.mongodb.core.MongoTemplate] and String supported: "
                            + mongoTemplate);
        }
    }

    protected MongoTemplate determineMongoTemplate() {
        Assert.notNull(this.resolvedMongoTemplates, "mongoTemplate router not initialized");
        Object lookupKey = determineCurrentLookupKey();
        MongoTemplate mongoTemplate = this.resolvedMongoTemplates.get(lookupKey);
        if (mongoTemplate == null && (lookupKey == null)) {
            mongoTemplate = this.resolvedDefaultMongoTemplate;
        }
        if (mongoTemplate == null) {
            throw new IllegalStateException("Cannot determine target MongoTemplate for lookup key [" + lookupKey + "]");
        }
        return mongoTemplate;
    }

    protected abstract Object determineCurrentLookupKey();
}
