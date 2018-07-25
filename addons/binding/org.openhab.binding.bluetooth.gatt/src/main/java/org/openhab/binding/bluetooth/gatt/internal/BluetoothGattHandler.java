/**
 * Copyright (c) 2014,2018 by the respective copyright holders.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.bluetooth.gatt.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.binding.bluetooth.ConnectedBluetoothHandler;
import org.eclipse.smarthome.core.thing.Thing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link Bluetooth.GattHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Kai Kreuzer - Initial contribution
 */
@NonNullByDefault
public class BluetoothGattHandler extends ConnectedBluetoothHandler {

    private final Logger logger = LoggerFactory.getLogger(BluetoothGattHandler.class);

    public BluetoothGattHandler(Thing thing) {
        super(thing);
    }

}
