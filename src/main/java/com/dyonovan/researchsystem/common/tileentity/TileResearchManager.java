package com.dyonovan.researchsystem.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

import javax.annotation.Nonnull;
import java.util.UUID;

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
public class TileResearchManager extends BaseTile implements ITickable {

    private UUID owner;

    public TileResearchManager() {

    }

    @Override
    public void update() {

    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public UUID getOwner() {
        return owner;
    }

    public String getOwnerName() {
        if (owner != null)
            return worldObj.getPlayerEntityByUUID(owner).getDisplayNameString();
        else return "";
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("Owner", owner.toString());
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        String owner = tag.getString("Owner");
        if (!owner.isEmpty())
            setOwner(UUID.fromString(owner));
    }
}
