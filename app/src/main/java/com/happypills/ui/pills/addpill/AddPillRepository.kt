package com.happypills.ui.pills.addpill

import com.happypills.objects.Pill
import com.happypills.util.db.DbDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Suppress("unused")
class AddPillRepository(val dao: DbDao) {

    fun insertPill(pill: Pill) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertPill(pill)
        }
    }

}