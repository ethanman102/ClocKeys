package com.example.clockeys.Notifications;

import android.net.Uri;

import java.util.Date;
import java.util.List;

public class ImageNotification extends Notification{
    private String text;
    private List<String> imageUrls;

    public ImageNotification(int id, int companyId, String title, Date postDate, int urgency, int posterId, String posterName,Date dismissTime, String text, List<String> imageUrls) {
        super(id, companyId, title, postDate, urgency, posterId, posterName,dismissTime);
        this.text = text;
        this.imageUrls = imageUrls;
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
