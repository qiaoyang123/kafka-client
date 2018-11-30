package com.ggj.bigdata.comsumer;

import com.ggj.bigdata.configure.KafKaConsumerConfigure;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author: <a href="mailto:qiaoy@gegejia.com">qy</a>
 * @version: 1.0 2018/11/28 14:54
 * @since 1.0
 */
public class KafkaConsumerClient {

    public static KafkaConsumer getConsumer(String[] topics, String groupId){
        Properties prop = KafKaConsumerConfigure.getProp(groupId);

        //构造消息对象，也即生成一个消费实例
        KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(prop);

        //设置消费组订阅的Topic，可以订阅多个
        //如果GROUP_ID_CONFIG是一样，则订阅的Topic也建议设置成一样
        List<String> subscribedTopics =  new ArrayList<String>();
        //如果需要订阅多个Topic，则在这里add进去即可
        //每个Topic需要先在控制台进行创建
        for (String topic : topics) {
            subscribedTopics.add(topic);
        }

        consumer.subscribe(subscribedTopics);

        return consumer;
    }
}
