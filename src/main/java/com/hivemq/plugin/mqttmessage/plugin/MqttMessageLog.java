/*
 * Copyright 2015 dc-square GmbH
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.hivemq.plugin.mqttmessage.plugin;

import com.hivemq.plugin.mqttmessage.config.MqttMessageLogConfig;
import com.hivemq.spi.PluginEntryPoint;
import com.hivemq.plugin.mqttmessage.callbacks.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Florian Limpöck
 * @author Max Marche
 */
public class MqttMessageLog extends PluginEntryPoint {

    private final MqttMessageLogConfig config;

    @Inject
    public MqttMessageLog(MqttMessageLogConfig config) {
        this.config = config;
    }

    @PostConstruct
    public void postConstruct() {

        if (config.isClientConnect()) {
            getCallbackRegistry().addCallback(new ClientConnect());
        }
        if (config.isSubscribeReceived()) {
            getCallbackRegistry().addCallback(new SubscribeReceived());
        }
        if (config.isUnsubscribeReceived()) {
            getCallbackRegistry().addCallback(new UnsubscribeReceived());
        }
        if (config.isClientDisconnect()) {
            getCallbackRegistry().addCallback(new ClientDisconnect());
        }
        if (config.isPublishReceived()) {
            getCallbackRegistry().addCallback(new PublishReceived());
        }
        if (config.isPublishSend()) {
            getCallbackRegistry().addCallback(new PublishSend());
        }
    }
}
