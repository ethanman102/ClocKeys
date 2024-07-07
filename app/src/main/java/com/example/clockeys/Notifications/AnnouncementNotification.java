package com.example.clockeys.Notifications;

import java.util.Date;

public class AnnouncementNotification extends Notification{

    private String announcement;

    public AnnouncementNotification(int id, int companyId, String title, Date postDate, int urgency,int posterId,String posterName, String announcement) {
        super(id, companyId, title, postDate, urgency,posterId,posterName);
        this.announcement = announcement;
    }

    public String getAnnouncement() {
        return this.announcement;
    }
}
