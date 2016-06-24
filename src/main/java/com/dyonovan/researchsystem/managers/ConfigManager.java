package com.dyonovan.researchsystem.managers;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/20/2016
 */
public class ConfigManager {

    public static void preInit(String configFolder) {

        Configuration config = new Configuration(new File(configFolder + File.separator + "ResearchSystem.cfg"));

        config.load();

        config.save();
    }
}
