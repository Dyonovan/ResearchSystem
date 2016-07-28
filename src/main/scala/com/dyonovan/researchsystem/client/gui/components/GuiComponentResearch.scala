package com.dyonovan.researchsystem.client.gui.components

import java.awt.Color
import java.util
import java.util.Comparator

import com.dyonovan.researchsystem.client.gui.GuiResearchManagerConstants
import com.dyonovan.researchsystem.collections.ResearchNode
import com.dyonovan.researchsystem.managers.ResearchManager
import com.dyonovan.researchsystem.managers.ResearchManager.Status
import com.teambr.bookshelf.client.gui.component.BaseComponent
import com.teambr.bookshelf.client.gui.component.control.{GuiComponentButton, GuiComponentScrollBar, GuiComponentTextBox}
import com.teambr.bookshelf.client.gui.component.display.{GuiComponentColoredZone, GuiComponentText}
import com.teambr.bookshelf.client.gui.{GuiBase, GuiColor}
import com.teambr.bookshelf.util.RenderUtils
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.renderer.{GlStateManager, RenderHelper}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import org.lwjgl.opengl.GL11

import scala.collection.mutable.ArrayBuffer

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
    var visibleNodes : util.ArrayList[ResearchNode] = _
    var activeNode : ResearchNode = null

    //Backgrounds
    var backgroundList     = new GuiComponentColoredZone(x, y, width, height, new Color(150, 150, 150, 150))
    var backgroundRequired = new GuiComponentColoredZone(x + width + 2, y, 250 - width - 12, 55, new Color(150, 150, 150, 150))
    var backgroundRequiredTitle = new GuiComponentColoredZone(x + width + 2, y, 250 - width - 12, 14, new Color(150, 150, 150, 150))
    var backgroundTree     = new GuiComponentColoredZone(x + width + 2, y + 57, 250 - width - 12, height - 57, new Color(150, 150, 150, 150))
    var backgroundTreeTitle     = new GuiComponentColoredZone(x + width + 2, y + 57, 250 - width - 12, 14, new Color(150, 150, 150, 150))

    // List Components
    var searchBar = new GuiComponentTextBox(x + 1, y + 1, width - 2, 14) {
        override def fieldUpdated(value: String): Unit = {}
    }

    var listScrollBar = new GuiComponentScrollBar(x + width - 14, y + 16, height - 16) {
        override def onScroll(position: Float): Unit = {
            currentScroll = position
            updateVisibleNodes()
        }
    }
    var currentScroll = 0F

    lazy val MAX_SIZE_LIST = 8
    var currentButtons : Array[GuiComponentButton] = _

    // Unlock Components
    var requiredTitle = new GuiComponentText("NONE", x + width + 6, y + 3)

    // Required Components
    var requiredResearchTitle = new GuiComponentText("Required Research", x + width + 6, y + 60)
    var requiredButtons : Array[GuiComponentButton] = _

    /***
      * Called to load important bits, load research here
      */
    override def initialize(): Unit = {
        //Load all research
        visibleNodes = new util.ArrayList[ResearchNode]()
        visibleNodes.addAll(ResearchManager.getResearchNodes)

        updateButtons()

        if(GuiResearchManagerConstants.currentResearchNode != null)
            setViewedResearch(GuiResearchManagerConstants.currentResearchNode)
    }

    /**
      * Render research backgrounds, slot and items
      */
    override def render(guiLeft: Int, guiTop: Int, mouseX: Int, mouseY: Int): Unit = {
        // Backgrounds
        GlStateManager.pushAttrib()

        backgroundList.render(guiLeft, guiTop, mouseX, mouseY)
        backgroundRequired.render(guiLeft, guiTop, mouseX, mouseY)
        backgroundRequiredTitle.render(guiLeft, guiTop, mouseX, mouseY)
        backgroundTree.render(guiLeft, guiTop, mouseX, mouseY)
        backgroundTreeTitle.render(guiLeft, guiTop, mouseX, mouseY)

        GlStateManager.popAttrib()

        //List components
        GlStateManager.pushMatrix()
        GlStateManager.pushAttrib()

        searchBar.render(guiLeft, guiTop, mouseX, mouseY)
        listScrollBar.render(guiLeft, guiTop, mouseX, mouseY)

        for(z <- 0 until MAX_SIZE_LIST) {
            if(visibleNodes.size() > z &&
                    currentButtons(z) != null) {
                GlStateManager.pushMatrix()
                GlStateManager.pushAttrib()
                currentButtons(z).render(guiLeft, guiTop, mouseX, mouseY)
                GlStateManager.popAttrib()
                GlStateManager.popMatrix()
            }
        }

        GlStateManager.popAttrib()
        GlStateManager.popMatrix()

        // Required Components
        GlStateManager.pushMatrix()
        GlStateManager.pushAttrib()

        requiredTitle.render(guiLeft, guiTop, mouseX, mouseY)

        if(requiredButtons != null)
            for(button <- requiredButtons)
                button.render(guiLeft, guiTop, mouseX, mouseY)

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

        for(z <- 0 until MAX_SIZE_LIST) {
            if(visibleNodes.size() > z && currentButtons(z) != null) {
                GlStateManager.pushMatrix()
                GlStateManager.pushAttrib()

                // Pop out text so we can manipulate it
                val string = currentButtons(z).label
                var color = ""

                // Color the label
                ResearchManager.getResearchStatus(player, string) match {
                    case Status.ABLE => color = GuiColor.GREEN + ""
                    case Status.NOT_ABLE => color = GuiColor.GRAY + ""
                    case Status.COMPLETED => color = GuiColor.BLUE + ""
                    case _ =>
                }

                val size: Int = Minecraft.getMinecraft.fontRendererObj.getStringWidth(string)
                var scale : Float = 1.0F
                val maxWidth = width - 20

                if(size > maxWidth) {
                    val different : Float = size - maxWidth
                    scale = 1.0F - (different / size.toFloat)
                }

                if(scale == 0)
                    scale = 0.01F

                GL11.glScaled(scale, scale, 0)
                GL11.glTranslated((currentButtons(z).xPos + (currentButtons(z).width / 2 - (size * scale) / 2)) / scale,
                    (currentButtons(z).yPos + 4 + ((1 - scale) * 3 / 1.0)) / scale, 0)
                RenderUtils.prepareRenderState()
                Minecraft.getMinecraft.fontRendererObj.drawString(color + string, 0, 0, 0xFFFFFF)

                GlStateManager.popAttrib()
                GlStateManager.popMatrix()
            }
        }

        GlStateManager.popAttrib()
        GlStateManager.popMatrix()

        // Required Components
        GlStateManager.pushMatrix()
        GlStateManager.pushAttrib()

        if(GuiResearchManagerConstants.currentResearchNode != null) {
            requiredTitle.setText(GuiResearchManagerConstants.currentResearchNode.getTitle)
            requiredTitle.renderOverlay(guiLeft, guiTop, mouseX, mouseY)

            for(i <- 0 until GuiResearchManagerConstants.currentResearchNode.getUnlockables.size) {
                if(i < 16) {
                    val unlock = GuiResearchManagerConstants.currentResearchNode.getUnlockables.get(i)
                    if (getItemStackFromString(unlock) != null) {
                        GlStateManager.pushMatrix()
                        GlStateManager.pushAttrib()

                        RenderHelper.enableGUIStandardItemLighting()

                        RenderUtils.bindGuiComponentsSheet()
                        this.drawTexturedModalRect(102 + (if (i <= 7) i * 18 else (i - 8) * 18),
                            if (i <= 7) 32 else 32 + 18, 0, 20, 18, 18)
                        Minecraft.getMinecraft.getRenderItem.renderItemAndEffectIntoGUI(getItemStackFromString(unlock),
                            103 + (if (i <= 7) i * 18 else (i - 8) * 18),
                            if (i <= 7) 33 else 33 + 18)

                        RenderHelper.disableStandardItemLighting()

                        GlStateManager.popAttrib()
                        GlStateManager.popMatrix()
                        RenderUtils.restoreColor()
                    }
                }
            }

            requiredResearchTitle.renderOverlay(guiLeft, guiTop, mouseX, mouseY)
        }

        if(requiredButtons != null)
            for(button <- requiredButtons) {
                GlStateManager.pushMatrix()
                GlStateManager.pushAttrib()

                // Pop out text so we can manipulate it
                val string = button.label

                val size: Int = Minecraft.getMinecraft.fontRendererObj.getStringWidth(string)
                var scale : Float = 1.0F
                val maxWidth = width - 20

                if(size > maxWidth) {
                    val different : Float = size - maxWidth
                    scale = 1.0F - (different / size.toFloat)
                }

                if(scale == 0)
                    scale = 0.01F

                GL11.glScaled(scale, scale, 0)
                GL11.glTranslated((button.xPos + (button.width / 2 - (size * scale) / 2)) / scale,
                    (button.yPos + 3 + ((1 - scale) * 3 / 1.0)) / scale, 0)
                RenderUtils.prepareRenderState()
                Minecraft.getMinecraft.fontRendererObj.drawString(string, 0, 0, 0xFFFFFF)

                GlStateManager.popAttrib()
                GlStateManager.popMatrix()
            }

        GlStateManager.popAttrib()
        GlStateManager.popMatrix()
    }

    /**
      * Used to set the current viewed node, update all relevant GUI components here
      * @param node The new active node
      */
    def setViewedResearch(node : ResearchNode) : Unit = {
        GuiResearchManagerConstants.currentResearchNode = node

        requiredButtons = new Array[GuiComponentButton](node.getRequirements.size)
        for(i <- 0 until node.getRequirements.size) {
            val require = node.getRequirements.get(i)
            if(require != null)
                requiredButtons(i) = new GuiComponentButton(
                    x + width + - 2 + (if(i % 2 == 0) 4 else 5 + (250 - width - 12) / 2),
                    y + 57 + 14 + ((i / 2) * 15),
                    (250 - width - 12) / 2,
                    14,
                    require) {
                    override def doAction(): Unit = {
                        val newNode = ResearchManager.getNodeByName(label)
                        if(newNode != null)
                            setViewedResearch(newNode)
                    }
                }
        }
    }

    /**
      * Used to update the current viewed nodes
      */
    def updateVisibleNodes() : Unit = {
        //Filter out by text field
        val filterString = searchBar.getTextField.getText.toLowerCase
        // Clear listing
        visibleNodes.clear()

        val startPoint = currentScroll * (ResearchManager.getResearchNodes.size() - 1) / 1.0


        for(i <- startPoint.toInt until ResearchManager.getResearchNodes.size()) {
            // Only run if space is available
            if (visibleNodes.size() < MAX_SIZE_LIST) {
                val node = ResearchManager.getResearchNodes.get(i)

                // If title matches, add it
                if (node.getTitle.toLowerCase.contains(filterString))
                    visibleNodes.add(node)
                else {
                    // Loop unlocks for test
                    var added = false
                    for(i <- 0 until node.getUnlockables.size()) {
                        val unlock = node.getUnlockables.get(i)
                        val item = getItemStackFromString(unlock)
                        if(item != null && !added) {
                            if(item.getDisplayName.toLowerCase.contains(filterString)) {
                                visibleNodes.add(node)
                                added = true
                            }
                        }
                    }
                }
            }
        }

        // Sorting
        visibleNodes.sort(new Comparator[ResearchNode] {
            override def compare(o1: ResearchNode, o2: ResearchNode): Int = {
                val first = ResearchManager.getResearchStatus(player, o1.getTitle)
                val second = ResearchManager.getResearchStatus(player, o2.getTitle)

                first match {
                    case Status.ABLE =>
                        second match {
                            case Status.ABLE => 0
                            case Status.NOT_ABLE => -1
                            case Status.COMPLETED => -2
                            case _ => 0
                        }
                    case Status.NOT_ABLE =>
                        second match {
                            case Status.ABLE => 1
                            case Status.NOT_ABLE => 0
                            case Status.COMPLETED => -1
                            case _ => 0
                        }
                    case Status.COMPLETED =>
                        second match {
                            case Status.ABLE => 2
                            case Status.NOT_ABLE => 1
                            case Status.COMPLETED => 0
                            case _ => 0
                        }
                    case _ => 0
                }
            }
        })

        updateButtons()
    }

    /**
      * Used to update the viewed buttons
      */
    def updateButtons() : Unit = {
        currentButtons = new Array[GuiComponentButton](MAX_SIZE_LIST)
        for(z <- 0 until MAX_SIZE_LIST) {
            if(visibleNodes.size() > z) {
                val node = visibleNodes.get(z)

                currentButtons(z) = new GuiComponentButton(x + 1, y + 17 + (z * 17), width - 16, 16, node.getTitle) {
                    override def doAction(): Unit = {
                        setViewedResearch(node)
                    }
                }

                var toolTip = new ArrayBuffer[String]()
                toolTip += GuiColor.YELLOW +  "Unlocked Items: "
                for(i <- 0 until node.getUnlockables.size()) {
                    val unlock = node.getUnlockables.get(i)
                    val item = getItemStackFromString(unlock)
                    if(item != null) {
                        toolTip += "  -" + GuiColor.BLUE + item.getDisplayName
                    }
                }
                currentButtons(z).setToolTip(toolTip)
            }
        }
    }

    /**
      * Called when the mouse is pressed
      * @param mouseX Mouse X Position
      * @param y Mouse Y Position
      * @param button Mouse Button
      */
    override def mouseDown(mouseX: Int, y: Int, button: Int) {
        searchBar.mouseDown(mouseX, y, button)

        if (listScrollBar.isMouseOver(mouseX, y))
            listScrollBar.mouseDown(mouseX, y, button)

        for (z <- 0 until MAX_SIZE_LIST) {
            if (visibleNodes.size() > z &&
                    currentButtons(z) != null) {
                if(currentButtons(z).isMouseOver(mouseX, y))
                    currentButtons(z).mouseDown(mouseX, y, button)
            }
        }

        if(requiredButtons != null)
            for(require <- requiredButtons) {
                if(require.isMouseOver(mouseX, y))
                    require.mouseDown(mouseX, y, button)
            }

        updateVisibleNodes()
    }

    /**
      * Called when the user drags the component
      * @param x Mouse X Position
      * @param y Mouse Y Position
      * @param button Mouse Button
      * @param time How long
      */
    override def mouseDrag(x: Int, y: Int, button: Int, time: Long) {
        if(listScrollBar.isMouseOver(x, y))
            listScrollBar.mouseDrag(x, y, button, time)
    }

    /**
      * Called when the mouse button is over the component and released
      * @param x Mouse X Position
      * @param y Mouse Y Position
      * @param button Mouse Button
      */
    override def mouseUp(x: Int, y: Int, button: Int) {
        if(listScrollBar.isMouseOver(x, y))
            listScrollBar.mouseUp(x, y, button)
    }

    /**
      * Called to check if mouse is over
      */
    override def isMouseOver(mouseX: Int, mouseY: Int): Boolean = {
        for (z <- 0 until MAX_SIZE_LIST) {
            if (visibleNodes.size() > z &&
                    currentButtons(z) != null) {
                currentButtons(z).isMouseOver(mouseX, mouseY)
            }
        }

        if(requiredButtons != null)
            for(button <- requiredButtons)
                button.isMouseOver(mouseX, mouseY)

        super.isMouseOver(mouseX, mouseY)
    }

    /**
      * Used when a key is pressed
      * @param letter The letter
      * @param keyCode The code
      */
    override def keyTyped(letter: Char, keyCode: Int) {
        searchBar.keyTyped(letter, keyCode)
        updateVisibleNodes()
    }

    /**
      * Render the tooltip if you can
      *
      * @param mouseX Mouse X
      * @param mouseY Mouse Y
      */
    override def renderToolTip(mouseX: Int, mouseY: Int, parent: GuiScreen) {
        for(component <- currentButtons)
            if (component != null && component.isMouseOver(mouseX - xPos - parent.asInstanceOf[GuiBase[_ <: Container]].getGuiLeft,
                mouseY - parent.asInstanceOf[GuiBase[_ <: Container]].getGuiTop)) component.renderToolTip(mouseX, mouseY, parent)
        super.renderToolTip(mouseX, mouseY, parent)
    }

    override def getWidth: Int = 250

    override def getHeight: Int = 175

    /**
      * Used to get the string form of an ItemStack
      *
      * @param itemStack The stack to translate
      * @return A string version of the stack in format MODID:ITEMID:META
      */
    def getItemStackString(itemStack: ItemStack): String = {
        itemStack.getItem.getRegistryName.toString + ":" + itemStack.getItemDamage
    }

    /**
      * Used to get a stack from a string
      *
      * @param item The item string in format MODID:ITEMID:META
      * @return The stack for the string
      */
    def getItemStackFromString(item: String): ItemStack = {
        val name: Array[String] = item.split(":")
        name.length match {
            case 3 =>
                if (item == "")
                    null
                else
                    new ItemStack(GameRegistry.findItem(name(0), name(1)), 1, Integer.valueOf(name(2)))
            case 2 =>
                if(item == "")
                    null
                else
                    new ItemStack(GameRegistry.findItem(name(0), name(1)), 1)
            case _ => null
        }
    }
}
