package com.dyonovan.researchsystem.client.gui;

import com.dyonovan.researchsystem.common.container.ContainerLaboratory;
import com.dyonovan.researchsystem.common.tileentity.TileLaboratory;
import com.dyonovan.researchsystem.lib.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
@SideOnly(Side.CLIENT)
class GuiLaboratory extends GuiContainer {

    GuiLaboratory(InventoryPlayer inventory, TileLaboratory tileEntity) {
        super(new ContainerLaboratory(inventory, tileEntity));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        ResourceLocation laboratoryGui = new ResourceLocation(Reference.Mod.MOD_ID + ":textures/gui/guiLaboratory.png");
        this.mc.getTextureManager().bindTexture(laboratoryGui);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j + 18, 0, 0, this.xSize, this.ySize);
    }
}
