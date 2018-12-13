package com.example.victo.noteapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;
    private LiveData<List<NoteEntry>> mAllNotes;
//    private NoteEntry noteEntry;

    public NoteViewModel(Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();

    }

    LiveData<List<NoteEntry>> getAllNotes(){
        return mAllNotes;
    }

    public void insert(NoteEntry noteEntry) {
        mRepository.insert(noteEntry);
    }
}
