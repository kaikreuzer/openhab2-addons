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
import org.eclipse.smarthome.binding.bluetooth.BluetoothBindingConstants;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link Bluetooth.GattBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Kai Kreuzer - Initial contribution
 */
@NonNullByDefault
public class BluetoothGattBindingConstants {

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_XIAOMI_TEMPHUMID = new ThingTypeUID(
            BluetoothBindingConstants.BINDING_ID, "MJ_HT_V1");

    // List of all Channel ids
    public static final String CHANNEL_1 = "channel1";
}
