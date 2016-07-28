package com.dyonovan.researchsystem.common.block

import com.dyonovan.researchsystem.ResearchSystem
import com.dyonovan.researchsystem.client.gui.GuiLaboratory
import com.dyonovan.researchsystem.common.container.ContainerLaboratory
import com.dyonovan.researchsystem.common.tileentity.TileLaboratory
import com.dyonovan.researchsystem.lib.Reference
import com.teambr.bookshelf.common.tiles.traits.OpensGui
import net.minecraft.block.{BlockContainer, SoundType}
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.{IBlockAccess, World}

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
class BlockLaboratory extends BlockContainer(Material.IRON) with OpensGui {

    setUnlocalizedName(Reference.MOD_ID + ":blockLaboratory")
    setCreativeTab(ResearchSystem.tabResearchSystem)
    setHardness(2.0F)
    setSoundType(SoundType.METAL)

    override def createNewTileEntity(worldIn: World, meta: Int): TileEntity = new TileLaboratory

    override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
        val te = world.getTileEntity(new BlockPos(x, y, z))
        te match {
            case tile: TileLaboratory => new ContainerLaboratory(player.inventory, tile)
            case _ => null
        }
    }

    override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
        val te = world.getTileEntity(new BlockPos(x, y, z))
        te match {
            case tile: TileLaboratory => new GuiLaboratory(player, tile)
            case _ => null
        }
    }

    override def isNormalCube(state: IBlockState, world: IBlockAccess, pos: BlockPos): Boolean = true

    override def isVisuallyOpaque: Boolean = false

    override def getRenderType(state: IBlockState): EnumBlockRenderType = EnumBlockRenderType.MODEL
}
