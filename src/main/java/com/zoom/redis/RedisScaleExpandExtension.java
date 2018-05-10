package com.zoom.redis;

import com.zoom.core.ScaleExpandExtension;

import java.util.List;

/**
 * Created by liangbo.zhou on 18-5-10.
 */
public interface RedisScaleExpandExtension extends ScaleExpandExtension{

    void addNode(List<RedisBackend.NodeInfo> nodes);

    void delNode(List<RedisBackend.NodeInfo> nodes);
}
