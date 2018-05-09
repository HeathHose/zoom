package com.zoom.redis;

import com.zoom.core.ScaleExpandExtension;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liangbo.zhou on 18-5-8.
 */
public class RedisManager implements RedisExtension,ScaleExpandExtension{
    @Autowired
    public RedisBase base;
    @Autowired
    public RedisBackend backend;

    @Override
    public void addNode(String ip, String port) {

    }

    @Override
    public void delNode(String ip, String port) {

    }

    @Override
    public void checkCluster() {

    }
}
