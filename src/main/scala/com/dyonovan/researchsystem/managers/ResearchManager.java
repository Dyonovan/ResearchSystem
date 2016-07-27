package com.dyonovan.researchsystem.managers;

import com.dyonovan.researchsystem.ResearchSystem;
import com.dyonovan.researchsystem.capability.ResearchCapability;
import com.dyonovan.researchsystem.collections.GroupResearch;
import com.dyonovan.researchsystem.collections.LockedList;
import com.dyonovan.researchsystem.collections.RemovedRecipes;
import com.dyonovan.researchsystem.lib.Reference;
import com.dyonovan.researchsystem.util.JsonUtils;
import com.dyonovan.researchsystem.util.LogHelper;
import com.google.gson.reflect.TypeToken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

/**
 * This file was created for Research System
 * <p>
 * Research System is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/26/2016
 */
public class ResearchManager {

    private static final ArrayList<LockedList> lockedList = new ArrayList<>();
    private static final ArrayList<RemovedRecipes> removedRecipes = new ArrayList<>();
    private static final ArrayList<GroupResearch> groupResearch = new ArrayList<>();


    public static void init() {
        if (!loadFromFiles())
            generateDefaults();

        LogHelper.info("Research System loaded...");
    }

    private static void generateDefaults() {
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("minecraft:torch", "minecraft:crafting_table"));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("minecraft:bed", "minecraft:cauldron", "minecraft:furnace"));
        lockedList.add(new LockedList("test", list1, "", 20, 0, 0, 0));
        lockedList.add(new LockedList("test1", list2, "test", 20, 20, 0, 0));
        JsonUtils.writeToJson(lockedList, ResearchSystem.configDir() + File.separator + Reference.LOCKED_LISTS() + File.separator + "minecraft.json");
    }

    private static boolean loadFromFiles() {
        File dir = new File(ResearchSystem.configDir() + File.separator + Reference.LOCKED_LISTS());
        if (!dir.exists()) dir.mkdir();

        FilenameFilter filterJson = (dir1, name) -> name.toLowerCase().endsWith(".json");

        File[] files = dir.listFiles(filterJson);
        if (files.length <= 0) return false;

        TypeToken<ArrayList<LockedList>> type = new TypeToken<ArrayList<LockedList>>() {
        };
        for (File file : files) {
            ArrayList<LockedList> list = JsonUtils.readFromJson(type, file.toString());
            if (list != null && !list.isEmpty())
                lockedList.addAll(list); //TODO Add Some checks ie: if name already exists
        }
        return !lockedList.isEmpty();
    }

    public static ArrayList<LockedList> getLockedList() {
        return lockedList;
    }

    public static ArrayList<RemovedRecipes> getRemovedRecipes() {
        return removedRecipes;
    }

    public static ArrayList<GroupResearch> getGroupResearch() {
        return groupResearch;
    }

    public static void removeRecipes() {
        List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();

        for (LockedList list : lockedList) {
            for (String block : list.getBlockList()) {
                for (int i = 0; i < recipeList.size(); i++) {
                    IRecipe recipe = recipeList.get(i);
                    if (recipe.getRecipeOutput() == null) continue;
                    ItemStack item = GameRegistry.makeItemStack(block, 0, 1, null);
                    if (item != null && recipe.getRecipeOutput().isItemEqual(item)) {
                        removedRecipes.add(new RemovedRecipes(list.getName(), recipeList.get(i)));
                        recipeList.remove(i);
                    }
                }
            }
        }
    }

    public static boolean isUnlocked(EntityPlayer player, String research) {
        if (player == null || player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).getGroup() == null)
            return false;

        UUID group = player.getCapability(ResearchCapability.UNLOCKED_RESEARCH, null).getGroup();
        for (GroupResearch gr : groupResearch) {
            if (gr.getGroupUUID() == group) {
                if (gr.getUnlockedResearch().contains(research)) return true;
            }
        }
        return false;
    }

    public static void setUnlocked(UUID group, String research) {
        for (GroupResearch gr : groupResearch) {
            if (gr.getGroupUUID() == group) {
                if (!gr.getUnlockedResearch().contains(research))
                    gr.getUnlockedResearch().add(research);
                return;
            }
        }
        groupResearch.add(new GroupResearch(group, new ArrayList<>(Collections.singletonList(research))));
    }
}
