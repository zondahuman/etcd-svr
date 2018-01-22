package com.abin.lee.etcd.svr.stub.etcd4j;

import com.abin.lee.etcd.svr.common.JsonUtil;
import com.abin.lee.etcd.svr.stub.jetcd.util.EtcdCommon;
import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;
import com.coreos.jetcd.kv.DeleteResponse;
import com.coreos.jetcd.kv.GetResponse;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by abin on 2018/1/22 16:04.
 * etcd-svr
 * com.abin.lee.etcd.svr.stub.test
 */
public class Etcd4jTest {

    public static void main(String[] args) {

    }

    @Test
    public void testPut1() throws ExecutionException, InterruptedException {
// create client
//        Client client = Client.builder().endpoints("http://172.16.2.133:2379").build();
        Client client = Client.builder().endpoints("http://127.0.0.1:2379").build();
        KV kvClient = client.getKVClient();

        ByteSequence key = ByteSequence.fromString("/abin/test_key");
        ByteSequence value = ByteSequence.fromString("test_value");

// put the key-value
        kvClient.put(key, value).get();
// get the CompletableFuture
        CompletableFuture<GetResponse> getFuture = kvClient.get(key);
// get the value from CompletableFuture
        GetResponse response = getFuture.get();
        System.out.println("response=----------------- : "+response);
// delete the key
        DeleteResponse deleteRangeResponse = kvClient.delete(key).get();

    }

    @Test
    public void testPut() throws ExecutionException, InterruptedException {
        String key = "lee";
        String value = "lee" + (int)(Math.random()*100);
        Etcd4jCommon.getInstance().put("/lee",key, value);

        Map<String, String> result = Etcd4jCommon.getInstance().get(key);
        System.out.println("result=" + JsonUtil.toJson(result));
    }
}
