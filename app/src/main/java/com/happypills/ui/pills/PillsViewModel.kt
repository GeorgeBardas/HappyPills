package com.happypills.ui.pills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.happypills.objects.Pill

class PillsViewModel : ViewModel() {

    val pillsList: LiveData<List<Pill>> = PillsRepository().getPills()
}