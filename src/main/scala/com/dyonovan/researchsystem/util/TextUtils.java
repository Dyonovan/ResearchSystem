package com.dyonovan.researchsystem.util;

import net.minecraft.client.resources.I18n;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/24/2016
 */
public class TextUtils {

    public static int centerGuiText(int textWidth, int guiWidth) {
        return (guiWidth / 2) - (textWidth / 2);
    }

    public static String translate(String translateKey, Object... parameters) {
        return I18n.format(translateKey, parameters);
    }
}
