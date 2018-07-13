package com.hivemq.plugin.mqttmessage.config;

import com.hivemq.spi.config.SystemInformation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MqttMessageLogConfigReaderTest {

    SystemInformation sysInfo;

    @Before
    public void setup() {
        sysInfo = mock(SystemInformation.class);
    }

    @Test
    public void emptyPropertiesWhenNopropertyFileInConfigFolder() {
        final Properties properties = getProperties("src/test/resources/empty-conf");

        assertTrue(properties != null && properties.isEmpty());
    }

    @Test
    public void nonEmptyPropertiesWhenpropertyFileInConfigFolder() {
        final Properties properties = getProperties("src/test/resources/test-conf");

        assertThat(properties.size(), is(2));
        assertThat(properties.stringPropertyNames(), containsInAnyOrder("publish-send", "publish-received"));
    }

    private Properties getProperties(final String confPath) {
        when(sysInfo.getConfigFolder()).thenReturn(new File(confPath));
        final MqttMessageLogConfigReader mqttMessageLogConfigReader = new MqttMessageLogConfigReader(sysInfo);
        mqttMessageLogConfigReader.postConstruct();
        return mqttMessageLogConfigReader.getProperties();
    }

}