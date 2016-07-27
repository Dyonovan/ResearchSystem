package com.dyonovan.researchsystem.util

import java.io.{File, FileInputStream, FileOutputStream}

import net.minecraft.nbt.{CompressedStreamTools, NBTTagCompound}
import net.minecraftforge.common.DimensionManager

/**
  * This file was created for ResearchSystem
  *
  * ResearchSystem is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 7/27/2016
  */
object SaveResearchData {

    val mcDir = DimensionManager.getCurrentSaveRootDirectory
    val dataDir = new File(mcDir, "ResearchSystem")
    dataDir.mkdir()
    val dataFile = new File(dataDir, "ResearchSystem.dat")

    def SaveData(): Unit = {
        val nbt = new NBTTagCompound
        nbt.setBoolean("Testing", false) //TODO

        CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(dataFile))
    }

    def LoadData(): Unit = {
        if (!dataFile.exists()) return

        val nbt = CompressedStreamTools.readCompressed(new FileInputStream(dataFile))

        //TODO
    }
}
