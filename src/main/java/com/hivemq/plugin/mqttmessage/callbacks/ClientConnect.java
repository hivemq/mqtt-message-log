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

package com.hivemq.plugin.mqttmessage.callbacks;

import com.hivemq.spi.callback.CallbackPriority;
import com.hivemq.spi.callback.events.OnConnectCallback;
import com.hivemq.spi.callback.exception.RefusedConnectionException;
import com.hivemq.spi.message.CONNECT;
import com.hivemq.spi.security.ClientData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Florian Limpöck
 */
public class ClientConnect implements OnConnectCallback {

    private static final Logger log = LoggerFactory.getLogger(ClientConnect.class);


    @Override
    public void onConnect(final CONNECT connect, final ClientData clientData) throws RefusedConnectionException {
        log.info("Client {} connected", clientData.getClientId());

        if (connect.isWill()) {
            log.info("Client {} set Last Will and Testament. Topic: \"{}\", Payload: \"{}\", QoS: {}, retain: {}",
                    clientData.getClientId(), connect.getWillTopic(), connect.getWillMessage(), connect.getWillQos().getQosNumber(), connect.isWillRetain());
        }
    }

    @Override
    public int priority() {
        return CallbackPriority.LOW;
    }
}
