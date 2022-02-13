package me.jrose;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        String sourceDirectoryString = "D:\\resource\\nft\\";
        String destinationDirectoryString = "D:\\output\\";
        int numToMake = 2200;
        int numAnimationFrames=15;
        int width = 815;
        int height = 815;

        File imageSourceDirectory = new File(sourceDirectoryString);
        if (!imageSourceDirectory.exists()){
            throw new RuntimeException("Image Source Directory: " + imageSourceDirectory.getAbsolutePath() +
                    " does not exist!");
        }

        File destinationDirectory = new File(destinationDirectoryString);
        if (!destinationDirectory.exists()){
            destinationDirectory.mkdir();
        }

        TraitPool traitPool = new TraitPool();
        traitPool.inputTraits(imageSourceDirectory);

        DistributedTraitPool distributedTraitPool = new DistributedTraitPool();
        distributedTraitPool.feedInTraitPool(traitPool);
        distributedTraitPool.createBuildIds(numToMake);

        String[] buildIds = new String[distributedTraitPool.getUsedBuildIds().size()];
        distributedTraitPool.getUsedBuildIds().toArray(buildIds);

        for (int i = 0; i < buildIds.length; i++) {
            traitPool.buildNftFrames(buildIds[i], destinationDirectoryString, numAnimationFrames, width, height);
            if (i % 50 == 0)
                System.out.println("Number of Builds Processed: " + i);
        }

    }
}
