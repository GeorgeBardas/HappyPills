package com.happypills.ui.pills

import android.app.Application
import androidx.lifecycle.*
import com.happypills.objects.Pill
import com.happypills.util.db.AppRoomDb

class PillsViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: PillsRepository
    val pillsList: LiveData<List<Pill>>

    init {
        val dao = AppRoomDb.getDatabase(app).DbDao()
        repository = PillsRepository(dao)
        pillsList = repository.getData()

    }

}