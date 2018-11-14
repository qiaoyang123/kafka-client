package com.ggj.bigdata.configure;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;

import java.util.Properties;

/**
 * @author: <a href="mailto:qiaoy@gegejia.com">qy</a>
 * @version: 1.0 2018/11/7 15:57
 * @since 1.0
 */
public class KafkaProducerConfigure {

    public static Properties setProp() {
        // 设置 sasl 文件的路径
        KafkaPropConfigurer.configureSasl();

        // 加载 kafka.properties
        Properties kafkaProperties = KafkaPropConfigurer.getKafkaProperties();

        Properties props = new Properties();

        // 设置接入点，请通过控制台获取对应 Topic 的接入点
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getProperty("bootstrap.servers"));

        // 设置 SSL 根证书的路径，请记得将 XXX 修改为自己的路径
        //与 sasl 路径类似，该文件也不能被打包到 jar 中
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaProperties.getProperty("ssl.truststore.location"));

        //根证书store的密码，保持不变
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "KafkaOnsClient");

        //接入协议，目前支持使用 SASL_SSL 协议接入
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");

        // SASL 鉴权方式，保持不变
        props.put(SaslConfigs.SASL_MECHANISM, "ONS");

        // Kafka 消息的序列化方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // 请求的最长等待时间
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 30 * 1000);

        return props;
    }
}
