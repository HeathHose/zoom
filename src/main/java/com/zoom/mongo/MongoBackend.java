package com.zoom.mongo;

import com.zoom.rest.AsyncRESTCallback;
import com.zoom.rest.RESTFacade;
import com.zoom.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import java.util.List;

/**
 * Created by liangbo.zhou on 18-4-8.
 */
public class MongoBackend {

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

    public static class ShardNodeInfo extends NodeInfo{
        public String shardDataPath;
        public String shardLogPath;
    }

    public static class MongosNodeInfo extends NodeInfo{
        public String mongosLogPath = MongoConstant.MONGOS_LOG_PATH;
    }

    public static class ConfigServerInfo extends NodeInfo{
        public String configServerDataPath = MongoConstant.CONFIG_SERVER_DATA_PATH;
        public String configServerLogPath = MongoConstant.CONFIG_SERVER_LOG_PATH;
    }


    public static final String INIT_MONGO_CLUSTER_PATH = "/mongo/init";
    public static final String ADD_SHARD_PATH = "/mongo/add";
    public static final String DEL_SHARD_PATH = "/mongo/del";
    public static final String CHECK_MONGO_CLUSTER_PATH = "/mongo/check";

    public static class InitMongoClusterCmd extends AgentCmd{
        public List<ShardNodeInfo> shards;
        public List<MongosNodeInfo> mongos;
        public List<ConfigServerInfo> configServers;
        public String company;
        public String task;

        public InitMongoClusterCmd(List<ShardNodeInfo> shards, List<MongosNodeInfo> mongos,
                                   List<ConfigServerInfo> configServers, String company, String task) {
            this.shards = shards;
            this.mongos = mongos;
            this.configServers = configServers;
            this.company = company;
            this.task = task;
        }
    }

    public static class InitMongoClusterRsp extends AgentRsp{}

    public static class AddShardSetCmd extends  AgentCmd{
        public void setShards(List<ShardNodeInfo> shards) {
            this.shards = shards;
        }

        public List<ShardNodeInfo> shards;
    }

    public static class AddShardSetRsp extends AgentRsp{}

    public static class DelShardSetCmd extends  AgentCmd{
        public void setShards(List<ShardNodeInfo> shards) {
            this.shards = shards;
        }

        public List<ShardNodeInfo> shards;
    }

    public static class DelShardSetRsp extends AgentRsp{}

    public static class CheckMongoClusterCmd extends AgentCmd{}

    public static class CheckClusterRsp extends AgentRsp{}

    public void initMongoCluster(InitMongoClusterCmd cmd){
        restf.asyncJsonPost(INIT_MONGO_CLUSTER_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }

    public void addShardSet(AddShardSetCmd cmd){
        restf.asyncJsonPost(ADD_SHARD_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }

    public void delShardSet(DelShardSetCmd cmd){
        restf.asyncJsonPost(DEL_SHARD_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }

    public void checkMongoCluster(CheckMongoClusterCmd cmd){
        restf.asyncJsonPost(CHECK_MONGO_CLUSTER_PATH, cmd, new AsyncRESTCallback() {
            @Override
            public void fail(ErrorCode err) {

            }

            @Override
            public void success(HttpEntity<String> responseEntity) {

            }
        });
    }
}
