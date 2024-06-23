package com.example.clockeys.Time;

import android.util.Log;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Timecard implements Serializable {
    private ArrayList<Punch> clockedHours;


    public Timecard(ArrayList<Punch> clockedHours){
        this.clockedHours = new ArrayList<Punch>(clockedHours);
    }


    public List<Punch> getClockedHours(){
        return Collections.unmodifiableList(clockedHours);
    }


    public double calculateTime(){
        double totalTime = 0.00;
        for (Punch punch : clockedHours){
            totalTime += punch.punchTime();
            Log.d("HHEY", "calculateTime: " + String.valueOf(totalTime));
        }

        return  totalTime / 60.0 / 60.0;
    }

    public String newPunch(){
        if (clockedHours.size() == 0 || clockedHours.get(clockedHours.size() - 1).getPunchOut() != null){
            clockedHours.add(new Punch(LocalDateTime.now()));
            return "In";
        }else{
            clockedHours.get(clockedHours.size() - 1).createPunchOut(LocalDateTime.now());
            return "Out";
        }
    }

}
