/*
 * Copyright 2013 dc-square GmbH
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

package com.hivemq.plugin.mqttmessage.callbacks;

import com.hivemq.spi.callback.CallbackPriority;
import com.hivemq.spi.callback.events.OnSubscribeCallback;
import com.hivemq.spi.callback.exception.InvalidSubscriptionException;
import com.hivemq.spi.message.SUBSCRIBE;
import com.hivemq.spi.message.Topic;
import com.hivemq.spi.security.ClientData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Florian Limpöck
 */
public class SubscribeReceived implements OnSubscribeCallback {

    private static Logger log = LoggerFactory.getLogger(SubscribeReceived.class);

    @Override
    public void onSubscribe(final SUBSCRIBE message, final ClientData clientData) throws InvalidSubscriptionException {
        log.info("Subscribe from client {} received: {}", clientData.getClientId(), buildSubscriptionString(message.getTopics()));
    }

    @Override
    public int priority() {
        return CallbackPriority.VERY_LOW;
    }

    private String buildSubscriptionString(List<Topic> subscription) {
        StringBuilder sb = new StringBuilder();

        for (Topic s : subscription) {
            sb.append(s.getTopic()).
                    append(" QoS: ").
                    append(s.getQoS().getQosNumber()).
                    append(',');
        }

        sb.deleteCharAt(sb.length() - 1); //delete last comma

        return sb.toString();
    }
}
