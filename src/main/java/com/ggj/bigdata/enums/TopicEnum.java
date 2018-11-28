package com.ggj.bigdata.enums;

/**
 * kafka topic 枚举
 *
 * @author <a href="mailto:qiaoy@gegejia.com">qy</a>
 * @version 1.0 2018/4/12
 * @since 1.0
 */
public enum TopicEnum {

    /**
     * 环球捕手app通用日志
     */
    LOG_HQBS_GENERAL("log_hqbs_general","环球捕手app通用日志");


    /**
     * topic命名
     */
    private String topic;

    /**
     * topic注释
     */
    private String message;

    TopicEnum(String topic,String message) {
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }
}