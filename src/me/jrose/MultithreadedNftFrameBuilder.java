package me.jrose;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MultithreadedNftFrameBuilder implements Runnable{
    private TraitPool traitPool;
    private String buildId;
    private String destinationDirectory;
    private String destinationGifDirectory;
    private int numAnimationFrames;
    private int width;
    private int height;

    public MultithreadedNftFrameBuilder(TraitPool traitPool, String buildId, String destinationDirectory,
                                        String destinationGifDirectory, int numAnimationFrames, int width, int height) {
        this.traitPool = traitPool;
        this.buildId = buildId;
        this.destinationDirectory = destinationDirectory;
        this.destinationGifDirectory = destinationGifDirectory;
        this.numAnimationFrames = numAnimationFrames;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        String[] traitIds = buildId.split("-");

        //make a folder that matches the buildId for each unique complete build where we put the assembled frames
//        File outputDirectory = new File(destinationDirectory + buildId);
//        outputDirectory.mkdirs();


        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(destinationGifDirectory + "\\" + buildId + ".gif");
//        encoder.setFrameRate(150);
        encoder.setFrameRate(9);
        encoder.setRepeat(0);

        for (int i = 0; i < numAnimationFrames; i++) {

//            System.out.println("starting outside loop of buildNftFrames");
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics graphics = outputImage.getGraphics();

            //iteration for each
            for (int j = 0; j < traitIds.length; j++) {

//                System.out.println("starting inside loop of buildNftFrames");
                BufferedImage sourceImage = null;
                try {
                    sourceImage = ImageIO.read(new File(
                            //path here
                            traitPool.getTraitById(Integer.parseInt(traitIds[j])).getTraitFolder().getAbsolutePath() +
                                    //ternary to add subfolder here if skin color dependent
                                    (traitPool.getTraitById(Integer.parseInt(traitIds[j])).isSkinColorDependent() ? "\\" + traitPool.getTraitById(Integer.parseInt(traitIds[1])).getName() : "")
                            ,
                            //image name here
                            traitPool.getTraitById(Integer.parseInt(traitIds[j])).getName() +
                                    ////ternary to add skin color to name here if skin color dependent
                                    (traitPool.getTraitById(Integer.parseInt(traitIds[j])).isSkinColorDependent() ? traitPool.getTraitById(Integer.parseInt(traitIds[1])).getName() : "") +
                                    //ternary to handle crazy file names for animation frames received from Krita (image processing)
                                    (i<9 ? "000" : "00") + (i+1) + ".png"
                    ));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                graphics.drawImage(sourceImage, 0, 0, null);
            }
            graphics.dispose();
            try {
//                ImageIO.write(outputImage, "PNG", new File(outputDirectory, i +".png"));
                encoder.addFrame(outputImage);
//            } catch (IOException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        encoder.finish();
    }
}
