package com.dyonovan.researchsystem.network;

import com.dyonovan.researchsystem.lib.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * This file was created for ResearchSystem
 * <p>
 * ResearchSystem is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 7/27/2016
 */
public class PacketDispatcher {
    public static SimpleNetworkWrapper net;
    private static int nextPacketID = 0;

    public static void initPackets() {
        net = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID().toUpperCase());

        registerMessage(UpdateCapabilityPacket.class, UpdateCapabilityPacket.class);
    }

    @SuppressWarnings("unchecked")
    private static void registerMessage(Class packet, Class message) {
        net.registerMessage(packet, message, nextPacketID, Side.CLIENT);
        net.registerMessage(packet, message, nextPacketID, Side.SERVER);
        nextPacketID++;
    }
}
