package com.dyonovan.researchsystem;

import com.dyonovan.researchsystem.client.gui.GuiHandler;
import com.dyonovan.researchsystem.common.CommonProxy;
import com.dyonovan.researchsystem.lib.Reference;
import com.dyonovan.researchsystem.managers.BlockManager;
import com.dyonovan.researchsystem.managers.ConfigManager;
import com.dyonovan.researchsystem.managers.ResearchManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

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
@SuppressWarnings({"unused", "WeakerAccess"})
@Mod(modid             = Reference.Mod.MOD_ID,
        name           = Reference.Mod.MOD_NAME,
        version        = Reference.Mod.VERSION,
        dependencies   = Reference.Mod.DEPENDENCIES,
        updateJSON     = Reference.Mod.UPDATE_JSON)
public class ResearchSystem {

    @Instance(Reference.Mod.MOD_ID)
    public static ResearchSystem instance;

    @SidedProxy(clientSide = "com.dyonovan.researchsystem.client.ClientProxy",
            serverSide = "com.dyonovan.researchsystem.common.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabResearchSystem = new CreativeTabs(Reference.Mod.MOD_ID + ".tabResearchSystem") {
        @SuppressWarnings("NullableProblems")
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.NETHERRACK); //TODO switch icon
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigManager.preInit(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + "ResearchSystem");
        BlockManager.preInit();
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ResearchManager.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(ResearchSystem.instance, new GuiHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
