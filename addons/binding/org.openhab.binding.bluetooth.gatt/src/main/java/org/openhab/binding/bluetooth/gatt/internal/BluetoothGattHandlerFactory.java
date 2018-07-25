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

import static org.openhab.binding.bluetooth.gatt.internal.BluetoothGattBindingConstants.THING_TYPE_XIAOMI_TEMPHUMID;

import java.util.Collections;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.binding.bluetooth.BeaconBluetoothHandler;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link Bluetooth.GattHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Kai Kreuzer - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.bluetooth.gatt", service = ThingHandlerFactory.class)
public class BluetoothGattHandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections
            .singleton(THING_TYPE_XIAOMI_TEMPHUMID);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (THING_TYPE_XIAOMI_TEMPHUMID.equals(thingTypeUID)) {
            return new BeaconBluetoothHandler(thing);
        }

        return null;
    }
}
