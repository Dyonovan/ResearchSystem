package com.dyonovan.researchsystem.client.gui

import com.dyonovan.researchsystem.common.container.ContainerResearchManager
import com.dyonovan.researchsystem.common.tileentity.TileResearchManager
import com.teambr.bookshelf.client.gui.GuiBase
import net.minecraft.entity.player.EntityPlayer

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
class GuiResearchManager(player: EntityPlayer, tile: TileResearchManager)
  extends GuiBase(new ContainerResearchManager(player.inventory, tile), 175, 165, "tile.researchsystem:blockResearchManager.name"){

  override def addComponents(): Unit = {}
}
