package com.dyonovan.researchsystem.managers;

import com.dyonovan.researchsystem.util.LogHelper;

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
public class ResearchManager {

    public static void init() {
        if (!loadFromFiles())
            generateDefaults();
        else
            LogHelper.info("Research System loaded...");
    }

    private static void generateDefaults() {

    }

    private static boolean loadFromFiles() {
        return false;
    }
}
