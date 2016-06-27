package com.dyonovan.researchsystem.managers;

import com.dyonovan.researchsystem.events.OnCrafting;
import net.minecraftforge.common.MinecraftForge;

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
public class EventManager {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new OnCrafting());
    }
}
