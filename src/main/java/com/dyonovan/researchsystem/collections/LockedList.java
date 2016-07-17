package com.dyonovan.researchsystem.collections;

import java.util.ArrayList;

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
public class LockedList {

    private String name;
    private ArrayList<String> blockList;
    private String requires;
    private int tier1, tier2, tier3, tier4;

    public LockedList(String name, ArrayList<String> blockList, String requires, int tier1, int tier2, int tier3, int tier4) {
        this.name = name;
        this.blockList = blockList;
        this.requires = requires;
        this.tier1 = tier1;
        this.tier2 = tier2;
        this.tier3 = tier3;
        this.tier4 = tier4;
    }

    public ArrayList<String> getBlockList() {
        return blockList;
    }

    public String getRequires() {
        return requires;
    }

    public int getTier1() {
        return tier1;
    }

    public int getTier2() {
        return tier2;
    }

    public int getTier3() {
        return tier3;
    }

    public int getTier4() {
        return tier4;
    }

    public String getName() {
        return name;
    }
}
