package com.hivemq.plugin.mqttmessage.config;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Properties;

import static com.hivemq.plugin.mqttmessage.config.MqttMessageLogConfig.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MqttMessageLogConfigTest {

    private static final String FALSE = "false";

    MqttMessageLogConfig emptyConfig;
    MqttMessageLogConfig allFalseConfig;
    MqttMessageLogConfig allTrueConfig;
    MqttMessageLogConfig mixedConfig;

    @Before
    public void setup() {
        final MqttMessageLogConfigReader emptyReader = mock(MqttMessageLogConfigReader.class);
        when(emptyReader.getProperties()).thenReturn(new Properties());
        emptyConfig = new MqttMessageLogConfig(emptyReader);

        final MqttMessageLogConfigReader allFalseReader = mock(MqttMessageLogConfigReader.class);
        final Properties allFalseProperties = new Properties();
        allFalseProperties.setProperty(CLIENT_CONNECT, FALSE);
        allFalseProperties.setProperty(CLIENT_DISCONNECT, FALSE);
        allFalseProperties.setProperty(PUBLISH_RECEIVED, FALSE);
        allFalseProperties.setProperty(PUBLISH_SEND, FALSE);
        allFalseProperties.setProperty(SUBSCRIBE_RECEIVED, FALSE);
        allFalseProperties.setProperty(UNSUBSCRIBE_RECEIVED, FALSE);
        when(allFalseReader.getProperties()).thenReturn(allFalseProperties);
        allFalseConfig = new MqttMessageLogConfig((allFalseReader));

        final MqttMessageLogConfigReader allTrueReader = mock(MqttMessageLogConfigReader.class);
        final Properties allTrueProperties = new Properties();
        allTrueProperties.setProperty(CLIENT_CONNECT, TRUE);
        allTrueProperties.setProperty(CLIENT_DISCONNECT, TRUE);
        allTrueProperties.setProperty(PUBLISH_RECEIVED, TRUE);
        allTrueProperties.setProperty(PUBLISH_SEND, TRUE);
        allTrueProperties.setProperty(SUBSCRIBE_RECEIVED, TRUE);
        allTrueProperties.setProperty(UNSUBSCRIBE_RECEIVED, TRUE);
        when(allTrueReader.getProperties()).thenReturn(allTrueProperties);
        allTrueConfig = new MqttMessageLogConfig((allTrueReader));

        final MqttMessageLogConfigReader mixedReader = mock(MqttMessageLogConfigReader.class);
        final Properties mixedProperties = new Properties();
        mixedProperties.setProperty(CLIENT_CONNECT, TRUE);
        mixedProperties.setProperty(CLIENT_DISCONNECT, FALSE);
        mixedProperties.setProperty(PUBLISH_RECEIVED, FALSE);
        when(mixedReader.getProperties()).thenReturn(mixedProperties);
        mixedConfig = new MqttMessageLogConfig((mixedReader));
    }

    @Test
    public void isClientConnect() {
        assertTrue(emptyConfig.isClientConnect());
        assertFalse(allFalseConfig.isClientConnect());
        assertTrue(allTrueConfig.isClientConnect());
        assertTrue(mixedConfig.isClientConnect());
    }

    @Test
    public void isClientDisconnect() {
        assertTrue(emptyConfig.isClientDisconnect());
        assertFalse(allFalseConfig.isClientDisconnect());
        assertTrue(allTrueConfig.isClientDisconnect());
        assertFalse(mixedConfig.isClientDisconnect());
    }

    @Test
    public void isPublishReceived() {
        assertTrue(emptyConfig.isPublishReceived());
        assertFalse(allFalseConfig.isPublishReceived());
        assertTrue(allTrueConfig.isPublishReceived());
        assertFalse(mixedConfig.isPublishReceived());
    }

    @Test
    public void isPublishSend() {
        assertTrue(emptyConfig.isPublishSend());
        assertFalse(allFalseConfig.isPublishSend());
        assertTrue(allTrueConfig.isPublishSend());
        assertTrue(mixedConfig.isPublishSend());
    }

    @Test
    public void isSubscribeReceived() {
        assertTrue(emptyConfig.isSubscribeReceived());
        assertFalse(allFalseConfig.isSubscribeReceived());
        assertTrue(allTrueConfig.isSubscribeReceived());
        assertTrue(mixedConfig.isSubscribeReceived());
    }

    @Test
    public void isUnsubscribeReceived() {
        assertTrue(emptyConfig.isUnsubscribeReceived());
        assertFalse(allFalseConfig.isUnsubscribeReceived());
        assertTrue(allTrueConfig.isUnsubscribeReceived());
        assertTrue(mixedConfig.isUnsubscribeReceived());
    }
}