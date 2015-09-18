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

import com.hivemq.spi.callback.events.OnDisconnectCallback;
import com.hivemq.spi.security.ClientData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Florian Limpöck
 */
public class ClientDisconnect implements OnDisconnectCallback {

    private static final Logger log = LoggerFactory.getLogger(ClientDisconnect.class);

    @Override
    public void onDisconnect(final ClientData clientData, final boolean abruptDisconnect) {
        if (!abruptDisconnect) {
            log.info("Client {} sent DISCONNECT message", clientData.getClientId());
        } else {
            log.info("Client {} disconnected", clientData.getClientId());
        }
    }
}
