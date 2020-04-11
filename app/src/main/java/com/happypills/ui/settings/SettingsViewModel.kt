package com.happypills.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.happypills.objects.Pill
import com.happypills.util.db.AppRoomDb

class SettingsViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: SettingsRepository

    init {
        val dao = AppRoomDb.getDatabase(app).DbDao()
        repository = SettingsRepository(dao)
    }

    suspend fun deleteAllPills() = repository.deletePills()

    suspend fun deleteAllDoctors() = repository.deleteDoctors()

}