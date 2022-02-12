package me.jrose;

public enum TraitType {
    BACKGROUND, SKIN, BASE, BODY, ARMS, MOUTH, HEAD, EYES, ERROR;

    //returns number of useful TraitTypes, not including Error
    public static int getNumberOfTraitTypes(){
        return ERROR.ordinal();
    }
}
