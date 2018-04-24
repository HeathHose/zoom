package com.zoom.mongo;

/**
 * Created by liangbo.zhou on 18-4-24.
 */
public class MongodbTemplateContextHolder{

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setMongoDBTemplate(String mongodbTemplateType) {
        contextHolder.set(mongodbTemplateType);
    }

    public static String getMongoDBTemplate() {
        return contextHolder.get();
    }

    public static void clearMongoDBTemplate() {
        contextHolder.remove();
    }
}