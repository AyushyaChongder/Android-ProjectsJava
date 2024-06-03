package com.example.mentoring;

public class Model {
    String stuName;
    int stuImage;

    public Model(String stuName, int stuImage) {
        this.stuName = stuName;
        this.stuImage = stuImage;
    }

    public String getStuName() {
        return stuName;
    }

    public int getStuImage() {
        return stuImage;
    }

}
