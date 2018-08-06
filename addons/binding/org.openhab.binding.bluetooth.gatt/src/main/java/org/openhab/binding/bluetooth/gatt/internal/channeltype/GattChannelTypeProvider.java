package org.openhab.binding.bluetooth.gatt.internal.channeltype;

import java.util.Collection;
import java.util.Locale;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.type.ChannelGroupType;
import org.eclipse.smarthome.core.thing.type.ChannelGroupTypeUID;
import org.eclipse.smarthome.core.thing.type.ChannelType;
import org.eclipse.smarthome.core.thing.type.ChannelTypeProvider;
import org.eclipse.smarthome.core.thing.type.ChannelTypeUID;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sputnikdev.bluetooth.gattparser.BluetoothGattParserFactory;
import org.sputnikdev.bluetooth.gattparser.spec.BluetoothGattSpecificationReader;
import org.sputnikdev.bluetooth.gattparser.spec.Characteristic;

@Component
public class GattChannelTypeProvider implements ChannelTypeProvider {

    private final Logger logger = LoggerFactory.getLogger(GattChannelTypeProvider.class);

    @Activate
    protected void activate() {
        BluetoothGattSpecificationReader specReader = BluetoothGattParserFactory.getSpecificationReader();
        for (Characteristic c : specReader.getCharacteristics()) {
            logger.debug("{}", c);
        }
    }

    @Override
    public @Nullable Collection<@NonNull ChannelType> getChannelTypes(@Nullable Locale locale) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @Nullable ChannelType getChannelType(@NonNull ChannelTypeUID channelTypeUID, @Nullable Locale locale) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @Nullable ChannelGroupType getChannelGroupType(@NonNull ChannelGroupTypeUID channelGroupTypeUID,
            @Nullable Locale locale) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public @Nullable Collection<@NonNull ChannelGroupType> getChannelGroupTypes(@Nullable Locale locale) {
        // TODO Auto-generated method stub
        return null;
    }

}
