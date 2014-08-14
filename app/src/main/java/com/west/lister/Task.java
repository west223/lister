package com.west.lister;

/**
 * Created by usr1 on 8/11/14.
 */
public class Task {

    private String listName;
    private String taskName;
    private int status;
    private int id;

    public Task(){
        this.listName = null;
        this.taskName = null;
        this.status = 0;
    }

    public Task(String taskName, int status){
        super();
       // this.listName = listName; //Special List
        this.taskName = taskName;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

//    public String getListName(){
//        return listName;
//    }
//
//    public void setListName(String listName){
//        this.listName = listName;
//    }

    public String getTaskName(){
        return taskName;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }
}
