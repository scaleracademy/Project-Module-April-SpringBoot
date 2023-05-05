package com.scaler.springboot1.task;

import java.util.Date;

public class Task {
    Integer id;
    String name;
    Date dueDate;

    Boolean completed;

    public Task(Integer id, String name, Date dueDate, Boolean completed) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    /* add getter and setter funcitons for each of our id*


     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return dueDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Boolean setCompleted(Boolean completed) {
        this.completed = completed;
        return completed;
    }

}
