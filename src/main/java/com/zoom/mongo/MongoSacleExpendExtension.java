package com.zoom.mongo;

import com.zoom.core.ScaleExpandExtension;

import java.util.List;

/**
 * Created by liangbo.zhou on 18-5-10.
 */
public interface MongoSacleExpendExtension extends ScaleExpandExtension{

    void addNode(List<MongoBackend.ShardNodeInfo> shards);

    void delNode(List<MongoBackend.ShardNodeInfo> shards);
}
