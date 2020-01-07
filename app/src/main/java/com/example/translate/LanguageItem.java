package com.example.translate;

public class LanguageItem {
    private String name;
    private int imageId;

    public LanguageItem(String name, int imageId) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}

