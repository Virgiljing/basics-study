package juc.example.mq;

import lombok.Data;

import java.util.Date;

/**
 * @author sss
 * @date 2019-02-20
 */
@Data
public class Message {
    private Long id;
    private String msg;
    private Date sendTime;
}
