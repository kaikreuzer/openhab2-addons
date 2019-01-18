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
package org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the BlueGiga Enumeration <b>GapConnectableMode</b>.
 * <p>
 * GAP connectable modes
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum GapConnectableMode {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * [0] Not connectable
     */
    GAP_NON_CONNECTABLE(0x0000),

    /**
     * [1] Directed Connectable
     */
    GAP_DIRECTED_CONNECTABLE(0x0001),

    /**
     * [2] Undirected connectable
     */
    GAP_UNDIRECTED_CONNECTABLE(0x0002),

    /**
     * [3] Same as non-connectable, but also supports ADV_SCAN_IND packets. Device accepts scan
     * requests (active scanning) but is not connectable.
     */
    GAP_SCANNABLE_NON_CONNECTABLE(0x0003);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, GapConnectableMode> codeMapping;

    private int key;

    private GapConnectableMode(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, GapConnectableMode>();
        for (GapConnectableMode s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    /**
     * Lookup function based on the type code. Returns null if the code does not exist.
     *
     * @param gapConnectableMode
     *            the code to lookup
     * @return enumeration value.
     */
    public static GapConnectableMode getGapConnectableMode(int gapConnectableMode) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(gapConnectableMode) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(gapConnectableMode);
    }

    /**
     * Returns the BlueGiga protocol defined value for this enum
     *
     * @return the BGAPI enumeration key
     */
    public int getKey() {
        return key;
    }
}
