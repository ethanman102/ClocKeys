package com.example.clockeys.Users;

import com.example.clockeys.Time.Timecard;

import java.io.Serializable;
import java.sql.Time;
import java.util.Comparator;
import java.util.Date;

public class Employee implements Serializable {

    public static class EmployeeComparator<T extends Employee> implements Comparator<Employee>{
        private String sortType;
        public EmployeeComparator(){
            this("name");
        }

        public EmployeeComparator(String sortType){
            this.sortType = sortType;
        }
        @Override
        public int compare(Employee employeeOne, Employee employeeTwo) {
            if (sortType.equalsIgnoreCase("name")) {
                return employeeOne.name.compareTo(employeeTwo.name);
            } else if (sortType.equalsIgnoreCase("hire Date")) {
                return employeeOne.hireDate.compareTo(employeeTwo.hireDate);
            } else if (sortType.equalsIgnoreCase("age")){
                return employeeOne.dateOfBirth.compareTo(employeeTwo.dateOfBirth);
            }else if (sortType.equalsIgnoreCase("id")){
                return Integer.compare(employeeOne.employeeNumber,employeeTwo.employeeNumber);
            }else{
                return employeeOne.name.compareTo(employeeTwo.name);
            }
        }
    }

    private int employeeNumber;
    private String name;
    private Date dateOfBirth;
    private Date hireDate;

    private String bio;
    private String address;
    private Timecard timecard;
    private String title;


    public Employee(int employeeNumber, String name, Date dateOfBirth, Timecard timecard, Date hireDate,String title,String bio, String address){



        this.employeeNumber = employeeNumber;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.timecard = timecard;
        this.hireDate = hireDate;
        this.title = title;
        this.bio = bio;
        this.address = address;
    }

    public Employee(){}



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

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
}
