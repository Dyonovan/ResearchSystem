package com.dyonovan.researchsystem.common.commands;

import com.dyonovan.researchsystem.managers.ResearchManager;
import com.dyonovan.researchsystem.util.TextUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

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
public class IsUnlockCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "research_isunlock";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return TextUtils.translate("researchsystem:commands.isunlock.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 2) throw new WrongUsageException("researchsystem:commands.unlock.usage");

        EntityPlayer player = getPlayer(server, sender, args[0]);

        if (ResearchManager.isUnlocked(player, args[1])) {
            //TODO fix message
            throw new WrongUsageException("UNLOCKED");
        } else throw new WrongUsageException("LOCKED");
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 1;
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
