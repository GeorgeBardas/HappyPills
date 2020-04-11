package com.happypills.ui.doctors.adddoctor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.happypills.objects.Doctor
import com.happypills.util.db.AppRoomDb

class AddDoctorViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: AddDoctorRepository

    init {
        val dao = AppRoomDb.getDatabase(app).DbDao()
        repository = AddDoctorRepository(dao)
    }

    fun inserDoctor(doctor: Doctor) {
        repository.insertDoctor(doctor)
    }

}