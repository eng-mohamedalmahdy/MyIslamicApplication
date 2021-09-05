package com.example.myisamlicapplication.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myisamlicapplication.data.pojo.Aya;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Callable;

@Database(entities = {Aya.class}, version = 1)
public abstract class QuranDatabase extends RoomDatabase {

    private static QuranDatabase instance = null;

    public abstract QuranDao quranDao();

    public static QuranDatabase getInstance(Context context) {

        if (instance == null) {
            synchronized (QuranDatabase.class) {
                if (instance == null) {
                    try {
                        String DATABASE_NAME = "quran.db";
                        instance = Room.databaseBuilder(context.getApplicationContext(),
                                QuranDatabase.class, DATABASE_NAME)
                                .createFromAsset("databases/quran.db")
                                .allowMainThreadQueries()
                                .build();
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        }
        return instance;
    }

}
