package com.zoom.core;

/**
 * Created by liangbo.zhou on 18-4-9.
 */
public interface ScaleExpandExtension {

    void addNode(String ip, String port);
    void delNode(String ip, String port);
    void checkCluster();

}
