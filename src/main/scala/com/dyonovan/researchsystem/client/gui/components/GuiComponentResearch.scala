package com.dyonovan.researchsystem.client.gui.components

import java.awt.Color

import com.teambr.bookshelf.client.gui.component.BaseComponent
import com.teambr.bookshelf.client.gui.component.control.{GuiComponentScrollBar, GuiComponentTextBox}
import com.teambr.bookshelf.client.gui.component.display.GuiComponentColoredZone
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.EntityPlayer

/**
  * This file was created for ResearchSystem
  *
  * ResearchSystem is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Paul Davis <pauljoda>
  * @since 7/28/2016
  */
class GuiComponentResearch(x : Int, y : Int, var width : Int, var height : Int, var player : EntityPlayer)
    extends BaseComponent(x, y) {

    //Data


    //Backgrounds
    var backgroundList     = new GuiComponentColoredZone(x, y, width, height, new Color(0, 255, 0, 150))
    var backgroundRequired = new GuiComponentColoredZone(x + width + 2, y, 250 - width - 12, 50, new Color(255, 0, 0, 150))
    var backgroundTree     = new GuiComponentColoredZone(x + width + 2, y + 52, 250 - width - 12, height - 52, new Color(0, 0, 255, 150))

    // List components
    var searchBar = new GuiComponentTextBox(x + 1, y + 1, width - 2, 14) {
        override def fieldUpdated(value: String): Unit = {}
    }

    var listScrollBar = new GuiComponentScrollBar(x + width - 14, y + 16, height - 16) {
        override def onScroll(position: Float): Unit = {}
    }

    /***
      * Called to load important bits, load research here
      */
    override def initialize(): Unit = {}

    /**
      * Render research backgrounds, slot and items
      */
    override def render(guiLeft: Int, guiTop: Int, mouseX: Int, mouseY: Int): Unit = {
        // Backgrounds
        GlStateManager.pushAttrib()

        backgroundList.render(guiLeft, guiTop, mouseX, mouseY)
        backgroundRequired.render(guiLeft, guiTop, mouseX, mouseY)
        backgroundTree.render(guiLeft, guiTop, mouseX, mouseY)

        GlStateManager.popAttrib()

        //List components
        GlStateManager.pushMatrix()
        GlStateManager.pushAttrib()

        searchBar.render(guiLeft, guiTop, mouseX, mouseY)
        listScrollBar.render(guiLeft, guiTop, mouseX, mouseY)

        GlStateManager.popAttrib()
        GlStateManager.popMatrix()
    }

    /**
      * Render overlay objects
      */
    override def renderOverlay(guiLeft: Int, guiTop: Int, mouseX: Int, mouseY: Int): Unit = {
        //List components
        GlStateManager.pushMatrix()
        GlStateManager.pushAttrib()

        searchBar.renderOverlay(guiLeft, guiTop, mouseX, mouseY)
        listScrollBar.renderOverlay(guiLeft, guiTop, mouseX, mouseY)

        GlStateManager.popAttrib()
        GlStateManager.popMatrix()
    }

    /**
      * Called when the mouse is pressed
      * @param mouseX Mouse X Position
      * @param y Mouse Y Position
      * @param button Mouse Button
      */
    override def mouseDown(mouseX: Int, y: Int, button: Int) {
        searchBar.mouseDown(mouseX, y, button)
        listScrollBar.mouseDown(mouseX, y, button)
    }

    /**
      * Called when the user drags the component
      * @param x Mouse X Position
      * @param y Mouse Y Position
      * @param button Mouse Button
      * @param time How long
      */
    override def mouseDrag(x: Int, y: Int, button: Int, time: Long) {
        listScrollBar.mouseDrag(x, y, button, time)
    }

    /**
      * Called when the mouse button is over the component and released
      * @param x Mouse X Position
      * @param y Mouse Y Position
      * @param button Mouse Button
      */
    override def mouseUp(x: Int, y: Int, button: Int) {
        listScrollBar.mouseUp(x, y, button)
    }

    /**
      * Used when a key is pressed
      * @param letter The letter
      * @param keyCode The code
      */
    override def keyTyped(letter: Char, keyCode: Int) {
        searchBar.keyTyped(letter, keyCode)
    }

    override def getWidth: Int = 250

    override def getHeight: Int = 175
}
