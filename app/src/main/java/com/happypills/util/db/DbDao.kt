package com.happypills.util.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.happypills.objects.Pill

@Dao
interface DbDao {

    @Query("SELECT * from pills_table")
    fun getPills(): List<Pill>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPill(pill: Pill)

}