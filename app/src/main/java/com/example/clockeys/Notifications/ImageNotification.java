package com.example.clockeys.Notifications;

import android.net.Uri;

import java.util.Date;

public class ImageNotification extends AnnouncementNotification{

    private String imageUrl;

    public ImageNotification(int id, int companyId, String title, Date postDate, int urgency, String announcement,int posterId,String posterName, String imageUrl) {
        super(id, companyId, title, postDate, urgency,posterId,posterName,announcement);
        this.imageUrl = imageUrl;
    }

    public Uri getImage(){
        return Uri.EMPTY;
    }


}
