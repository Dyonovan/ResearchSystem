package com.dyonovan.researchsystem;

import com.dyonovan.researchsystem.common.CommonProxy;
import com.dyonovan.researchsystem.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * This file was created for ResearchSystem
 * <p>
 * ResearchSystem is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 12/22/2016
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, updateJSON = Reference.UPDATE_JSON)
public class ResearchSystem {

    @SidedProxy(clientSide = "com.dyonovan.researchsystem.client.ClientProxy", serverSide = "com.dyonovan.researchsystem.common.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabResearchSystem = new CreativeTabs(Reference.MOD_ID + ".tabResearchSystem") {
        @Override
        @SuppressWarnings("NullableProblems")
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.NETHERBRICK);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {

    }
}
