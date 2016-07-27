package com.dyonovan.researchsystem.capability;

import com.dyonovan.researchsystem.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

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
public class ResearchCapability {

    @CapabilityInject(IResearchCapability.class)
    public static final Capability<IResearchCapability> UNLOCKED_RESEARCH = null;

    public static final EnumFacing DEFAULT_FACING = null;

    public static final ResourceLocation UNLOCKED = new ResourceLocation(Reference.MOD_ID(), "ResearchUnlocked");

    public static void register() {
        CapabilityManager.INSTANCE.register(IResearchCapability.class, new Capability.IStorage<IResearchCapability>() {

            @Override
            public NBTBase writeNBT(Capability<IResearchCapability> capability, IResearchCapability instance, EnumFacing side) {
                return instance.get();
            }

            @Override
            public void readNBT(Capability<IResearchCapability> capability, IResearchCapability instance, EnumFacing side, NBTBase nbt) {
                instance.set((NBTTagList) nbt);
            }
        }, UnlockedResearch::new);

        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    public static class EventHandler {
        @SubscribeEvent
        public void playerCreation(AttachCapabilitiesEvent.Entity event) {
            if (event.getEntity() instanceof EntityPlayer) {
                event.addCapability(UNLOCKED, new Provider());
            }
        }

        @SubscribeEvent
        public void playerClone(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                //TODO clone the capability
            }
        }
    }

    public static class Provider implements ICapabilitySerializable<NBTTagList> {

        private IResearchCapability researchCapability = new UnlockedResearch();

        @Override
        public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == UNLOCKED_RESEARCH;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
            if (capability == UNLOCKED_RESEARCH)
                return UNLOCKED_RESEARCH.cast(researchCapability);

            return null;
        }

        @Override
        public NBTTagList serializeNBT() {
            return (NBTTagList) UNLOCKED_RESEARCH.getStorage().writeNBT(UNLOCKED_RESEARCH, researchCapability, DEFAULT_FACING);
        }

        @Override
        public void deserializeNBT(NBTTagList nbt) {
            UNLOCKED_RESEARCH.getStorage().readNBT(UNLOCKED_RESEARCH, researchCapability, DEFAULT_FACING, nbt);
        }
    }
}
