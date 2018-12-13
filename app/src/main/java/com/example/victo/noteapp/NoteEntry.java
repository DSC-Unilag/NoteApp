package com.example.victo.noteapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


import java.util.Date;

@Entity(tableName = "note_table")
public class NoteEntry {

    @PrimaryKey(autoGenerate = true)
    private  int id;
    private String note;
    private Date date;

    @Ignore
    public NoteEntry(String note, Date date) {
        this.note = note;
        this.date = date;
    }
    public NoteEntry(String note) {
        this.note = note;
        this.id = id;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
