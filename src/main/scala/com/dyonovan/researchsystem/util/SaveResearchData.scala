package com.dyonovan.researchsystem.util

import java.io.{File, FileInputStream, FileOutputStream}
import java.util
import java.util.UUID

import com.dyonovan.researchsystem.collections.GroupResearch
import com.dyonovan.researchsystem.managers.ResearchManager
import net.minecraft.nbt.{CompressedStreamTools, NBTTagCompound, NBTTagList}
import net.minecraftforge.common.DimensionManager

import collection.JavaConverters._

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
    val dataFile = new File(dataDir, "ResearchSystem.dat")

    def SaveData(): Unit = {
        if (!dataDir.exists())
            dataDir.mkdir()

        val nbtList = new NBTTagList
        val rs = ResearchManager.getGroupResearch.asScala
        if (rs.isEmpty) return

        for (res <- rs) {
            val nbt = new NBTTagCompound
            nbt.setString("UUID", res.getGroupUUID.toString)
            val list = new NBTTagList
            for (i <- 0 until res.getUnlockedResearch.size()) {
                val tag = new NBTTagCompound
                tag.setString("UnLocked" + i, res.getUnlockedResearch.get(i))
                list.appendTag(tag)
            }
            nbt.setTag("UnLocked", list)
            nbtList.appendTag(nbt)
        }

        val nbtTag = new NBTTagCompound
        nbtTag.setTag("GroupResearch", nbtList)
        CompressedStreamTools.writeCompressed(nbtTag, new FileOutputStream(dataFile))
    }

    def LoadData(): Unit = {
        if (!dataFile.exists()) return

        val rs = ResearchManager.getGroupResearch
        rs.clear()

        val nbtTag = CompressedStreamTools.readCompressed(new FileInputStream(dataFile))
        val nbtList = nbtTag.getTag("GroupResearch").asInstanceOf[NBTTagList]

        for (i <- 0 until nbtList.tagCount()) {
            val nbt = nbtList.getCompoundTagAt(i)
            val uuid = UUID.fromString(nbt.getString("UUID"))
            val unlocked = new util.ArrayList[String]()
            val list = nbt.getTag("UnLocked").asInstanceOf[NBTTagList]
            for (j <- 0 until list.tagCount()) {
                val s = list.getCompoundTagAt(j)
                unlocked.add(s.getString("UnLocked" + j))
            }
            rs.add(new GroupResearch(uuid, unlocked))
        }
    }
}
