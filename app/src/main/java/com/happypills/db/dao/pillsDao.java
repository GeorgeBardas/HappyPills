package com.happypills.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.happypills.db.objects.doctor;
import com.happypills.db.objects.pill;
import com.happypills.fragments.pills;

import java.util.List;

@Dao
public interface pillsDao {

    @Query("SELECT * FROM pills")
    LiveData<List<pill>> getAll();

    @Insert
    void insert(pill pill);

    @Query("DELETE FROM pills")
    void emptyTable();

    @Update
    void updatePill(pill pill);

    @Delete
    void deletePill(pill pill);

    @Query("SELECT * FROM doctors")
    LiveData<List<doctor>> getAllDoctors();

    @Insert
    void insertDoctor(doctor doctor);

    @Query("DELETE FROM doctors")
    void emptyDoctorsTable();

}
