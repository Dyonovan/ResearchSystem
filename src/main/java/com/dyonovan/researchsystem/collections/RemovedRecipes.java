package com.dyonovan.researchsystem.collections;

import net.minecraft.item.crafting.IRecipe;

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
public class RemovedRecipes {

    private String name;
    private IRecipe recipe;

    public RemovedRecipes(String name, IRecipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public IRecipe getRecipe() {
        return recipe;
    }
}
