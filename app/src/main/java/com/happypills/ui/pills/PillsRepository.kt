package com.happypills.ui.pills

import androidx.lifecycle.LiveData
import com.happypills.objects.Pill
import com.happypills.util.db.DbDao

class PillsRepository(private val dao: DbDao) {

    fun getData() : LiveData<List<Pill>> = dao.getPills()

}