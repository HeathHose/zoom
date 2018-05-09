package com.zoom.redis;

import com.zoom.rest.AsyncRESTCallback;
import com.zoom.rest.RESTFacade;
import com.zoom.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import java.util.List;

/**
 * Created by liangbo.zhou on 18-5-8.
 */
public class RedisBackend {

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

    public static final String INIT_REDIS_CLUSTER_PATH = "/redis/init";
    public static final String ADD_NODE_PATH = "/redis/add";
    public static final String DEL_NODE_PATH = "/redis/del";

    public static class InitRedisClusterCmd extends AgentCmd{
       public List<NodeInfo> nodes;
    }

    public static class AddRedisNodeCmd extends AgentCmd{
        public List<NodeInfo> nodes;
    }

    public static class DelRedisNodeCmd extends AgentCmd{
        public List<NodeInfo> nodes;
    }

    public void initMongoCluster(InitRedisClusterCmd cmd){
        restf.asyncJsonPost(INIT_REDIS_CLUSTER_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }

    public void addShardSet(AddRedisNodeCmd cmd){
        restf.asyncJsonPost(ADD_NODE_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }

    public void delShardSet(DelRedisNodeCmd cmd){
        restf.asyncJsonPost(DEL_NODE_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }
}
