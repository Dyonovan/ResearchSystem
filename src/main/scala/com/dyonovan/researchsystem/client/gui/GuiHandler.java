package com.dyonovan.researchsystem.client.gui;

import com.dyonovan.researchsystem.common.container.ContainerLaboratory;
import com.dyonovan.researchsystem.common.container.ContainerResearchManager;
import com.dyonovan.researchsystem.common.tileentity.TileResearchManager;
import com.dyonovan.researchsystem.lib.Reference;
import com.dyonovan.researchsystem.common.tileentity.TileLaboratory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/24/2016
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        BlockPos pos = new BlockPos(x, y, z);

        if (world.getTileEntity(pos) == null) return false;

        switch(ID) {
            case Reference.Gui.LABORATORY_GUI:
                return new ContainerLaboratory(player.inventory, (TileLaboratory) world.getTileEntity(pos));
            case Reference.Gui.RESEARCH_MANAGER_GUI:
                return new ContainerResearchManager(player.inventory, (TileResearchManager) world.getTileEntity(pos));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world instanceof WorldClient) {
            BlockPos pos = new BlockPos(x, y, z);

            switch(ID) {
                case Reference.Gui.LABORATORY_GUI:
                    return new GuiLaboratory(player.inventory, (TileLaboratory) world.getTileEntity(pos));
                case Reference.Gui.RESEARCH_MANAGER_GUI:
                    return new GuiResearchManager(player.inventory, (TileResearchManager) world.getTileEntity(pos));
            }
        }
        return null;
    }
}
