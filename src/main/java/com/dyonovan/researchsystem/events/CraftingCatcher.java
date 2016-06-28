package com.dyonovan.researchsystem.events;

import com.dyonovan.researchsystem.managers.ResearchManager;
import com.google.common.base.Throwables;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/27/2016
 */
public class CraftingCatcher implements IRecipe {

    private IRecipe stack;

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        EntityPlayer player = findPlayer(inv);
        if (player == null) return false;
        for (IRecipe recipe: ResearchManager.getRemovedRecipes()) {
            stack = recipe;
            if (recipe.matches(inv, world)) {
                //TODO Check if its locked
                return true;
            }
        }
        return false;
    }

    @Nullable
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return (stack == null) ? null : stack.getCraftingResult(inv);
    }

    @Override
    public int getRecipeSize() {
        return (stack == null) ? 0 : stack.getRecipeSize();
    }

    @Nullable
    @Override
    public ItemStack getRecipeOutput() {
        return (stack == null) ? null : stack.getRecipeOutput();
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        return stack.getRemainingItems(inv);
    }

    private static final Field eventHandlerField = ReflectionHelper.findField(InventoryCrafting.class, "eventHandler");
    private static final Field containerPlayerPlayerField = ReflectionHelper.findField(ContainerPlayer.class, "thePlayer");
    private static final Field slotCraftingPlayerField = ReflectionHelper.findField(SlotCrafting.class, "thePlayer");


    private static EntityPlayer findPlayer(InventoryCrafting inv) {
        try {
            Container container = (Container) eventHandlerField.get(inv);
            if (container instanceof ContainerPlayer) {
                return (EntityPlayer) containerPlayerPlayerField.get(container);
            } else if (container instanceof ContainerWorkbench) {
                return (EntityPlayer) slotCraftingPlayerField.get(container.getSlot(0));
            } else {
                // don't know the player
                return null;
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}
