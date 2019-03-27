/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.persistence.exec.internal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.items.Item;
import org.openhab.core.persistence.PersistenceService;
import org.openhab.core.persistence.strategy.PersistenceStrategy;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the implementation of the Exec {@link PersistenceService}.
 *
 * @author Henrik Sjöstrand - Initial contribution
 * @author Thomas.Eichstaedt-Engelen - Initial contribution
 * @author Kai Kreuzer - Migration to 3.x
 */
@NonNullByDefault
@Component
public class ExecPersistenceService implements PersistenceService {

    private static final Logger logger = LoggerFactory.getLogger(ExecPersistenceService.class);

    @Override
    public String getId() {
        return "exec";
    }

    @Override
    public String getLabel(@Nullable Locale locale) {
        return "Commandline Execution";
    }

    @Override
    public void store(Item item, String alias) {
        String execCmd = null;
        BufferedReader reader = null;

        try {
            execCmd = formatAlias(alias, item.getState().toString(), Calendar.getInstance().getTime(), item.getName());
            logger.debug("Executing command [{}]", execCmd);

            Process process = Runtime.getRuntime().exec(execCmd);
            String line = null;
            String output = "";
            logger.debug("Stored item '{}' as '{}' using Exec at {}.", item.getName(), item.getState(),
                    new java.util.Date());

            // Collect the output stream (if any)
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                output = output + line;
            }
            reader.close();
            if (output.length() > 0) {
                logger.debug("Output from exec command is: {}", output);
            }

            // Collect the error stream (if any)
            output = "";
            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = reader.readLine()) != null) {
                output = output + line;
            }
            reader.close();
            if (output.length() > 0) {
                logger.debug("Error from exec command is: {}", output);
            }

            process.waitFor();

        } catch (Exception e) {
            logger.error("Could not execute command [{}]", execCmd, e);
        } finally {
            try {
                reader.close();
                reader = null;
            } catch (Exception hidden) {
            }
        }
    }

    @Override
    public void store(Item item) {
        throw new UnsupportedOperationException(
                "The Exec service requires aliases for persistence configurations that should match the Exec statement. Please configure exec.persist properly.");
    }

    /**
     * Formats the given <code>alias</code> by utilizing {@link Formatter}.
     *
     * @param alias the alias String which contains format strings
     * @param values the values which will be replaced in the alias String
     *
     * @return the formatted value. All format strings are replaced by
     *         appropriate values
     * @see java.util.Formatter for detailed information on format Strings.
     */
    protected String formatAlias(String alias, Object... values) {
        return String.format(alias, values);
    }

    @Override
    public List<PersistenceStrategy> getDefaultStrategies() {
        return Collections.emptyList();
    }

}
