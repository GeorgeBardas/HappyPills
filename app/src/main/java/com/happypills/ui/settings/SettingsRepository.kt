package com.happypills.ui.settings

import androidx.lifecycle.LiveData
import com.happypills.objects.Pill
import com.happypills.util.db.DbDao

class SettingsRepository(private val dao: DbDao) {

    suspend fun deletePills() = dao.emptyPillsTable()

    suspend fun deleteDoctors() = dao.emptyDoctorsTable()
}