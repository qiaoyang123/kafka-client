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
    LOG_HQBS_GENERAL("log_hqbs_general");


    private String topic;

    TopicEnum(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}