package com.ggj.bigdata.configure;

import com.ggj.bigdata.configure.base.KafkaPropConfigurer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * @author: <a href="mailto:qiaoy@gegejia.com">qy</a>
 * @version: 1.0 2018/11/14 10:10
 * @since 1.0
 */
public class KafKaConsumerConfigure {

    public static Properties getProp(){
        //加载kafka.properties
        Properties kafkaProperties =  KafkaPropConfigurer.getKafkaProperties();
        Properties props = new Properties();
        //设置接入点，请通过控制台获取对应Topic的接入点
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getProperty("bootstrap.servers"));
        //两次poll之间的最大允许间隔
        //请不要改得太大，服务器会掐掉空闲连接，不要超过30000
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 25000);
        //每次poll的最大数量
        //注意该值不要改得太大，如果poll太多数据，而不能在下次poll之前消费完，则会触发一次负载均衡，产生卡顿
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 30);
        //消息的反序列化方式
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //当前消费实例所属的消费组，请在控制台申请之后填写
        //属于同一个组的消费实例，会负载消费消息
        if(!StringUtils.isEmpty(kafkaProperties.getProperty("group.id"))){
            props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getProperty("group.id"));
        }

        return props;
    }
}
