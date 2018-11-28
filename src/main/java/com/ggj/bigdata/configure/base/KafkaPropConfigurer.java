package com.ggj.bigdata.configure.base;

import com.ggj.bigdata.producer.KafkaProducerClient;

import java.util.Properties;

/**
 * @author: <a href="mailto:qiaoy@gegejia.com">qy</a>
 * @version: 1.0 2018/11/7 15:43
 * @since 1.0
 */
public class KafkaPropConfigurer {
    private static Properties properties;

    public synchronized static Properties getKafkaProperties() {
        if (null != properties) {
            return properties;
        }
        // 获取配置文件 kafka.properties 的内容
        Properties kafkaProperties = new Properties();
        try {
            kafkaProperties.load(KafkaProducerClient.class.getClassLoader().getResourceAsStream("kafka.properties"));
        } catch (Exception e) {
            // 没加载到文件，程序要考虑退出e
            throw new RuntimeException("找不到配置文件kafka.properties");
        }
        properties = kafkaProperties;
        return kafkaProperties;
    }
}
