package com.example.victo.noteapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Date;

@Database(entities = {NoteEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class NoteAppDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
    private static final String LOG_TAG = NoteAppDatabase.class.getSimpleName();
    public static final Object LOCK = new Object();
    public static final String DATABASE_NAME = "todonote";
    private static NoteAppDatabase sInstance;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(sInstance).execute();
                }
            };

    public static NoteAppDatabase getInstance(Context context) {
        if (sInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "getInstance: Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        NoteAppDatabase.class, NoteAppDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
//                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
        }
        Log.d(LOG_TAG, "getInstance: Getting the database instance");
        return sInstance;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDao mDao;

        PopulateDbAsync(NoteAppDatabase db) {
            mDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mDao.deleteAll();
            Date date = new Date();
            NoteEntry note = new NoteEntry("New text", date);
            mDao.insert(note);
            note = new NoteEntry("Welcome to NoteApp DSC Challenge..");
            mDao.insert(note);
            return null;
        }
    }


}
