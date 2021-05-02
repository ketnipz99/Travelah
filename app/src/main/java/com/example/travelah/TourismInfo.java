package com.example.travelah;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TourismInfo {

    public int id;
    public String title, desc_1, desc_2, url;
    private Bitmap image_1, image_2;

    public TourismInfo() {}

    public TourismInfo (int id, String title, String desc_1, String desc_2, Bitmap image_1, Bitmap image_2, String url) {

        this.id = id;
        this.title = title;
        this.desc_1 = desc_1;
        this.desc_2 = desc_2;
        this.image_1 = image_1;
        this.image_2 = image_2;
        this.url = url;
    }

    //Getter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc_1() {
        return desc_1;
    }

    public String getDesc_2() {
        return desc_2;
    }

    public Bitmap getImage_1() {
        return image_1;
    }

    public Bitmap getImage_2() {
        return image_2;
    }

    public String getUrl() {
        return url;
    }

    //Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc_1(String desc_1) {
        this.desc_1 = desc_1;
    }

    public void setDesc_2(String desc_2) {
        this.desc_2 = desc_2;
    }

    public void setImage_1(byte[] image_1_blob) {
        byte[] bytes_1 = image_1_blob;
        this.image_1 = BitmapFactory.decodeByteArray(bytes_1, 0, bytes_1.length);
    }

    public void setImage_2(byte[] image_2_blob) {
        byte[] bytes_2 = image_2_blob;
        this.image_2 = BitmapFactory.decodeByteArray(bytes_2, 0, bytes_2.length);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
