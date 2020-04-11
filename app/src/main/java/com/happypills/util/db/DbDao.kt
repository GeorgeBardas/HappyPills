package com.happypills.util.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.happypills.objects.Doctor
import com.happypills.objects.Pill

@Dao
interface DbDao {

    @Query("SELECT * from pillsTable")
    fun getPills(): LiveData<List<Pill>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPill(pill: Pill)

    @Query("SELECT * from doctorsTable")
    fun getDoctors(): LiveData<List<Doctor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctor(doctor: Doctor)

    @Update
    suspend fun updatePill(pill: Pill)

    @Delete
    suspend fun deletePill(pill: Pill)

    @Query("DELETE FROM pillsTable")
    suspend fun emptyPillsTable()

    @Query("DELETE FROM doctorsTable")
    suspend fun emptyDoctorsTable()

}