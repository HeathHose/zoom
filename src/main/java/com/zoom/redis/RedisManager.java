package com.zoom.redis;

import com.zoom.core.ScaleExpandExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by liangbo.zhou on 18-5-8.
 */
public class RedisManager implements RedisExtension,RedisScaleExpandExtension{
    @Autowired
    public RedisBase base;
    @Autowired
    public RedisBackend backend;

    @Override
    public void addNode(List<RedisBackend.NodeInfo> nodes) {
        RedisBackend.AddRedisNodeCmd cmd = new RedisBackend.AddRedisNodeCmd();
        cmd.setNodes(nodes);
        backend.addRedisNode(cmd);
    }

    @Override
    public void delNode(List<RedisBackend.NodeInfo> nodes) {
        RedisBackend.DelRedisNodeCmd cmd = new RedisBackend.DelRedisNodeCmd();
        cmd.setNodes(nodes);
        backend.delRedisNode(cmd);
    }

    //mysql 需要涉及数据保存内存和硬盘空间
    @Override
    public Map<String,String> checkCluster() {
        RedisBackend.CheckRedisClusterCmd cmd = new RedisBackend.CheckRedisClusterCmd();
        backend.checkRedisCluster(cmd);
    }


    @Override
    public void initRedisCluster(List<RedisBackend.NodeInfo> nodes) {
        RedisBackend.InitRedisClusterCmd cmd = new RedisBackend.InitRedisClusterCmd();
        cmd.setNodes(nodes);
        backend.initRedisCluster(cmd);
    }
}
