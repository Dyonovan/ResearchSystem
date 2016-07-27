package com.dyonovan.researchsystem.events

import com.dyonovan.researchsystem.util.SaveResearchData
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

/**
  * This file was created for ResearchSystem
  *
  * ResearchSystem is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 7/27/2016
  */
class WorldEvents {

    @SubscribeEvent
    def onWorldSave(event: WorldEvent.Save): Unit = {
        SaveResearchData.SaveData()
    }

    @SubscribeEvent
    def onWorldLoad(event: WorldEvent.Load): Unit = {
        SaveResearchData.LoadData()
    }

}
