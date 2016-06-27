package com.dyonovan.researchsystem.managers;

import com.dyonovan.researchsystem.ResearchSystem;
import com.dyonovan.researchsystem.collections.LockedList;
import com.dyonovan.researchsystem.lib.Reference;
import com.dyonovan.researchsystem.util.JsonUtils;
import com.dyonovan.researchsystem.util.LogHelper;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

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

    private static ArrayList<LockedList> lockedList = new ArrayList<>();

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
        JsonUtils.writeToJson(lockedList, ResearchSystem.configDir + File.separator + Reference.FileLocs.LOCKED_LISTS + File.separator + "minecraft.json");
    }

    private static boolean loadFromFiles() {
        File dir = new File(ResearchSystem.configDir + File.separator + Reference.FileLocs.LOCKED_LISTS);
        if (!dir.exists()) dir.mkdir();

        FilenameFilter filterJson = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".json");
            }
        };

        File[] files = dir.listFiles(filterJson);
        if (files.length <= 0) return false;

        TypeToken<ArrayList<LockedList>> type = new TypeToken<ArrayList<LockedList>>(){};
        for (File file : files) {
            ArrayList<LockedList> list = JsonUtils.readFromJson(type, file.toString());
            if (list != null && !list.isEmpty()) lockedList.addAll(list); //TODO Check if name already exists
        }
        return lockedList != null && !lockedList.isEmpty();
    }

    public static ArrayList<LockedList> getLockedList() {
        return lockedList;
    }
}
