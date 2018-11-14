package com.ggj.bigdata.producer;

import com.ggj.bigdata.configure.KafKaConsumerConfigure;
import com.ggj.bigdata.configure.KafkaProducerConfigure;
import com.ggj.bigdata.configure.KafkaPropConfigurer;
import com.ggj.bigdata.enums.TopicEnum;
import com.ggj.platform.gsf.result.CodeMsg;
import com.ggj.platform.gsf.result.PlainResult;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author: <a href="mailto:qiaoy@gegejia.com">qy</a>
 * @version: 1.0 2018/11/7 15:47
 * @since 1.0
 */
public class KafkaProducerClient {

    private static KafkaProducer<String, String> producer = null;

    public static PlainResult send(TopicEnum topic, String message){

        PlainResult<String> result = new PlainResult<String>();

        String topic1 = topic.getTopic();
        if(StringUtils.isEmpty(topic1)){
             result.failure(new CodeMsg(1L,"没有此topic"));
             return result;
        }


        // 构造 Producer 对象，注意，该对象是线程安全的，一般来说，一个进程内一个 Producer 对象即可；
        // 如果想提高性能，可以多构造几个对象，但不要太多，最好不要超过 5 个
        Properties props = KafkaProducerConfigure.setProp();
        if(producer == null){
            producer = new KafkaProducer<String, String>(props);
        }

        // 构造一个 Kafka 消息
        // 消息的内容
        ProducerRecord<String, String> kafkaMessage = new ProducerRecord<String, String>(topic1, message);
        try {
            // 发送消息，并获得一个 Future 对象
            Future<RecordMetadata> metadataFuture = producer.send(kafkaMessage);
            // 同步获得 Future 对象的结果
            RecordMetadata recordMetadata = metadataFuture.get();
            result.success(recordMetadata.toString());
            return result;
        } catch (Exception e) {
            //todo 重试发送
            result.failure(new CodeMsg(1L,e.getMessage()));
            return result;
        }
    }

    public static KafkaConsumer<String, String> getConsumer(TopicEnum topic){


        //校验topic
        String topic1 = topic.getTopic();
        if(StringUtils.isEmpty(topic1)) {
           return null;
        }

        Properties properties = KafKaConsumerConfigure.setProp();
        //构造消息对象，也即生成一个消费实例
        KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(properties);
        //设置消费组订阅的Topic，可以订阅多个
        //如果GROUP_ID_CONFIG是一样，则订阅的Topic也建议设置成一样
        List<String> subscribedTopics =  new ArrayList<String>();
        //如果需要订阅多个Topic，则在这里add进去即可
        //每个Topic需要先在控制台进行创建
        subscribedTopics.add(topic1);
        consumer.subscribe(subscribedTopics);

        return consumer;
    }

    public static void main(String[] args) throws Exception {
        send(TopicEnum.LOG_HQBS_GENERAL,"hello world");
    }
}
