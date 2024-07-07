package com.example.clockeys.Notifications;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Notification {
    private int id;
    private int companyId;
    private int urgency;
    private int posterId;
    private String posterName;
    private String title;
    private Date postDate;



    public Notification(){
    }

    public Notification(int id, int companyId, String title, Date postDate, int urgency,int posterId, String posterName){
        this.id = id;
        this.companyId = companyId;
        this.title = title;
        this.postDate = postDate;
        this.posterId = posterId;
        this.posterName = posterName;

        // Setting the urgency, Urgency should be a number from 0 - 10 (11 choices)

        if (urgency < 0) this.urgency = 0;
        else if (urgency > 10) this.urgency = 10;
        else this.urgency = urgency;
    }


    public String timeElapsed(){
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        Date currentTime = Date.from(zonedDateTime.toInstant());

        int hoursPassed = (int) ((currentTime.getTime() - postDate.getTime()) / 1000/60/60);
        return Integer.toString(hoursPassed);
    }

    public int getId(){
        return this.id;
    }

    public int getCompanyId(){
        return this.companyId;
    }

    public Date getPostDate(){
        return this.postDate;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }



}
