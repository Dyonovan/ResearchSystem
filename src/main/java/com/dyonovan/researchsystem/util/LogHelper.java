package com.dyonovan.researchsystem.util;

import com.dyonovan.researchsystem.lib.Reference;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/26/2016
 */
public class LogHelper {

    private static Logger logger;

    public void LogHelper() {
        logger = LogManager.getLogger(Reference.Mod.MOD_ID);
    }

    public static void log(Level level, Object toPrint) { logger.log(level, toPrint.toString()); }

    public static void severe(Object toPrint) { logger.log(Level.FATAL, toPrint.toString()); }

    public static void info(Object toPrint) { logger.log(Level.INFO, toPrint.toString()); }
}
