package com.happypills.ui.doctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.happypills.objects.Doctor

class DoctorsViewModel : ViewModel() {

    val doctorsList: LiveData<List<Doctor>> = DoctorsRepository().getDoctors()

}