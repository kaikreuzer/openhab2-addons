/**
 * Copyright (c) 2014,2019 Contributors to the Eclipse Foundation
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
package org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.command.gap;

import org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.BlueGigaResponse;
import org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.enumeration.BgApiResponse;

/**
 * Class to implement the BlueGiga command <b>discover</b>.
 * <p>
 * This command starts the GAP discovery procedure to scan for advertising devices i.e. to
 * perform a device discovery. Scanning parameters can be configured with the Set Scan
 * Parameters command before issuing this command. To cancel on an ongoing discovery process
 * use the End Procedure command.
 * <p>
 * This class provides methods for processing BlueGiga API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class BlueGigaDiscoverResponse extends BlueGigaResponse {
    public static int COMMAND_CLASS = 0x06;
    public static int COMMAND_METHOD = 0x02;

    /**
     * 0: Scan procedure was successfully started Non-zero: An error occurred.
     * <p>
     * BlueGiga API type is <i>BgApiResponse</i> - Java type is {@link BgApiResponse}
     */
    private BgApiResponse result;

    /**
     * Response constructor
     */
    public BlueGigaDiscoverResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        event = (inputBuffer[0] & 0x80) != 0;

        // Deserialize the fields
        result = deserializeBgApiResponse();
    }

    /**
     * 0: Scan procedure was successfully started Non-zero: An error occurred.
     * <p>
     * BlueGiga API type is <i>BgApiResponse</i> - Java type is {@link BgApiResponse}
     *
     * @return the current result as {@link BgApiResponse}
     */
    public BgApiResponse getResult() {
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BlueGigaDiscoverResponse [result=");
        builder.append(result);
        builder.append(']');
        return builder.toString();
    }
}
