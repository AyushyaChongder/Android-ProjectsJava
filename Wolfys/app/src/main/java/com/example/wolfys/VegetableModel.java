package com.example.wolfys;

public class VegetableModel {
    private int id;
    private String title;
    private int imgId;
    private boolean isSelected;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImgId() {
        return imgId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public VegetableModel(int id, String title, int imgId) {
        this.id = id;
        this.title = title;
        this.imgId = imgId;
        this.isSelected = false;
    }
}
