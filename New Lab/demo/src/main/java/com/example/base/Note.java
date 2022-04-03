package com.example.base;

import java.io.Serializable;
import java.util.Date;

public class Note implements Comparable<Note>, Serializable {
    private Date date;
    private String title;

    public Note(String title){
        this.title = title;
        this.date = new Date();
    }
    public String getTitle(){
        return this.title;
    }
    public boolean equals(Note n){
        return this.title.equals(n.getTitle());
    }
    public String toString(){
        return date.toString() + "\t" + title;
    }
    @Override
    public int compareTo(Note o){
        if(this.date.after(o.date)){
            return -1;
        }
        else if (this.date.before(o.date)){
            return 1;
        }
        else return 0;
    }
}