package me.jrose;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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

    public void inputTraits(File imageSourceDirectory) throws IOException {
        File specs = new File( imageSourceDirectory.getAbsoluteFile() + "\\specs.txt");
        BufferedReader reader = new BufferedReader(new FileReader(specs));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] traitCategoryAndNameDistList = line.split(":");
            String[] nameDashDistList = traitCategoryAndNameDistList[1].split(",");
            for (int i = 0; i < nameDashDistList.length; i++) {
                String[] nameDistributionPair = nameDashDistList[i].split("-");

                switch(traitCategoryAndNameDistList[0]){
                    case "Background":
                        backgroundTraits.add(new Trait(TraitType.BACKGROUND, Integer.parseInt(nameDistributionPair[1]),
                                false, new File(imageSourceDirectory.getAbsoluteFile() +
                                "\\Background\\" + nameDistributionPair[0]), nameDistributionPair[0]));
                        break;
                    case "Skin":
                        skinTraits.add(new Trait(TraitType.SKIN, Integer.parseInt(nameDistributionPair[1]),
                                false, new File(imageSourceDirectory.getAbsoluteFile() +
                                "\\Skin\\" + nameDistributionPair[0]), nameDistributionPair[0]));
                        break;
                    case "Base":
                        baseTraits.add(new Trait(TraitType.BASE, Integer.parseInt(nameDistributionPair[1]),
                                false, new File(imageSourceDirectory.getAbsoluteFile() +
                                "\\Base\\" + nameDistributionPair[0]), nameDistributionPair[0]));
                        break;
                    case "Body":
                        bodyTraits.add(new Trait(TraitType.BODY, Integer.parseInt(nameDistributionPair[1]),
                                false, new File(imageSourceDirectory.getAbsoluteFile() +
                                "\\Body\\" + nameDistributionPair[0]), nameDistributionPair[0]));
                        break;
                    case "Arms":
                        armsTraits.add(new Trait(TraitType.ARMS, Integer.parseInt(nameDistributionPair[1]),
                                true, new File(imageSourceDirectory.getAbsoluteFile() +
                                "\\Arms\\" + nameDistributionPair[0]), nameDistributionPair[0]));
                        break;
                    case "Mouth":
                        mouthTraits.add(new Trait(TraitType.MOUTH, Integer.parseInt(nameDistributionPair[1]),
                                false, new File(imageSourceDirectory.getAbsoluteFile() +
                                "\\Mouth\\" + nameDistributionPair[0]), nameDistributionPair[0]));
                        break;
                    case "Head":
                        headTraits.add(new Trait(TraitType.HEAD, Integer.parseInt(nameDistributionPair[1]),
                                false, new File(imageSourceDirectory.getAbsoluteFile() +
                                "\\Head\\" + nameDistributionPair[0]), nameDistributionPair[0]));
                        break;
                    case "Eyes":
                        eyesTraits.add(new Trait(TraitType.EYES, Integer.parseInt(nameDistributionPair[1]),
                                false, new File(imageSourceDirectory.getAbsoluteFile() +
                                "\\Eyes\\" + nameDistributionPair[0]), nameDistributionPair[0]));
                        break;
                    default:
                        throw new RuntimeException("Unhandled Category type in TraitPool constructor: " + traitCategoryAndNameDistList[0]);
                }

            }
        }
    }

    public void buildNftFrames(String buildId, String destinationDirectory, int numAnimationFrames, int width, int height) throws IOException {
        String[] traitIds = buildId.split("-");
        File outputDirectory = new File(destinationDirectory + buildId);
        outputDirectory.mkdirs();
        for (int i = 0; i < numAnimationFrames; i++) {
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics graphics = outputImage.getGraphics();
            for (int j = 0; j < traitIds.length; j++) {

                BufferedImage sourceImage = ImageIO.read(new File(
                        //path here
                        getTraitById(Integer.parseInt(traitIds[j])).getTraitFolder().getAbsolutePath() +
                                //ternary to add subfolder here if skin color dependent
                                (getTraitById(Integer.parseInt(traitIds[j])).isSkinColorDependent() ? "\\" + getTraitById(Integer.parseInt(traitIds[1])).getName() : "")
                        ,
                        //image name here
                        getTraitById(Integer.parseInt(traitIds[j])).getName() +
                                ////ternary to add skin color to name here if skin color dependent
                                (getTraitById(Integer.parseInt(traitIds[j])).isSkinColorDependent() ? getTraitById(Integer.parseInt(traitIds[1])).getName() : "") +
                                //ternary to handle crazy file names for animation frames
                                (i<9 ? "000" : "00") + (i+1) + ".png"
                ));
                graphics.drawImage(sourceImage, 0, 0, null);
            }
            graphics.dispose();
            ImageIO.write(outputImage, "PNG", new File(outputDirectory, i +".png"));
        }
    }

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

//    public void addTrait(Trait trait) {
//        switch (trait.getTraitType().ordinal()){
//            case 0:
//                backgroundTraits.add(trait);
//                break;
//            case 1:
//                skinTraits.add(trait);
//                break;
//            case 2:
//                baseTraits.add(trait);
//                break;
//            case 3:
//                bodyTraits.add(trait);
//                break;
//            case 4:
//                armsTraits.add(trait);
//                break;
//            case 5:
//                mouthTraits.add(trait);
//                break;
//            case 6:
//                headTraits.add(trait);
//                break;
//            case 7:
//                eyesTraits.add(trait);
//                break;
//            default:
//                throw new IllegalArgumentException("Trait not a valid type");
//        }
//    }

}
