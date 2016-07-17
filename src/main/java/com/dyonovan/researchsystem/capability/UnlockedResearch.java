package com.dyonovan.researchsystem.capability;

import net.minecraft.nbt.NBTTagCompound;
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
@SuppressWarnings("WeakerAccess")
public class UnlockedResearch implements IResearchCapability {

    @Override
    public NBTTagList get() {
        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < unLocked.size(); i++) {
            String s = unLocked.get(i);
            if (s != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("String" + i, s);
                tagList.appendTag(tag);
            }
        }
        return tagList;
    }

    @Override
    public void set(NBTTagList tag) {
        for (int i = 0; i < tag.tagCount(); i++) {
            NBTTagCompound tagCompound = tag.getCompoundTagAt(i);
            String s = tagCompound.getString("String" + i);
            unLocked.add(s);
        }
    }

    @Override
    public void unlock(String name) {
        unLocked.add(name);
    }

    @Override
    public ArrayList<String> getResearch() {
        return unLocked;
    }
}
