package club.virgilin.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * TestMan
 *
 * @author virgilin
 * @date 2019/3/12
 */
public class TestMan {
    public static final String CHANNEL = "mychannel";
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 6379;

    private final static JedisPoolConfig POOL_CONFIG = new JedisPoolConfig();
    private final static JedisPool JEDIS_POOL = new JedisPool(POOL_CONFIG,HOST,PORT,0);

    public static void main(String[] args) {
        Jedis subscriberJedis = JEDIS_POOL.getResource();
        Jedis publisherJedis = JEDIS_POOL.getResource();
        Subscriber subscriber = new Subscriber();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Subscribing to mychannel,this thread will be block");
                subscriberJedis.subscribe(subscriber,CHANNEL);
                System.out.println("subscription ended");
            }
        }).start();
        new Publisher(publisherJedis, CHANNEL).startPublish();
        publisherJedis.close();

        subscriber.unsubscribe();
        subscriberJedis.close();
    }
}
