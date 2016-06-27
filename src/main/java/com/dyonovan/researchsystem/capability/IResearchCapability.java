package com.dyonovan.researchsystem.capability;

import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;

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
public interface IResearchCapability {

    ArrayList<String> unLocked = new ArrayList<>();

    NBTTagList get();

    void set(NBTTagList nbt);

    void unlock(String name);

    ArrayList<String> getResearch();
}
