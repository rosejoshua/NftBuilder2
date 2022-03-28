package me.jrose;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String sourceDirectoryString = "D:\\resource\\nft\\";
        String destinationDirectoryString = "D:\\output\\";
        String destinationGifDirectoryString = "D:\\output\\gifs";
        int numToMake = 2250;
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

        File destinationGifDirectory = new File(destinationGifDirectoryString);
        if (!destinationGifDirectory.exists()){
            destinationGifDirectory.mkdir();
        }

        TraitPool traitPool = new TraitPool();
        traitPool.inputTraits(imageSourceDirectory);

        DistributedTraitPool distributedTraitPool = new DistributedTraitPool();
        distributedTraitPool.feedInTraitPool(traitPool);
        distributedTraitPool.createBuildIds(numToMake);

        String[] buildIds = new String[distributedTraitPool.getUsedBuildIds().size()];
        distributedTraitPool.getUsedBuildIds().toArray(buildIds);

        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);

        for (int i = 0; i < buildIds.length; i++) {
            //single threaded version:
            //traitPool.buildNftFrames(buildIds[i], destinationDirectoryString, numAnimationFrames, width, height);

            //multi-threaded version:
            executorService.execute(new MultithreadedNftFrameBuilder(traitPool, buildIds[i],
                    destinationDirectoryString, destinationGifDirectoryString, numAnimationFrames, width, height));
            if (i % 50 == 0)
                System.out.println("Number of Builds Processed: " + i);
        }

        executorService.shutdown();

    }
}
