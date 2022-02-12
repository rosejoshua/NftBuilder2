package me.jrose;

import java.util.ArrayList;

public class TraitPool {
    private ArrayList<Trait> backgroundTraits = new ArrayList<>();
    private ArrayList<Trait> skinTraits = new ArrayList<>();
    private ArrayList<Trait> baseTraits = new ArrayList<>();
    private ArrayList<Trait> bodyTraits = new ArrayList<>();
    private ArrayList<Trait> armsTraits = new ArrayList<>();
    private ArrayList<Trait> mouthTraits = new ArrayList<>();
    private ArrayList<Trait> headTraits = new ArrayList<>();
    private ArrayList<Trait> eyesTraits = new ArrayList<>();

    public ArrayList<Trait> getBackgroundTraits() {
        return backgroundTraits;
    }

    public ArrayList<Trait> getSkinTraits() {
        return skinTraits;
    }

    public ArrayList<Trait> getBaseTraits() {
        return baseTraits;
    }

    public ArrayList<Trait> getBodyTraits() {
        return bodyTraits;
    }

    public ArrayList<Trait> getArmsTraits() {
        return armsTraits;
    }

    public ArrayList<Trait> getMouthTraits() {
        return mouthTraits;
    }

    public ArrayList<Trait> getHeadTraits() {
        return headTraits;
    }

    public ArrayList<Trait> getEyesTraits() {
        return eyesTraits;
    }

    public Trait getTraitById(int id) {
        Trait returnTrait = null;
        while (returnTrait==null) {
            for (Trait trait : backgroundTraits) {
                if (trait.getTraitId() == id)
                    returnTrait = trait;
            }
            for (Trait trait : skinTraits) {
                if (trait.getTraitId() == id)
                    returnTrait = trait;
            }
            for (Trait trait : baseTraits) {
                if (trait.getTraitId() == id)
                    returnTrait = trait;
            }
            for (Trait trait : bodyTraits) {
                if (trait.getTraitId() == id)
                    returnTrait = trait;
            }
            for (Trait trait : armsTraits) {
                if (trait.getTraitId() == id)
                    returnTrait = trait;
            }
            for (Trait trait : mouthTraits) {
                if (trait.getTraitId() == id)
                    returnTrait = trait;
            }
            for (Trait trait : headTraits) {
                if (trait.getTraitId() == id)
                    returnTrait = trait;
            }
            for (Trait trait : eyesTraits) {
                if (trait.getTraitId() == id)
                    returnTrait = trait;
            }
        }
        return returnTrait;
    }

    public void addTrait(Trait trait) {
        switch (trait.getTraitType().ordinal()){
            case 0:
                backgroundTraits.add(trait);
                break;
            case 1:
                skinTraits.add(trait);
                break;
            case 2:
                baseTraits.add(trait);
                break;
            case 3:
                bodyTraits.add(trait);
                break;
            case 4:
                armsTraits.add(trait);
                break;
            case 5:
                mouthTraits.add(trait);
                break;
            case 6:
                headTraits.add(trait);
                break;
            case 7:
                eyesTraits.add(trait);
                break;
            default:
                throw new IllegalArgumentException("Trait not a valid type");
        }
    }

}
