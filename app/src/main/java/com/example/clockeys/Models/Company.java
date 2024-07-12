package com.example.clockeys.Models;

import com.example.clockeys.Users.Employee;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company implements Serializable {

    private String companyName;
    private int employeeCount;
    private ArrayList<Employee> employees;
    private String companyImageURL;
    private int companyID;
    private int ownerID;
    private String address;

    public Company(){

    }
    public Company(String companyName,String address ,int employeeCount, ArrayList<Employee> employees, String companyImageURL, int companyID, int ownerID){
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyImageURL = companyImageURL;
        this.ownerID = ownerID;
        this.employeeCount = employeeCount;
        this.employees = employees;
        this.address = address;
    }

    public String generateCode(){

        // Generates a Random Join code to give to an employee so that the employee can join the
        // Company using such given code.

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6;

        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++){
            int random = sr.nextInt(characters.length());
            sb.append(characters.charAt(random));
        }

        return sb.toString();
    }

    public Boolean fireEmployee(Employee employee){
        if (!employees.contains(employee)){
            return Boolean.FALSE;
        }
        else{
            employees.remove(employee);
            return Boolean.TRUE;
        }
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public Boolean hireEmployee(Employee employee){
        if (employees.contains(employee)){
            return Boolean.FALSE;
        }
        else{
            employees.add(employee);
            return Boolean.TRUE;
        }
    }

    public List<Employee> getEmployees(){
        return Collections.unmodifiableList(employees);
    }

    public int getCompanyID(){
        return this.companyID;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }


}
