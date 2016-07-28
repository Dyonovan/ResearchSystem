package com.dyonovan.researchsystem.network

import java.util.UUID

import com.dyonovan.researchsystem.capability.ResearchCapability
import io.netty.buffer.ByteBuf
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.{IMessage, IMessageHandler, MessageContext}

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
class UpdateCapabilityPacket extends IMessage with IMessageHandler[UpdateCapabilityPacket, IMessage]{

    var uuid: UUID = _
    var entityPlayer: EntityPlayer = _
    var playerName = ""

    def this(entityPlayer: EntityPlayer, uuid: UUID) {
        this()
        this.entityPlayer = entityPlayer
        this.playerName = entityPlayer.getName
        this.uuid = uuid
    }
    override def toBytes(buf: ByteBuf): Unit = {
        ByteBufUtils.writeUTF8String(buf, uuid.toString)
        ByteBufUtils.writeUTF8String(buf, playerName)
    }

    override def fromBytes(buf: ByteBuf): Unit = {
        uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf))
        playerName = ByteBufUtils.readUTF8String(buf)
    }

    override def onMessage(message: UpdateCapabilityPacket, ctx: MessageContext): IMessage = {
        if (ctx.side.isClient) {
            val world = Minecraft.getMinecraft.theWorld
            val player = world.getPlayerEntityByName(message.playerName)
            player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).setGroup(message.uuid)
        }

        null
    }
}
