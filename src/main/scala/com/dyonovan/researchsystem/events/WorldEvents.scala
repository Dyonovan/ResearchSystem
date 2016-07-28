package com.dyonovan.researchsystem.events

import com.dyonovan.researchsystem.capability.ResearchCapability
import com.dyonovan.researchsystem.managers.ResearchManager
import com.dyonovan.researchsystem.network.{PacketDispatcher, UpdateCapabilityPacket}
import com.dyonovan.researchsystem.util.SaveResearchData
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent

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

    @SubscribeEvent
    def onPlayerJoin(event: PlayerEvent.PlayerLoggedInEvent): Unit = {
        val player = event.player
        if (player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).getGroup != null) {
            val uuid = player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).getGroup
            PacketDispatcher.net.sendTo(new UpdateCapabilityPacket(player, uuid), player.asInstanceOf[EntityPlayerMP])
        }

    }

}
