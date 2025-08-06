package com.expensetracker;
import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;

public class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        ExpenseDBManager e=new ExpenseDBManager();
        while(true){
            System.out.println("EXPENSE TRACKER MENU");
            System.out.println("1.Add expense");
            System.out.println("2.View all expenses");
            System.out.println("3.Search expense by Id");
            System.out.println("4.View total expense");
            System.out.println("5.Exit");
            System.out.println("Enter your choice:");
            int ch=sc.nextInt();
            sc.nextLine();
            switch(ch){
                case 1: System.out.println("Enter category:");
                        String category=sc.nextLine();
                        System.out.println("Enter amount:");
                        double amount=sc.nextDouble();
                        LocalDate localDate=LocalDate.now();
                        Date sqlDate=Date.valueOf(localDate);
                        Expense expense=new Expense(0,category,amount,sqlDate);
                        new TaskExecuter("Add Expense",()->{
                            try{
                                e.addExpense(expense);
                                System.out.println("Expense added");
                            }
                            catch(Exception ex){
                                ex.printStackTrace();
                            }
                        }).start();
                        break;
                case 2: new TaskExecuter("View all expenses",()->{
                            try{
                                e.getAllExpenses();
                            }
                            catch(Exception ex){
                                ex.printStackTrace();
                            }
                        }).start();
                        break;
                case 3: System.out.println("Enter expense id");
                        int id=sc.nextInt();
                        new TaskExecuter("Search expense",()->{
                            try{
                                Expense exp=e.getExpenseById(id);
                                if(exp!=null)
                                    System.out.println("ID: "+exp.getId()+", Rs."+exp.getAmount()+", "+exp.getCategory()+", "+exp.getDate());
                            }
                            catch(Exception ex){
                                ex.printStackTrace();
                            }
                        }).start();
                        break;
                case 4: new TaskExecuter("View total Expense",()->{
                            try{
                                double total=e.getTotalExpense();
                                System.out.println("Total Expenses: Rs." +total);
                            } 
                            catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }).start();
                        break;    
                case 5: System.out.println("Exiting");
                        System.exit(0);
                default: System.out.println("Invalid choice");

            }
        }
    }
}
