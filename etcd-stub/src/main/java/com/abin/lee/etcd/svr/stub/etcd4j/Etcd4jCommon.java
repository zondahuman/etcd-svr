package com.abin.lee.etcd.svr.stub.etcd4j;

import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;
import com.coreos.jetcd.kv.GetResponse;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.requests.EtcdKeyGetRequest;
import mousio.etcd4j.transport.EtcdNettyClient;
import mousio.etcd4j.transport.EtcdNettyConfig;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by abin on 2018/1/22 19:13.
 * etcd-svr
 * com.abin.lee.etcd.svr.stub.etcd4j
 * https://github.com/jurmous/etcd4j.git
 */
public class Etcd4jCommon {


    private static EtcdClient etcdClient = null;
//    private static final String endpoints = "http://172.16.2.132:2379";
//    private static final String endpoints = "http://172.16.2.132:2380";
    private static final String endpoints = "http://127.0.0.1:2379";

    static {
        EtcdNettyConfig config = new EtcdNettyConfig();
        EtcdNettyClient nettyClient = new EtcdNettyClient(config, URI.create(endpoints));
        config.setMaxFrameSize(1024 * 1024); // Desired max size
        etcdClient = new EtcdClient(nettyClient);
    }

    private Etcd4jCommon() {
    }

    private static class Etcd4jUtilHolder {
        public static Etcd4jCommon instance = new Etcd4jCommon();
    }

    public static Etcd4jCommon getInstance() {
        return Etcd4jUtilHolder.instance;
    }

    public void put(String path, String key, String value) throws ExecutionException, InterruptedException {
        etcdClient.put(path +"/"+key, value);
    }

    public Map<String, String> get(String key) throws ExecutionException, InterruptedException {
        ByteSequence keySequence = ByteSequence.fromString(key);
        EtcdKeyGetRequest etcdKeyGetRequest = etcdClient.get(key);
        Map<String, String> requestParams = etcdKeyGetRequest.getRequestParams();
        if (MapUtils.isNotEmpty(requestParams))
            return requestParams;
        else
            return null;
    }

}
