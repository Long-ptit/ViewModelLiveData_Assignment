package com.example.baseproject.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Student.class}, version = 2)
public abstract class MyDataBase extends RoomDatabase {

    private static MyDataBase instance;
    public abstract StudentDAO studentDAO();

    public MyDataBase() {
    }

    public static MyDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, MyDataBase.class, "MyDataBase").build();
        }
        return instance;
    }

}
