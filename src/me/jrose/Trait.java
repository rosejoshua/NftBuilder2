package me.jrose;

public class Trait {
    private final TraitType traitType;
    private final int numberOfDistributions;
    private final int traitId;
    private final boolean skinColorDependent;
    private static int nextTraitId = 0;

    public Trait(TraitType traitType, int layerPriority, int numberOfDistributions, SkinColor skinColor, boolean skinColorDependent) {
        this.traitType = traitType;
        this.numberOfDistributions = numberOfDistributions;
        this.skinColorDependent = skinColorDependent;
        this.traitId = nextTraitId;
        nextTraitId++;
    }

    public int getTraitId() {
        return traitId;
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
