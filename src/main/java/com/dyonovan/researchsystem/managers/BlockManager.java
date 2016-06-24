package com.dyonovan.researchsystem.managers;

import com.dyonovan.researchsystem.common.block.BlockLaboratory;
import com.dyonovan.researchsystem.common.block.BlockResearchManager;
import com.dyonovan.researchsystem.common.tileentity.TileResearchManager;
import com.dyonovan.researchsystem.lib.Reference;
import com.dyonovan.researchsystem.common.tileentity.TileLaboratory;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/23/2016
 */
public class BlockManager {

    public static Block blockLaboratory;
    public static Block blockResearchManager;

    public static void preInit() {

        blockLaboratory = registerBlock(new BlockLaboratory(), "blockLaboratory", TileLaboratory.class);
        blockResearchManager = registerBlock(new BlockResearchManager(), "blockResearchManager", TileResearchManager.class);
    }

    private static Block registerBlock(Block block, String name, Class<? extends TileEntity> tile) {

        if (block.getRegistryName() == null)
            block.setRegistryName(name);

        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(name));

        GameRegistry.registerTileEntity(tile, Reference.Mod.MOD_ID + ":" + tile.getSimpleName());

        return block;
    }
}
