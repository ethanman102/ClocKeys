package com.example.clockeys.Notifications;

import com.example.clockeys.Users.Employee;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Date;

public abstract class Notification {
    private int id;
    private int companyId;
    private int urgency;
    private int posterId;
    private String posterName;
    private String title;
    private Date postDate;


    public static class NotificationComparator<T extends Notification> implements Comparator<Notification>{
        private String sortType;

        public NotificationComparator(){
            this("date");
        }

        public NotificationComparator(String sortType){
            this.sortType = sortType;
        }
        @Override
        public int compare(Notification o1, Notification o2) {
            if (sortType.equalsIgnoreCase("urgency")){
                return Integer.compare(o1.urgency, o2.urgency);
            }else if (sortType.equalsIgnoreCase("date")){
                return o1.postDate.compareTo(o2.postDate);
            }else{
                return o1.postDate.compareTo(o2.postDate); // default will be to sort by posting date!
            }
        }
    }

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

    public abstract NotificationType getType();

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

    public int getPosterId() {
        return this.posterId;
    }

    public String getPosterName() {
        return this.posterName;
    }

    public String getTitle(){
        return this.title;
    }



}
