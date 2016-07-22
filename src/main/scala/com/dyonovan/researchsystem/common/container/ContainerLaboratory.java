package com.dyonovan.researchsystem.common.container;

import com.dyonovan.researchsystem.common.tileentity.TileLaboratory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

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
public class ContainerLaboratory extends Container {

    public ContainerLaboratory(InventoryPlayer inventory, TileLaboratory tileEntity) {
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}