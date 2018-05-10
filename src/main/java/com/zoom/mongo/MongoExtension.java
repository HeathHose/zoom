package com.zoom.mongo;

import java.util.List;

/**
 * Created by liangbo.zhou on 18-4-9.
 */
public interface MongoExtension {

    void initMongoCluster(List<MongoBackend.ShardNodeInfo> shards, List<MongoBackend.MongosNodeInfo> mongos,
                          List<MongoBackend.ConfigServerInfo> configServers, String company, String task);

    void addCollection(String name);

    void delDatabase(String name);

    //offline collection but not delete data;
    void offline(String name);

    //empty collection
    void empty(String name );

    void dataConversionWithCollection(String ip,String name, String ip2,String name2);
}
