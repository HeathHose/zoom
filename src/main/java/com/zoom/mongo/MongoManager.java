package com.zoom.mongo;

import com.zoom.core.ScaleExpandExtension;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liangbo.zhou on 18-5-8.
 */
public class MongoManager implements MongoExtension,ScaleExpandExtension{
    @Autowired
    public MongoBackend backend;
    @Autowired
    public MongoBase base;


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
    public void addNode(String ip, String port) {

    }

    @Override
    public void delNode(String ip, String port) {

    }

    @Override
    public void checkCluster() {

    }
}
