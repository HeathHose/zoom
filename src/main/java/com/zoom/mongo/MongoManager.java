package com.zoom.mongo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by liangbo.zhou on 18-5-8.
 */
public class MongoManager implements MongoExtension, MongoSacleExpendExtension {
    @Autowired
    public MongoBackend backend;
    public MongoBase base;

    public <E> MongoBase<E> createMongoBase(E e) {
        MongoBase<E> base = new MongoBase<E>();
        return base;
    }

    @Override
    public void addCollection(String name) {

    }

    @Override
    public void delDatabase(String name) {

    }

    @Override
    public void offline(String name) {

    }

    @Override
    public void empty(String name) {

    }

    @Override
    public void dataConversionWithCollection(String ip, String name, String ip2, String name2) {

    }

    @Override
    public void initMongoCluster(List<MongoBackend.ShardNodeInfo> shards, List<MongoBackend.MongosNodeInfo> mongos,
                                 List<MongoBackend.ConfigServerInfo> configServers, String company, String task) {
        MongoBackend.InitMongoClusterCmd cmd = new MongoBackend.InitMongoClusterCmd(shards, mongos, configServers, company, task);
        backend.initMongoCluster(cmd);

    }


    @Override
    public void addNode(List<MongoBackend.ShardNodeInfo> shards) {
        MongoBackend.AddShardSetCmd cmd = new MongoBackend.AddShardSetCmd();
        cmd.setShards(shards);
        backend.addShardSet(cmd);
    }

    @Override
    public void delNode(List<MongoBackend.ShardNodeInfo> shards) {
        MongoBackend.DelShardSetCmd cmd = new MongoBackend.DelShardSetCmd();
        cmd.setShards(shards);
        backend.delShardSet(cmd);
    }

    //mysql 需要涉及数据保存内存和硬盘空间
    @Override
    public Map<String, String> checkCluster() {
        MongoBackend.CheckMongoClusterCmd cmd = new MongoBackend.CheckMongoClusterCmd();
        backend.checkMongoCluster(cmd);

    }


}
