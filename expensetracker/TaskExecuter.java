package com.expensetracker;

public class TaskExecuter extends Thread{
    private String taskName;
    private Runnable task;
    public TaskExecuter(String taskName,Runnable task){
        this.taskName=taskName;
        this.task=task;
    }    
    public void run(){
        System.out.println("Executing task "+taskName);
        task.run();
        System.out.println("Task completed"+taskName);
    }
}
