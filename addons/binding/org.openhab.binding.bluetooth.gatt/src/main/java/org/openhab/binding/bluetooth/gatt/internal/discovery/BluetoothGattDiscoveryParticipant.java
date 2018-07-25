package org.openhab.binding.bluetooth.gatt.internal.discovery;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.binding.bluetooth.BluetoothBindingConstants;
import org.eclipse.smarthome.binding.bluetooth.BluetoothDevice;
import org.eclipse.smarthome.binding.bluetooth.discovery.BluetoothDiscoveryParticipant;
import org.eclipse.smarthome.config.discovery.DiscoveryResult;
import org.eclipse.smarthome.config.discovery.DiscoveryResultBuilder;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.openhab.binding.bluetooth.gatt.internal.BluetoothGattBindingConstants;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true)
public class BluetoothGattDiscoveryParticipant implements BluetoothDiscoveryParticipant {

    @Override
    public @NonNull Set<@NonNull ThingTypeUID> getSupportedThingTypeUIDs() {
        return Collections.singleton(BluetoothGattBindingConstants.THING_TYPE_XIAOMI_TEMPHUMID);
    }

    @Override
    public @Nullable ThingUID getThingUID(@NonNull BluetoothDevice device) {
        String name = device.getName();
        if (name != null) {
            if (name.equals(BluetoothGattBindingConstants.THING_TYPE_XIAOMI_TEMPHUMID.getId())) {
                return new ThingUID(BluetoothGattBindingConstants.THING_TYPE_XIAOMI_TEMPHUMID,
                        device.getAdapter().getUID(), device.getAddress().toString().toLowerCase().replace(":", ""));
            }
        }
        return null;
    }

    @Override
    public DiscoveryResult createResult(@NonNull BluetoothDevice device) {
        ThingUID thingUID = getThingUID(device);

        if (thingUID != null) {
            String label = "Xiaomi Temperature and Humidity Sensor";

            Map<String, Object> properties = new HashMap<>();
            properties.put(BluetoothBindingConstants.CONFIGURATION_ADDRESS, device.getAddress().toString());
            properties.put(Thing.PROPERTY_VENDOR, "Xiaomi");
            Integer txPower = device.getTxPower();
            if (txPower != null) {
                properties.put(BluetoothBindingConstants.PROPERTY_TXPOWER, Integer.toString(txPower));
            }

            // Create the discovery result and add to the inbox
            return DiscoveryResultBuilder.create(thingUID).withProperties(properties)
                    .withRepresentationProperty(BluetoothBindingConstants.CONFIGURATION_ADDRESS)
                    .withBridge(device.getAdapter().getUID()).withLabel(label).build();
        } else {
            return null;
        }
    }

}