package com.dyonovan.researchsystem.client.gui

import com.dyonovan.researchsystem.common.container.ContainerLaboratory
import com.dyonovan.researchsystem.common.tileentity.TileLaboratory
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
  * @since 7/28/2016
  */
class GuiLaboratory(var player: EntityPlayer, tile: TileLaboratory)
        extends GuiBase(new ContainerLaboratory(player.inventory, tile), 250, 175, "tile.researchsystem:blockLaboratory.name") {

    override def addComponents(): Unit = { }
}
