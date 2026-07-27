package org.springblade.common.utils.topic;
public class MqttTopicMatcher {
    private final String topic;
    public MqttTopicMatcher(String topic) { this.topic = topic; }
    public boolean matches(String other) { return topic.equals(other); }
}
