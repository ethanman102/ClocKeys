package com.example.clockeys.Users;

import com.example.clockeys.Time.Timecard;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Employee implements Serializable {

    private int employeeNumber;
    private String name;
    private Date dateOfBirth;
    private Date hireDate;

    private String bio;
    private Timecard timecard;
    private String title;


    public Employee(int employeeNumber, String name, Date dateOfBirth, Timecard timecard, Date hireDate,String title,String bio){
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.timecard = timecard;
        this.hireDate = hireDate;
        this.title = title;
        this.bio = bio;
    }



    // Common Getter and Setters:


    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }

    public String getName(){
        return this.name;
    }

    public int getEmployeeNumber(){
        return this.employeeNumber;
    }


    public void setName(String newName){
        this.name = newName;
    }

    public String getTitle(){
        return this.title;
    }

    public Timecard getTimecard(){
        return this.timecard;
    }

    public String getBio(){
        return this.bio;
    }

    public void setBio(String bio){
        this.bio = bio;
    }
}
