package com.dyonovan.researchsystem

import java.io.File

import com.dyonovan.researchsystem.capability.ResearchCapability
import com.dyonovan.researchsystem.common.CommonProxy
import com.dyonovan.researchsystem.common.commands.UnlockCommand
import com.dyonovan.researchsystem.events.CraftingCatcher
import com.dyonovan.researchsystem.lib.Reference
import com.dyonovan.researchsystem.managers.{BlockManager, ConfigManager, EventManager, ResearchManager}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent, FMLServerStartingEvent}
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.common.{Mod, SidedProxy}
import net.minecraftforge.oredict.RecipeSorter

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
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, modLanguage = "scala", updateJSON = Reference.UPDATE_JSON)
object ResearchSystem {

    var configDir: String = ""

    @SidedProxy(clientSide = "com.dyonovan.researchsystem.client.ClientProxy", serverSide = "com.dyonovan.researchsystem.common.CommonProxy")
    var proxy: CommonProxy = _

    var tabResearchSystem: CreativeTabs = new CreativeTabs(Reference.MOD_ID + ".tabResearchSystem") {
        @SuppressWarnings(Array("NullableProblems"))
        def getTabIconItem: Item = Item.getItemFromBlock(Blocks.NETHERRACK) //TODO switch icon
    }

    @EventHandler
    def preInit(event: FMLPreInitializationEvent) {
        configDir = event.getModConfigurationDirectory.getAbsolutePath + File.separator + "ResearchSystem"
        ConfigManager.preInit()
        BlockManager.preInit()
        proxy.preInit()
        ResearchCapability.register()
    }

    @EventHandler
    def init(event: FMLInitializationEvent) {
        ResearchManager.init()
        EventManager.init()
    }

    @EventHandler
    def postInit(event: FMLPostInitializationEvent) {
        ResearchManager.removeRecipes()
        GameRegistry.addRecipe(new CraftingCatcher)
        RecipeSorter.register("Testing", classOf[CraftingCatcher], RecipeSorter.Category.SHAPED, "before:minecraft:shaped")
    }

    @EventHandler def serverLoad(event: FMLServerStartingEvent) {
        event.registerServerCommand(new UnlockCommand)
    }
}
