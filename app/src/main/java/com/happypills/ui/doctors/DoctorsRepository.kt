package com.happypills.ui.doctors

import androidx.lifecycle.LiveData
import com.happypills.objects.Doctor
import com.happypills.util.db.DbDao

class DoctorsRepository(private val dao: DbDao) {

    fun getDoctors(): LiveData<List<Doctor>> = dao.getDoctors()

}