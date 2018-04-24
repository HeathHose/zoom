package com.zoom.mongo;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by liangbo.zhou on 18-4-24.
 */
public class MongoDBTemplate extends AbstractMongoDBRoutingMongoTemplate {

    public MongoDBTemplate() {
    }

    public MongoTemplate getMongoTemplate() {
        return determineMongoTemplate();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return MongodbTemplateContextHolder.getMongoDBTemplate();
    }

}
