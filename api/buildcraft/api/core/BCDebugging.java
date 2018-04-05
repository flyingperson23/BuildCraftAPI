/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.core;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.lang.reflect.Method;
import java.util.Locale;

/** Provides a way to quickly enable or disable certain debug conditions via VM arguments or whether the client/server
 * is in a dev environment */
public class BCDebugging {
    public enum DebugStatus {
        NONE,
        ENABLE,
        LOGGING_ONLY,
        ALL
    }

    enum DebugLevel {
        LOG,
        COMPLEX;

        final String name = name().toLowerCase(Locale.ROOT);
        boolean isAllOn;
    }

    private static final DebugStatus DEBUG_STATUS;

    static {
        // Basically we enable debugging for the dev-environment, and disable everything for normal players.
        // However if you provide VM arguments then the behaviour changes somewhat:
        // (VM argument is "-Dbuildcraft.debug=...")
        // - "enable" Enables debugging even if you are not in a dev environment
        // - "disable" Disables ALL debugging (and doesn't output any messages even in the dev environment)
        // - "log" Major debug options are turned on. Registry setup + API usage etc
        // - "all" All possible debug options are turned on. Lots of spam. Not recommended.

        boolean isDev;
        try {
            Method getTileEntity = World.class.getDeclaredMethod("getTileEntity", BlockPos.class);
            BCLog.logger.info("[debugger] Method found: World.getTileEntity = " + getTileEntity);
            isDev = true;
        } catch (Throwable ignored) {
            // If it didn't find it then we aren't in a dev environment
            isDev = false;
            BCLog.logger.info("[debugger] Not a dev environment!");
        }

        String value = System.getProperty("buildcraft.debug");
        if (value == null) DEBUG_STATUS = DebugStatus.NONE;
        else switch (value) {
            case "enable":
                DEBUG_STATUS = DebugStatus.ENABLE;
                break;
            case "all":
                DEBUG_STATUS = DebugStatus.ALL;
                break;
            case "disable":
                // let people disable the messages if they are in a dev environment but don't want messages.
                DEBUG_STATUS = DebugStatus.NONE;
                break;
            case "log":
                // Some debugging options are more than just logging, so we will differentiate between them
                DEBUG_STATUS = DebugStatus.LOGGING_ONLY;
                break;
            default:
                if (isDev) {
                    DEBUG_STATUS = DebugStatus.ENABLE;
                } else {
                    // Most likely a built jar - don't spam people with info they probably don't need
                    DEBUG_STATUS = DebugStatus.NONE;
                }
                break;
        }

        switch (DEBUG_STATUS) {
            case ALL:
                BCLog.logger.info("[debugger] Debugging automatically enabled for ALL of buildcraft. Prepare for log spam.");
                break;
            case LOGGING_ONLY:
                BCLog.logger.info("[debugger] Debugging automatically enabled for some non-spammy parts of buildcraft.");
                break;
            case ENABLE:
                BCLog.logger.info("[debugger] Debugging not automatically enabled for all of buildcraft. Logging all possible debug options.");
                BCLog.logger.info("              To enable it for only logging messages add \"-Dbuildcraft.debug=log\" to your launch VM arguments");
                BCLog.logger.info("              To enable it for ALL debugging \"-Dbuildcraft.debug=all\" to your launch VM arguments");
                BCLog.logger.info("              To remove this message and all future ones add \"-Dbuildcraft.debug=disable\" to your launch VM arguments");
                break;
        }

        DebugLevel.COMPLEX.isAllOn = DEBUG_STATUS == DebugStatus.ALL;
        DebugLevel.LOG.isAllOn = DEBUG_STATUS == DebugStatus.ALL || DEBUG_STATUS == DebugStatus.LOGGING_ONLY;
    }

    public static boolean shouldDebugComplex(String string) {
        return shouldDebug(string, DebugLevel.COMPLEX);
    }

    public static boolean shouldDebugLog(String string) {
        return shouldDebug(string, DebugLevel.LOG);
    }

    private static boolean shouldDebug(String option, DebugLevel type) {
        String prop = getProp(option);
        String actual = System.getProperty(prop);
        if ("false".equals(actual)) {
            BCLog.logger.info("[debugger] Debugging manually disabled for \"" + option + "\" (" + type + ").");
            return false;
        } else if ("true".equals(actual)) {
            BCLog.logger.info("[debugger] Debugging enabled for \"" + option + "\" (" + type + ").");
            return true;
        }
        if (type.isAllOn) {
            BCLog.logger.info("[debugger] Debugging automatically enabled for \"" + option + "\" (" + type + ").");
            return true;
        }
        if ("complex".equals(actual) || type.name.equals(actual)) {
            BCLog.logger.info("[debugger] Debugging enabled for \"" + option + "\" (" + type + ").");
            return true;
        } else if (DEBUG_STATUS != DebugStatus.NONE) {
            StringBuilder log = new StringBuilder();
            log.append("[debugger] To enable debugging for ");
            log.append(option);
            log.append(" add the option \"-D");
            log.append(prop);
            log.append("=true\" to your launch config as a VM argument (").append(type).append(").");
            BCLog.logger.info(log);
        }
        return false;
    }

    private static String getProp(String string) {
        return "buildcraft." + string + ".debug";
    }
}
