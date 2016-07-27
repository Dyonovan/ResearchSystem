package com.dyonovan.researchsystem.capability;

import net.minecraft.nbt.NBTTagList;

import javax.annotation.Nullable;
import java.util.ArrayList;
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
public interface IResearchCapability {

    NBTTagList get();

    void set(NBTTagList nbt);

    void setGroup(@Nullable UUID uuid);

    UUID getGroup();
}
