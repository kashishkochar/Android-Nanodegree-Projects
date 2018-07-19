package com.example.android.tourism;

/**
 * Created by kashish on 3/26/2018.
 */

public class tour {

    private String textPart,textPart2;
    private int imageId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    public tour(String text1, String text2, int image){

        textPart=text1;
        textPart2=text2;
        imageId=image;

    }

    public String getTextPart1(){

        return textPart;
    }
    public String getTextPart2(){

        return textPart2;
    }

    public int getImageId(){

        return imageId;
    }
    public boolean hasImage(){

        return imageId != NO_IMAGE_PROVIDED;
    }
}
