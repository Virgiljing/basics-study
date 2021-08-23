package club.virgilin.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * PPSubClient
 *
 * @author virgilin
 * @date 2019/3/12
 */
public class PPSubClient {
    private Jedis jedis;
    private JedisPubSub listener;
    private String CONSTANT_CLIENTSET="clientSet";
    public PPSubClient(String host,int port,String clientId){
        jedis = new Jedis(host,port);
        listener = new PPrintListener(clientId,new Jedis(host,port));
        jedis.sadd(CONSTANT_CLIENTSET, clientId);
    }
    public void sub(String channel){
        jedis.subscribe(listener, channel);
    }
    public void unsubscribe(String channel){
        listener.unsubscribe(channel);
    }

}
