package com.happypills.ui.doctors.adddoctor

import com.happypills.objects.Doctor
import com.happypills.util.db.DbDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddDoctorRepository(val dao: DbDao) {

    fun insertDoctor(doctor: Doctor) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertDoctor(doctor)
        }
    }

}