package me.jrose;

import java.io.File;

public class Trait {
    private final TraitType traitType;
    private final int numberOfDistributions;
    private final int traitId;
    private final boolean skinColorDependent;
    private static int nextTraitId = 0;
    private final File traitFolder;
    private final String name;

    public Trait(TraitType traitType, int numberOfDistributions, boolean skinColorDependent, File traitFolder, String name) {
        this.name = name;
        if (!traitFolder.exists()) {
            throw new RuntimeException("Attempted to create Trait with File: " +
                    traitFolder.getAbsolutePath());
        }

        this.traitType = traitType;
        this.numberOfDistributions = numberOfDistributions;
        this.skinColorDependent = skinColorDependent;
        this.traitFolder = traitFolder;
        this.traitId = nextTraitId;
        nextTraitId++;
    }

    public int getTraitId() {
        return traitId;
    }

    public File getTraitFolder() {
        return traitFolder;
    }

    public boolean isSkinColorDependent() {
        return skinColorDependent;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfDistributions() {
        return numberOfDistributions;
    }

    public TraitType getTraitType() {
        return traitType;
    }

    public int getLayerPriority(){
        return traitType.ordinal();
    }
}
