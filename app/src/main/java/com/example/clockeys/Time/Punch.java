package com.example.clockeys.Time;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

public class Punch {
    private String stringDate;
    private Date punchIn;
    private Date punchOut;


    public Punch(){
    }

    public Punch(LocalDateTime punchIn){
        ZonedDateTime zonedDateTime = punchIn.atZone(ZoneId.systemDefault());
        this.punchIn = Date.from(zonedDateTime.toInstant());
        this.stringDate = new SimpleDateFormat("MMMM dd yyyy",Locale.getDefault()).format(this.punchIn);
    }

    public void createPunchOut(LocalDateTime punchOut){
        ZonedDateTime zonedDateTime = punchOut.atZone(ZoneId.systemDefault());
        this.punchOut = Date.from(zonedDateTime.toInstant());
    }


    public long punchTime(){
        if (punchOut == null) return 0;
        else return (long) Math.ceil(((punchOut.getTime() - punchIn.getTime()) / 1000));
    }

    public String getStringDate(){
        return this.stringDate;
    }

    public Date getPunchOut(){
        return this.punchOut;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        // Create AM and PM date format
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());

        sb.append(sdf.format(this.punchIn));
        sb.append(" - ");
        sb.append(this.punchOut == null ? "" : sdf.format(this.punchOut));

        return sb.toString();
    }

}
