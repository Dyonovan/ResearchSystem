package com.dyonovan.researchsystem.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import javax.annotation.Nullable;
import java.util.UUID;

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
public class UnlockedResearch implements IResearchCapability {

    private UUID group = null;

    @Override
    public NBTTagList get() {

        NBTTagList tagList = new NBTTagList();
        if (group != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("Group", group.toString());
            tagList.appendTag(tag);
        }
        return tagList;
    }

    @Override
    public void set(NBTTagList tagList) {

        if (!tagList.hasNoTags()) {
            NBTTagCompound tag = tagList.getCompoundTagAt(0);
            group = UUID.fromString(tag.getString("Group"));
        }
    }

    @Override
    public void setGroup(@Nullable UUID uuid) {
        if (uuid == null)
            group = UUID.randomUUID();
        else
            group = uuid;
    }

    @Override
    public UUID getGroup() {
        return group != null ? group : null;
    }
}
