package com.happypills.ui.pills.addpill

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.happypills.objects.Pill
import com.happypills.util.db.AppRoomDb

class AddPillViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: AddPillRepository

    init {
        val dao = AppRoomDb.getDatabase(app).DbDao()
        repository = AddPillRepository(dao)
    }

    fun insertPill(pill: Pill) {
        repository.insertPill(pill)
    }

}