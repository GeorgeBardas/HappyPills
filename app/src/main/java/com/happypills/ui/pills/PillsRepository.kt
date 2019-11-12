package com.happypills.ui.pills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.happypills.objects.Pill

class PillsRepository {

    fun getPills() : MutableLiveData<List<Pill>> {
        val list = MutableLiveData<List<Pill>>()
        var listOfPills = mutableListOf<Pill>()
        listOfPills.add(Pill("Test", 2))
        listOfPills.add(Pill("Test", 3))
        listOfPills.add(Pill("Test", 10))
        listOfPills.add(Pill("Test", 7))
        listOfPills.add(Pill("Test", 35))
        listOfPills.add(Pill("Test", 1))
        listOfPills.add(Pill("Test", 0))
        listOfPills.add(Pill("Test", 7))
        listOfPills.add(Pill("Test", 35))
        listOfPills.add(Pill("Test", 1))
        listOfPills.add(Pill("Test", 0))
        list.value = listOfPills
        return list
    }

}