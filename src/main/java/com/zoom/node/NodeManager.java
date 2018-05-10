package com.zoom.node;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by liangbo.zhou on 18-5-9.
 */
public class NodeManager {
    @Autowired
    NodeBackend backend;

    public void addNode(List<NodeBackend.NodeInfo> nodes) {
        NodeBackend.AddNodeCmd cmd = new NodeBackend.AddNodeCmd();
        cmd.setNodes(nodes);
        backend.addRedisNode(cmd);
    }

    public void delNode(List<NodeBackend.NodeInfo> nodes) {
        NodeBackend.DelNodeCmd cmd = new NodeBackend.DelNodeCmd();
        cmd.setNodes(nodes);
        backend.delRedisNode(cmd);
    }

    //mysql 需要涉及数据保存内存和硬盘空间
    public void checkNode(String ip) {
        NodeBackend.CheckNodeCmd cmd = new NodeBackend.CheckNodeCmd();
        backend.checkRedisCluster(cmd);
    }
}
