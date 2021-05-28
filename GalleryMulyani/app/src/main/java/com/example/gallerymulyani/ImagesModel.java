package com.example.gallerymulyani;

import java.util.ArrayList;

public class ImagesModel {

    private String folderName;
//    ArrayList<String> arrayList_ImagePath;
    String stringImagePath;
    private String firstPic;
    private int numberOfPics = 0;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String stringFolder) {
        this.folderName = stringFolder;
    }

//    public ArrayList<String> getArrayList_ImagePath() {
//        return arrayList_ImagePath;
//    }
//
//    public void setArrayList_ImagePath(ArrayList<String> arrayList_ImagePath) {
//        this.arrayList_ImagePath = arrayList_ImagePath;
//    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public int getNumberOfPics() {
        return numberOfPics;
    }

    public void setNumberOfPics(int numberOfPics) {
        this.numberOfPics = numberOfPics;
    }

    public String getStringImagePath() {
        return stringImagePath;
    }

    public void setStringImagePath(String stringImagePath) {
        this.stringImagePath = stringImagePath;
    }

    public void addPics(){
        this.numberOfPics++;
    }

    //    private String path;
//    private String folderName;
//    private int numberOfPics = 0;
//    private String firstPic;
//
//    public ImagesModel(){
//
//    }
//
//    public ImagesModel(String path, String folderName){
//
//        this.path = path;
//        this.folderName = folderName;
//
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public String getFolderName() {
//        return folderName;
//    }
//
//    public void setFolderName(String folderName) {
//        this.folderName = folderName;
//    }
//
//    public int getNumberOfPics() {
//        return numberOfPics;
//    }
//
//    public void setNumberOfPics(int numberOfPics) {
//        this.numberOfPics = numberOfPics;
//    }
//
//    public String getFirstPic() {
//        return firstPic;
//    }
//
//    public void setFirstPic(String firstPic) {
//        this.firstPic = firstPic;
//    }
//
//    // addpics
//    public void addPics(){
//        this.numberOfPics++;
//    }
}
