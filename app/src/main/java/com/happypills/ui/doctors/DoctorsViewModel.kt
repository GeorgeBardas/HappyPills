package com.happypills.ui.doctors

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.happypills.objects.Doctor
import com.happypills.util.db.AppRoomDb

class DoctorsViewModel(app: Application) : AndroidViewModel(app) {

    val doctorsList: LiveData<List<Doctor>>
    private val repository: DoctorsRepository


    init {
        val dao = AppRoomDb.getDatabase(app).DbDao()
        repository = DoctorsRepository(dao)
        doctorsList = repository.getDoctors()
    }

}