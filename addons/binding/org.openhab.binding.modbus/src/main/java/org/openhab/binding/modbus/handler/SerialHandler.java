package org.openhab.binding.modbus.handler;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.modbus.ModbusBindingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.io.ModbusTransaction;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.util.SerialParameters;

/**
 * The {@link SerialHandler} class is responsible
 * for connection to Modbus device using
 * serial communications
 *
 * @author Dmitry Krasnov - Initial contribution
 */
public class SerialHandler extends BaseBridgeHandler implements BridgeConnector {

    public final static Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections
            .singleton(ModbusBindingConstants.THING_TYPE_SERIAL);
    private static final String PROP_PORTNAME = "port";
    private static final String PROP_BAUD = "baud";
    private static final String PROP_DATABITS = "databits";
    private static final String PROP_STOPBITS = "stopbits";
    private static final String PROP_PARITY = "parity";
    private static final String PROP_ENCODING = "encoding";
    private Logger logger = LoggerFactory.getLogger(SerialHandler.class);

    private SerialParameters parameters = new SerialParameters();
    private ModbusSerialTransaction transaction = null;

    private static SerialConnection connection = null;

    public SerialHandler(Bridge thing) {
        super(thing);
        parameters.setPortName(getConfig().get(PROP_PORTNAME).toString());
        parameters.setBaudRate(((BigDecimal) getConfig().get(PROP_BAUD)).intValue());
        parameters.setDatabits(((BigDecimal) getConfig().get(PROP_DATABITS)).intValue());
        parameters.setStopbits(getConfig().get(PROP_STOPBITS).toString());
        parameters.setParity(getConfig().get(PROP_PARITY).toString());
        parameters.setEncoding(getConfig().get(PROP_ENCODING).toString());
        transaction = new ModbusSerialTransaction();
    }

    @Override
    public boolean connect() {
        try {
            if (connection == null) {
                logger.debug("connection was null, going to create a new one");
                connection = new SerialConnection(parameters);
            }
            if (!connection.isOpen()) {
                connection.open();
            }
            transaction.setSerialConnection(connection);
        } catch (Exception e) {
            logger.error("ModbusSlave: Error connecting to master: {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void resetConnection() {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }

    @Override
    public ModbusTransaction getTransaction() {
        return transaction;
    }

    @Override
    public boolean isHeadless() {
        return true;
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
    }

}
