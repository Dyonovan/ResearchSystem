package com.dyonovan.researchsystem.client.gui;

import com.dyonovan.researchsystem.common.container.ContainerResearchManager;
import com.dyonovan.researchsystem.common.tileentity.TileResearchManager;
import com.dyonovan.researchsystem.lib.Reference;
import com.dyonovan.researchsystem.util.TextUtils;
import net.minecraft.client.gui.GuiButton;
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
class GuiResearchManager extends GuiContainer {

    private TileResearchManager te;

    GuiResearchManager(InventoryPlayer inventory, TileResearchManager tileEntity) {
        super(new ContainerResearchManager(inventory, tileEntity));

        this.te = tileEntity;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButton(1, 10, 52, 20, 20, "+"));
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        //Title
        String title = TextUtils.translate("tile.researchsystem:blockResearchManager.name");
        int xPos = TextUtils.centerGuiText (this.fontRendererObj.getStringWidth(title), this.xSize);
        this.fontRendererObj.drawString(title, xPos, 5, 4210752);

        //Owner
        String owner = "Owner: " + te.getOwnerName();
        xPos = TextUtils.centerGuiText (this.fontRendererObj.getStringWidth(owner), this.xSize);
        this.fontRendererObj.drawString(owner, xPos, 15, 4210752);

    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        ResourceLocation laboratoryGui = new ResourceLocation(Reference.Mod.MOD_ID + ":textures/gui/guiResearchManager.png");
        this.mc.getTextureManager().bindTexture(laboratoryGui);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        switch(guiButton.id) {
            case 1:
                break;
            default:
        }
    }
}
