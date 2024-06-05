package com.example.clockeys.Time;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Timecard {
    private ArrayList<Punch> clockedHours;


    public Timecard(ArrayList<Punch> clockedHours){
        this.clockedHours = new ArrayList<Punch>(clockedHours);
    }


    public List<Punch> getClockedHours(){
        return Collections.unmodifiableList(clockedHours);
    }


    public long calculateTime(){
        long totalTime = 0;
        for (Punch punch : clockedHours){
            totalTime += punch.punchTime();
        }
        return  totalTime;
    }

    private void newPunch(){
        if (clockedHours.size() == 0 || clockedHours.get(clockedHours.size() - 1).getPunchOut() != null){
            clockedHours.add(new Punch(LocalDateTime.now()));
        }else{
            clockedHours.get(clockedHours.size() - 1).createPunchOut(LocalDateTime.now());
        }
    }

}
