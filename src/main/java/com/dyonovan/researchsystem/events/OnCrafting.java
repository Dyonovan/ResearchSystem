package com.dyonovan.researchsystem.events;

import com.dyonovan.researchsystem.capability.ResearchCapability;
import com.dyonovan.researchsystem.capability.UnlockedResearch;
import com.dyonovan.researchsystem.collections.LockedList;
import com.dyonovan.researchsystem.managers.ResearchManager;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.ArrayList;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/26/2016
 */
public class OnCrafting {

    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        /*if (event.crafting.isItemEqual(new ItemStack(Blocks.WOODEN_SLAB))) {
            event.player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).unlock("test");
        }*/
        String name = event.crafting.getItem().getRegistryName().toString();

        for (LockedList list : ResearchManager.getLockedList()) {
            if (list.getBlockList().contains(name)) {
                ArrayList<String> research = event.player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).getResearch();
                if (!research.contains(list.getName())) {
                    //event.setCanceled(true);
                    event.player.addChatMessage(new TextComponentString("You have not unlocked that item!"));
                }
            }
        }
    }
}
