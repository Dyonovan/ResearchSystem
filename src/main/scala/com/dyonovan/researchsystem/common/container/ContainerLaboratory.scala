package com.dyonovan.researchsystem.common.container

import com.dyonovan.researchsystem.common.tileentity.TileLaboratory
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
  * @since 7/28/2016
  */
class ContainerLaboratory(playerInventory: InventoryPlayer, tile: TileLaboratory) extends Container {

    override def canInteractWith(playerIn: EntityPlayer): Boolean = true
}
