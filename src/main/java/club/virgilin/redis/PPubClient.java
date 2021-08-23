package club.virgilin.redis;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Publisher
 *
 * @author virgilin
 * @date 2019/3/12
 */
public class PPubClient {
    private Jedis jedis;
    private String CONSTANT_CLIENTSET = "clientSet";
    public PPubClient(String host,int port){
        jedis = new Jedis(host,port);
    }

    private void put(String message){
        Set<String> subClients = jedis.smembers(CONSTANT_CLIENTSET);
        for (String clientKey : subClients) {
            jedis.rpush(clientKey,message);
        }
    }

    public void pub(String channel,String message){
        Long txid = jedis.incr("MAXID");
        String content = txid + "/" + message;
        this.put(content);
        jedis.publish(channel,message);
    }

    public void close(String channel){
        jedis.publish(channel,"quit");
        jedis.del(channel);
    }
}
