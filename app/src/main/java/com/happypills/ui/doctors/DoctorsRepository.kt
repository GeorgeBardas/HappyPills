package com.happypills.ui.doctors

import androidx.lifecycle.MutableLiveData
import com.happypills.objects.Doctor

class DoctorsRepository {

    fun getDoctors(): MutableLiveData<List<Doctor>> {
        var list = MutableLiveData<List<Doctor>>()
        val listOfDoctors = mutableListOf<Doctor>()

        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))
        listOfDoctors.add(Doctor(name = "Johnny Ive", phone = "+40 756 154 650", specialization = "Dentist"))

        list.value = listOfDoctors
        return list
    }

}