package com.dyonovan.researchsystem.common.commands;

import com.dyonovan.researchsystem.capability.ResearchCapability;
import com.dyonovan.researchsystem.collections.GroupResearch;
import com.dyonovan.researchsystem.managers.ResearchManager;
import com.dyonovan.researchsystem.network.PacketDispatcher;
import com.dyonovan.researchsystem.network.UpdateCapabilityPacket;
import com.dyonovan.researchsystem.util.TextUtils;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import com.sun.xml.internal.ws.client.dispatch.PacketDispatch;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/28/2016
 */
public class UnlockCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "research_unlock";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return TextUtils.translate("researchsystem:commands.unlock.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 2) throw new WrongUsageException("researchsystem:commands.unlock.usage");

        EntityPlayer player = getPlayer(server, sender, args[0]);
        //todo make sure player exists

        if (player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).getGroup() == null) {
            player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).setGroup(null);

        }

        UUID group = player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).getGroup();
        if (!ResearchManager.getGroupResearch().contains(group)) {
            if (!ResearchManager.isUnlocked(player, args[1])) {
                ResearchManager.setUnlocked(group, args[1]);
                    //TODO add msg saying its been added
            } else throw new WrongUsageException("researchsystem:commands.unlock.alreadyunlocked");
        }
        PacketDispatcher.net.sendTo(new UpdateCapabilityPacket(player, group), (EntityPlayerMP) player);
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 3;
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos)
    {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getPlayerList().getOppedPlayerNames()) : Collections.<String>emptyList();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 0;
    }
}
