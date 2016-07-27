package com.dyonovan.researchsystem.collections;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This file was created for ResearchSystem
 * <p>
 * ResearchSystem is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 7/27/2016
 */
public class GroupResearch {

    private UUID groupUUID;
    private ArrayList<String> unlockedResearch;

    public GroupResearch(UUID groupUUID, ArrayList<String> unlockedResearch) {
        this.groupUUID = groupUUID;
        this.unlockedResearch = unlockedResearch;
    }

    public UUID getGroupUUID() {
        return groupUUID;
    }

    public ArrayList<String> getUnlockedResearch() {
        return unlockedResearch;
    }
}
