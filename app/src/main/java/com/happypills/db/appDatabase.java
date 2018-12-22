package com.happypills.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.happypills.db.dao.pillsDao;
import com.happypills.db.objects.doctor;
import com.happypills.db.objects.pill;

@Database(entities = {pill.class, doctor.class}, version = 1)
public abstract class appDatabase extends RoomDatabase {

    private static appDatabase INSTANCE;

    public abstract pillsDao pillsDao();

    public static appDatabase getAppDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), appDatabase.class, "database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
