package club.virgilin.redis;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Publisher
 *
 * @author virgilin
 * @date 2019/3/12
 */
public class Publisher {
    private Jedis publisherJedis;
    private String channel;
    public Publisher(Jedis publisherJedis,String channel){
        this.publisherJedis = publisherJedis;
        this.channel = channel;
    }

    public void startPublish() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("请输入message：");
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!"quite".equals(line)){
                publisherJedis.publish(channel,line);
            } else {
                break;
            }
        }
    }
}
