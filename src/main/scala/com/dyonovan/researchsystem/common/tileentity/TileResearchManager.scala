package com.dyonovan.researchsystem.common.tileentity

import java.util.UUID

import com.teambr.bookshelf.common.tiles.traits.UpdatingTile
import net.minecraft.nbt.NBTTagCompound

/**
  * This file was created for ResearchSystem
  *
  * ResearchSystem is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 7/22/2016
  */
class TileResearchManager extends UpdatingTile {

  private var owner: UUID = _

  def getOwner(owner: UUID): String = {
    if (this.owner != null)
      worldObj.getPlayerEntityByUUID(owner).getDisplayNameString
    else ""
  }

  def setOwner(owner: UUID): Unit = {
    this.owner = owner
  }

  override def writeToNBT(tag: NBTTagCompound): NBTTagCompound = {
    super.writeToNBT(tag)
    tag.setString("Owner", owner.toString)
    tag
  }

  override def readFromNBT(tag: NBTTagCompound): Unit = {
    super.readFromNBT(tag)
    val o: String = tag.getString("Owner")
    if (!o.isEmpty)
      setOwner(UUID.fromString(o))
  }
}
