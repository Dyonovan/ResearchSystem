package com.dyonovan.researchsystem.client.gui

import com.dyonovan.researchsystem.client.gui.components.GuiComponentResearch
import com.dyonovan.researchsystem.common.container.ContainerResearchManager
import com.dyonovan.researchsystem.common.tileentity.TileResearchManager
import com.teambr.bookshelf.client.gui.GuiBase
import com.teambr.bookshelf.helper.LogHelper
import net.minecraft.entity.player.EntityPlayer
import org.lwjgl.input.Keyboard

/**
  * This file was created for ResearchSystem
  *
  * ResearchSystem is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 7/22/2016
  */
class GuiResearchManager(var player: EntityPlayer, tile: TileResearchManager)
        extends GuiBase(new ContainerResearchManager(player.inventory, tile), 250, 175, "tile.researchsystem:blockResearchManager.name") {

    lazy val RESEARCH_COMPONENT = 1

    override def addComponents(): Unit = {
        components += new GuiComponentResearch(5, 16, 95, 154, player)
    }

    /**
      * Used when a key is pressed
      * @param letter The letter
      * @param keyCode The code
      */
    override def keyTyped(letter: Char, keyCode: Int) {
        components(RESEARCH_COMPONENT) match {
            case textBox: GuiComponentResearch =>
                if(keyCode != Keyboard.KEY_ESCAPE && textBox.searchBar.getTextField.isFocused)
                    textBox.keyTyped(letter, keyCode)
                else
                    super.keyTyped(letter, keyCode)
            case _ => LogHelper.debug("This is not the text box")}
    }
}
