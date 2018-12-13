package com.example.victo.noteapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note_table")
    LiveData<List<NoteEntry>> getAllNotes();
//    List<Note> NOTE_ENTRIES();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteEntry note);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void Note(NoteEntry noteEntry);

    @Query("DELETE FROM note_table")
    void deleteAll();

}
