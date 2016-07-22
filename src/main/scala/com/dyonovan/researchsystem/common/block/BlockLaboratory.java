package com.dyonovan.researchsystem.common.block;

import com.dyonovan.researchsystem.ResearchSystem;
import com.dyonovan.researchsystem.common.tileentity.TileLaboratory;
import com.dyonovan.researchsystem.lib.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/22/2016
 */
public class BlockLaboratory extends BlockContainer {

    public BlockLaboratory() {
        super(Material.IRON);

        setUnlocalizedName(Reference.Mod.MOD_ID + ":blockLaboratory");
        setCreativeTab(ResearchSystem.tabResearchSystem());
        setHardness(2.0F);
        setSoundType(SoundType.METAL);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileLaboratory();
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isVisuallyOpaque()
    {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

}
