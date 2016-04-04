package ru.mdh.android.model;


import android.graphics.Bitmap;

public class Photo {
    Bitmap photo;
    String contactID;


    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }
}
