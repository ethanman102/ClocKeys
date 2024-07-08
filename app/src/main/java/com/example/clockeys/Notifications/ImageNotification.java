package com.example.clockeys.Notifications;

import android.net.Uri;

import java.util.Date;

public class ImageNotification extends Notification{
    private String text;
    private String imageUrl;

    public ImageNotification(int id, int companyId, String title, Date postDate, int urgency, int posterId, String posterName, String text, String imageUrl) {
        super(id, companyId, title, postDate, urgency, posterId, posterName);
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public Uri getImage(){
        return Uri.EMPTY;
    }

    public String getText(){
        return this.text;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.IMAGE;
    }
}
