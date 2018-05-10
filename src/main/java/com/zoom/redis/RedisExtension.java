package com.zoom.redis;

import java.util.List;

/**
 * Created by liangbo.zhou on 18-5-9.
 */
public interface RedisExtension {
    void initRedisCluster(List<RedisBackend.NodeInfo> nodes);
}
