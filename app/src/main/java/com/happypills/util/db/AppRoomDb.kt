package com.happypills.util.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.happypills.objects.Doctor
import com.happypills.objects.Pill

@Database(entities = [Pill::class, Doctor::class], version = 1, exportSchema = false)
abstract class AppRoomDb : RoomDatabase() {

    abstract fun DbDao(): DbDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDb? = null

        fun getDatabase(context: Context): AppRoomDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDb::class.java,
                    "happypills_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}