package com.abin.lee.etcd.svr.stub.jetcd.util;

import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;
import com.coreos.jetcd.kv.GetResponse;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by abin on 2018/1/22 15:49.
 * etcd-svr
 * com.abin.lee.etcd.svr.stub.util
 * https://github.com/coreos/jetcd.git
 */
public class EtcdCommon {

    private static KV kvClient = null;
    private static final String endpoints = "http://172.16.2.132:2379";
//    private static final String endpoints = "http://172.16.2.132:2380";

    static {
        Client client = Client.builder().endpoints(endpoints).build();
        kvClient = client.getKVClient();
    }

    private EtcdCommon() {
    }

    private static class EtcdUtilHolder {
        public static EtcdCommon instance = new EtcdCommon();
    }

    public static EtcdCommon getInstance() {
        return EtcdUtilHolder.instance;
    }

    public void put(String key, String value) throws ExecutionException, InterruptedException {
        ByteSequence keyParam = ByteSequence.fromString(key);
        ByteSequence valueParam = ByteSequence.fromString(value);
        kvClient.put(keyParam, valueParam).get();
    }

    public List<KeyValue> get(String key) throws ExecutionException, InterruptedException {
        ByteSequence keySequence = ByteSequence.fromString(key);
        CompletableFuture<GetResponse> getFeature = kvClient.get(keySequence);
        GetResponse response = getFeature.get();
        if (ObjectUtils.notEqual(null, response))
            return response.getKvs();
        else
            return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EtcdCommon.getInstance().get("");



    }

}
