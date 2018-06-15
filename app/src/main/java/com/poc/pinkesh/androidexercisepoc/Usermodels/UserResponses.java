package com.poc.pinkesh.androidexercisepoc.Usermodels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponses {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("title")
    private String title;

    public ArrayList<Rows> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Rows> rows) {
        this.rows = rows;
    }

    @SerializedName("rows")
    private ArrayList<Rows> rows;


}
