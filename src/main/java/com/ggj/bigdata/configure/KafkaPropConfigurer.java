package com.ggj.bigdata.configure;

import com.ggj.bigdata.producer.KafkaProducerClient;

import java.util.Properties;

/**
 * @author: <a href="mailto:qiaoy@gegejia.com">qy</a>
 * @version: 1.0 2018/11/7 15:43
 * @since 1.0
 */
public class KafkaPropConfigurer {
    private static Properties properties;

    private static String AUTH_LOGIN_CONFIG_KEY = "java.security.auth.login.config";

    public static void configureSasl() {
        // 如果用 -D 或者其它方式设置过，这里不再设置
        if (null == System.getProperty(AUTH_LOGIN_CONFIG_KEY)) {
            // 这个路径必须是一个文件系统可读的路径，不能被打包到 jar 中
            System.setProperty(AUTH_LOGIN_CONFIG_KEY, getKafkaProperties().getProperty(AUTH_LOGIN_CONFIG_KEY));
        }
    }
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
