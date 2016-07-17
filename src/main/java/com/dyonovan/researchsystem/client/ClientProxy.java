package com.dyonovan.researchsystem.client;

import com.dyonovan.researchsystem.common.CommonProxy;
import com.dyonovan.researchsystem.lib.Reference;
import com.dyonovan.researchsystem.managers.BlockManager;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/20/2016
 */
public class ClientProxy extends CommonProxy {

    public void preInit() {
        registerBlockModel(BlockManager.blockLaboratory, "blockLaboratory", "normal");
        registerBlockModel(BlockManager.blockResearchManager, "blockResearchManager", "normal");
    }

    public void init() {}

    public void postInit() {}

    private void registerBlockModel(Block block, String name, String variants, int meta) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
                new ModelResourceLocation(new ResourceLocation(Reference.Mod.MOD_ID, name), variants));
    }

    private void registerBlockModel(Block block, String name, String variants) {
        registerBlockModel(block, name, variants, 0);
    }

}
