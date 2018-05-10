package com.zoom.node;

import com.zoom.rest.AsyncRESTCallback;
import com.zoom.rest.RESTFacade;
import com.zoom.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import java.util.List;

/**
 * Created by liangbo.zhou on 18-5-10.
 */
public class NodeBackend {

    @Autowired
    private RESTFacade restf;

    private static class AgentCmd {
    }

    private static class AgentRsp {
        public boolean success;
        public String error;
    }

    public static class NodeInfo{
        public String ip;
        public String port;
    }

    public static class AddNodeCmd extends AgentCmd{
        public void setNodes(List<NodeInfo> nodes) {
            this.nodes = nodes;
        }

        public List<NodeInfo> nodes;
    }

    public static class DelNodeCmd extends AgentCmd{
        public void setNodes(List<NodeInfo> nodes) {
            this.nodes = nodes;
        }

        public List<NodeInfo> nodes;
    }

    public static class CheckNodeCmd extends AgentCmd{}

    public static class CheckNodeRsp extends AgentRsp{}

    public static final String ADD_NODE_PATH = "/node/add";
    public static final String DEL_NODE_PATH = "/node/del";
    public static final String CHECK_CLUSTER_PATH = "/node/check";

    public void addRedisNode(AddNodeCmd cmd){
        restf.asyncJsonPost(ADD_NODE_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }

    public void delRedisNode(DelNodeCmd cmd){
        restf.asyncJsonPost(DEL_NODE_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }

    public void checkRedisCluster(CheckNodeCmd cmd){
        restf.asyncJsonPost(CHECK_CLUSTER_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }

}
