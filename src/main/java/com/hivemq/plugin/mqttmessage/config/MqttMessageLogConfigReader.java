package com.hivemq.plugin.mqttmessage.config;

import com.hivemq.spi.config.SystemInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class MqttMessageLogConfigReader {

    private static final Logger log = LoggerFactory.getLogger(MqttMessageLogConfigReader.class);

    private final Properties properties = new Properties();
    private final SystemInformation systemInformation;

    private static final String PROPERTIES_FILE_NAME = "mqttMessageLog.properties";

    @Inject
    public MqttMessageLogConfigReader(SystemInformation systemInformation) {
        this.systemInformation = systemInformation;
    }

    @PostConstruct
    public void postConstruct() {
        final File configFolder = systemInformation.getConfigFolder();
        final File pluginFile = new File(configFolder, PROPERTIES_FILE_NAME);

        log.debug("MQTT Message Log Plugin: Will try to read config properties from {}", PROPERTIES_FILE_NAME);

        if (!pluginFile.canRead()) {
            log.info("MQTT Message Log Plugin: No properties file {} available", pluginFile.getAbsolutePath());
            return;
        }

        try (InputStream is = new FileInputStream(pluginFile)) {
            properties.load(is);
        } catch (Exception e) {
            log.warn("MQTT Message Log Plugin: Could not load properties, reason {}", e.getMessage());
        }
    }

    Properties getProperties() {
        return properties;
    }
}
