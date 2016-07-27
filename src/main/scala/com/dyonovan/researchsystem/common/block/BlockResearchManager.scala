package com.dyonovan.researchsystem.common.block

import com.dyonovan.researchsystem.ResearchSystem
import com.dyonovan.researchsystem.client.gui.GuiResearchManager
import com.dyonovan.researchsystem.common.container.ContainerResearchManager
import com.dyonovan.researchsystem.common.tileentity.TileResearchManager
import com.dyonovan.researchsystem.lib.Reference
import com.teambr.bookshelf.common.tiles.traits.OpensGui
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.block.{BlockContainer, SoundType}
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.{IBlockAccess, World}

/**
  * This file was created for ResearchSystem
  *
  *
  * ResearchSystem is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 7/22/2016
  */
class BlockResearchManager extends BlockContainer(Material.IRON) with OpensGui {

  setUnlocalizedName(Reference.MOD_ID + ":blockResearchManager")
  setCreativeTab(ResearchSystem.tabResearchSystem)
  setHardness(2.0F)
  setSoundType(SoundType.METAL)

  override def onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
    val te: TileEntity = world.getTileEntity(pos)
    if (te != null && te.isInstanceOf[TileResearchManager])
      te.asInstanceOf[TileResearchManager].setOwner(placer.getUniqueID)
  }

  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
    val te = world.getTileEntity(new BlockPos(x, y, z))
    te match {
      case tile: TileResearchManager => new ContainerResearchManager(player.inventory, tile)
      case _ => null
    }
  }

  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
    val te = world.getTileEntity(new BlockPos(x, y, z))
    te match {
      case tile: TileResearchManager => new GuiResearchManager(player, tile)
      case _ => null
    }
  }

  override def createNewTileEntity(worldIn: World, meta: Int): TileEntity = new TileResearchManager()

  override def isNormalCube(state: IBlockState, world: IBlockAccess, pos: BlockPos): Boolean = true

  override def isVisuallyOpaque: Boolean = false

  override def getRenderType(state: IBlockState): EnumBlockRenderType = EnumBlockRenderType.MODEL
}
