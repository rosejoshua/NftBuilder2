package me.jrose;

import java.util.ArrayList;
import java.util.HashSet;

public class DistributedTraitPool {
    private int totalBackgroundTraits = 0;
    private int totalSkinTraits = 0;
    private int totalBaseTraits = 0;
    private int totalBodyTraits = 0;
    private int totalArmsTraits = 0;
    private int totalMouthTraits = 0;
    private int totalHeadTraits = 0;
    private int totalEyesTraits = 0;

    private final int numberOfTraitCategories = 8;

    private ArrayList<Integer> backgroundTraitIdsDistributed = new ArrayList<>();
    private ArrayList<Integer> skinTraitIdsDistributed = new ArrayList<>();
    private ArrayList<Integer> baseTraitIdsDistributed = new ArrayList<>();
    private ArrayList<Integer> bodyTraitIdsDistributed = new ArrayList<>();
    private ArrayList<Integer> armsTraitIdsDistributed = new ArrayList<>();
    private ArrayList<Integer> mouthTraitIdsDistributed = new ArrayList<>();
    private ArrayList<Integer> headTraitIdsDistributed = new ArrayList<>();
    private ArrayList<Integer> eyesTraitIdsDistributed = new ArrayList<>();

    public String createBuildIds(int numberOfBuilds) {
        int numBuildFailsDueToDuplication = 0;
        while (usedBuildIds.size()<numberOfBuilds) {
            String buildId = "";
            int[] randomIndexesArray = new int[8];
            randomIndexesArray[0] = (int)(Math.random() * backgroundTraitIdsDistributed.size());
            randomIndexesArray[1] = (int)(Math.random() * skinTraitIdsDistributed.size());
            randomIndexesArray[2] = (int)(Math.random() * baseTraitIdsDistributed.size());
            randomIndexesArray[3] = (int)(Math.random() * bodyTraitIdsDistributed.size());
            randomIndexesArray[4] = (int)(Math.random() * armsTraitIdsDistributed.size());
            randomIndexesArray[5] = (int)(Math.random() * mouthTraitIdsDistributed.size());
            randomIndexesArray[6] = (int)(Math.random() * headTraitIdsDistributed.size());
            randomIndexesArray[7] = (int)(Math.random() * eyesTraitIdsDistributed.size());

            buildId += (int) backgroundTraitIdsDistributed.get(randomIndexesArray[0]);
            buildId += "-";
            buildId += (int) skinTraitIdsDistributed.get(randomIndexesArray[1]);
            buildId += "-";
            buildId += (int) baseTraitIdsDistributed.get(randomIndexesArray[2]);
            buildId += "-";
            buildId += (int) bodyTraitIdsDistributed.get(randomIndexesArray[3]);
            buildId += "-";
            buildId += (int) armsTraitIdsDistributed.get(randomIndexesArray[4]);
            buildId += "-";
            buildId += (int)mouthTraitIdsDistributed.get(randomIndexesArray[5]);
            buildId += "-";
            buildId += (int) headTraitIdsDistributed.get(randomIndexesArray[6]);
            buildId += "-";
            buildId += (int) eyesTraitIdsDistributed.get(randomIndexesArray[7]);

            if (usedBuildIds.add(buildId)){
                backgroundTraitIdsDistributed.remove(randomIndexesArray[0]);
                skinTraitIdsDistributed.remove(randomIndexesArray[1]);
                baseTraitIdsDistributed.remove(randomIndexesArray[2]);
                bodyTraitIdsDistributed.remove(randomIndexesArray[3]);
                armsTraitIdsDistributed.remove(randomIndexesArray[4]);
                mouthTraitIdsDistributed.remove(randomIndexesArray[5]);
                headTraitIdsDistributed.remove(randomIndexesArray[6]);
                eyesTraitIdsDistributed.remove(randomIndexesArray[7]);
            }
            else {
                numBuildFailsDueToDuplication++;
            }
        }
        return "Creation of " + numberOfBuilds + " build IDs complete. Wasted cycles " +
                "due to duplicate build IDs = " + numBuildFailsDueToDuplication;
    }

    private HashSet<String> usedBuildIds = new HashSet<>();

    public HashSet<String> getUsedBuildIds() {
        if (usedBuildIds.size() == 0)
            throw new RuntimeException("Error: trying to retrieve build IDs before calling" +
                    " createBuildIds()");
        else return usedBuildIds;
    }

    public void feedInTraitPool(TraitPool traitPool) {
        for (Trait trait : traitPool.getBackgroundTraits()) {
            for (int i = 0; i < trait.getNumberOfDistributions(); i++) {
                backgroundTraitIdsDistributed.add(trait.getTraitId());
            }
        }
        for (Trait trait : traitPool.getSkinTraits()) {
            for (int i = 0; i < trait.getNumberOfDistributions(); i++) {
                skinTraitIdsDistributed.add(trait.getTraitId());
            }
        }
        for (Trait trait : traitPool.getBaseTraits()) {
            for (int i = 0; i < trait.getNumberOfDistributions(); i++) {
                baseTraitIdsDistributed.add(trait.getTraitId());
            }
        }
        for (Trait trait : traitPool.getBodyTraits()) {
            for (int i = 0; i < trait.getNumberOfDistributions(); i++) {
                bodyTraitIdsDistributed.add(trait.getTraitId());
            }
        }
        for (Trait trait : traitPool.getArmsTraits()) {
            for (int i = 0; i < trait.getNumberOfDistributions(); i++) {
                armsTraitIdsDistributed.add(trait.getTraitId());
            }
        }
        for (Trait trait : traitPool.getMouthTraits()) {
            for (int i = 0; i < trait.getNumberOfDistributions(); i++) {
                mouthTraitIdsDistributed.add(trait.getTraitId());
            }
        }
        for (Trait trait : traitPool.getHeadTraits()) {
            for (int i = 0; i < trait.getNumberOfDistributions(); i++) {
                headTraitIdsDistributed.add(trait.getTraitId());
            }
        }
        for (Trait trait : traitPool.getEyesTraits()) {
            for (int i = 0; i < trait.getNumberOfDistributions(); i++) {
                eyesTraitIdsDistributed.add(trait.getTraitId());
            }
        }
    }

//    public void addBackgroundTrait(int intId) {
//        backgroundTraitIdsDistributed.add(intId);
//        totalBackgroundTraits++;
//    }
//    public void addSkinTrait(int intId) {
//        skinTraitIdsDistributed.add(intId);
//        totalSkinTraits++;
//    }
//    public void addBaseTrait(int intId) {
//        baseTraitIdsDistributed.add(intId);
//        totalBaseTraits++;
//    }
//    public void addBodyTrait(int intId) {
//        bodyTraitIdsDistributed.add(intId);
//        totalBodyTraits++;
//    }
//    public void addArmsTrait(int intId) {
//        armsTraitIdsDistributed.add(intId);
//        totalArmsTraits++;
//    }
//    public void addMouthTrait(int intId) {
//        mouthTraitIdsDistributed.add(intId);
//        totalMouthTraits++;
//    }
//    public void addHeadTrait(int intId) {
//        headTraitIdsDistributed.add(intId);
//        totalHeadTraits++;
//    }
//    public void addEyesTrait(int intId) {
//        eyesTraitIdsDistributed.add(intId);
//        totalEyesTraits++;
//    }

}
