package com.example.uploadimagesqlite;

import android.graphics.Bitmap;

public class ModalClass {
    private String image_name;
    private Bitmap image;

    public ModalClass(String image_name,  Bitmap image) {
        this.image_name = image_name;
        this.image = image;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
