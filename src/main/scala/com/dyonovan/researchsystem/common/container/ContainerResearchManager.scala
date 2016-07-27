package com.dyonovan.researchsystem.common.container

import com.dyonovan.researchsystem.common.tileentity.TileResearchManager
import net.minecraft.entity.player.{EntityPlayer, InventoryPlayer}
import net.minecraft.inventory.Container

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
class ContainerResearchManager(playerInventory: InventoryPlayer, tile: TileResearchManager) extends Container {




  override def canInteractWith(playerIn: EntityPlayer): Boolean = true
}
