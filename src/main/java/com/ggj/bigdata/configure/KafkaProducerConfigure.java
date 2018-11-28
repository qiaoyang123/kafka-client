package com.ggj.bigdata.configure;

import com.ggj.bigdata.configure.base.KafkaPropConfigurer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * @author: <a href="mailto:qiaoy@gegejia.com">qy</a>
 * @version: 1.0 2018/11/7 15:57
 * @since 1.0
 */
public class KafkaProducerConfigure {

    public static Properties setProp() {

        // 加载 kafka.properties
        Properties kafkaProperties = KafkaPropConfigurer.getKafkaProperties();

        Properties props = new Properties();

        // 设置接入点，请通过控制台获取对应 Topic 的接入点
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getProperty("bootstrap.servers"));

        // Kafka 消息的序列化方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // 请求的最长等待时间
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 30 * 1000);

        return props;
    }
}
