package com.dyonovan.researchsystem.common.tileentity

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

  override def writeToNBT(tag: NBTTagCompound): NBTTagCompound = {
    super.writeToNBT(tag)

    tag
  }

  override def readFromNBT(tag: NBTTagCompound): Unit = {
    super.readFromNBT(tag)
  }
}
