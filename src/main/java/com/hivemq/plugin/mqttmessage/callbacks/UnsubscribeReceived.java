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

import com.hivemq.spi.callback.events.OnUnsubscribeCallback;
import com.hivemq.spi.message.UNSUBSCRIBE;
import com.hivemq.spi.security.ClientData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Florian Limpöck
 */
public class UnsubscribeReceived implements OnUnsubscribeCallback {

    private static Logger log = LoggerFactory.getLogger(UnsubscribeReceived.class);

    @Override
    public void onUnsubscribe(UNSUBSCRIBE message, ClientData clientData) {
        log.info("Unsubscribe from client {} received: {}", clientData.getClientId(), buildSubscriptionString(message.getTopics()));

    }

    private String buildSubscriptionString(List<String> subscription) {
        StringBuilder sb = new StringBuilder();

        for (String s : subscription) {
            sb.append(s).append(',');
        }

        sb.deleteCharAt(sb.length() - 1); //delete last comma

        return sb.toString();
    }
}
