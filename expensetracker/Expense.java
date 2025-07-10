package com.expensetracker;
import java.sql.*;

public class Expense{
    private int id;
    private String category;
    private Date date;
    private double amount;
    public Expense(int id,String category, double amount,Date date){
        this.id=id;
        this.category=category;
        this.amount=amount;
        this.date=date;
    }
    public int getId(){
        return id;
    }
    public  String getCategory(){
        return category;
    }
    public double getAmount(){
        return amount;
    }
    public  Date getDate(){
        return date;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }
    public void setDate(Date date){
        this.date=date;
    }
}
