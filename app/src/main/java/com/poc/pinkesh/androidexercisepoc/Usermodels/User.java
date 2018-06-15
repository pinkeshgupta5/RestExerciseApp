package com.poc.pinkesh.androidexercisepoc.Usermodels;



import java.util.List;


public class User {


    private String title;


    private List<Rows> rows;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setRows(List<Rows> rows){
        this.rows = rows;
    }
    public List<Rows> getRows(){
        return this.rows;
    }

}
