package com.hivemq.plugin.mqttmessage.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Properties;

public class MqttMessageLogConfig {

    private static final Logger log = LoggerFactory.getLogger(MqttMessageLogConfig.class);

    private static final String TRUE = "true";
    private static final String CLIENT_CONNECT = "client-connect";
    private static final String CLIENT_DISCONNECT = "client-disconnect";
    private static final String PUBLISH_RECEIVED = "publish-received";
    private static final String PUBLISH_SEND = "publish-send";
    private static final String SUBSCRIBE_RECEIVED = "subscribe-received";
    private static final String UNSUBSCRIBE_RECEIVED = "unsubscribe-received";

    private final MqttMessageLogConfigReader configReader;
    private Properties properties = null;

    @Inject
    public MqttMessageLogConfig(MqttMessageLogConfigReader configReader) {
        this.configReader = configReader;
    }

    public boolean isClientConnect() {
        return getForKey(CLIENT_CONNECT);
    }

    public boolean isClientDisconnect() {
        return getForKey(CLIENT_DISCONNECT);
    }

    public boolean isPublishReceived() {
        return getForKey(PUBLISH_RECEIVED);
    }

    public boolean isPublishSend() {
        return getForKey(PUBLISH_SEND);
    }

    public boolean isSubscribeReceived() {
        return getForKey(SUBSCRIBE_RECEIVED);
    }

    public boolean isUnsubscribeReceived() {
        return getForKey(UNSUBSCRIBE_RECEIVED);
    }

    private boolean getForKey(String key) {
        if (properties == null) {
            properties = configReader.getProperties();
            log.info("initialize properties to: {}", properties);
        }

        return properties.getProperty(key, TRUE).equals(TRUE);
    }
}
