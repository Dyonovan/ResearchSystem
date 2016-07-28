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
public class ResearchNode {

    private String title;
    private ArrayList<String> unlockables;
    private ArrayList<String> requirements;
    private int tier1, tier2, tier3, tier4;

    /**
     * Creates a research node with given parameters
     * @param title     The display title
     * @param unlocked  What items/blocks to be unlocked
     * @param requires  What research is required
     * @param tier1     Tier 1 count
     * @param tier2     Tier 2 count
     * @param tier3     Tier 3 count
     * @param tier4     Tier 4 count
     */
    public ResearchNode(String title, ArrayList<String> unlocked,  ArrayList<String> requires, int tier1, int tier2, int tier3, int tier4) {
        this.title = title;
        this.unlockables = unlocked;
        this.requirements = requires;
        this.tier1 = tier1;
        this.tier2 = tier2;
        this.tier3 = tier3;
        this.tier4 = tier4;
    }

    /**
     * The title of the node, used for display
     * @return The display title
     */
    public String getTitle() {
        return title;
    }

    /**
     * List of items and blocks unlocked
     * @return List of items/blocks unlocked with this research
     */
    public ArrayList<String> getUnlockables() {
        return unlockables;
    }

    /**
     * List of required research.
     * @return List of required research using the title as the key
     */
    public  ArrayList<String> getRequirements() {
        return requirements;
    }

    /**
     * Tier 1 count
     * @return How many tier one tokens
     */
    public int getTier1() {
        return tier1;
    }

    /**
     * Tier 2 count
     * @return How many tier two tokens
     */
    public int getTier2() {
        return tier2;
    }

    /**
     * Tier 3 count
     * @return How many tier three tokens
     */
    public int getTier3() {
        return tier3;
    }

    /**
     * Tier 4 count
     * @return How many tier four tokens
     */
    public int getTier4() {
        return tier4;
    }
}
