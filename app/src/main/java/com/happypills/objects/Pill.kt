package com.happypills.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pills_table")
class Pill {

    @PrimaryKey
    @ColumnInfo
    var title : String
    @ColumnInfo
    var quantity : Int

    constructor(title: String, quantity: Int) {
        this.title = title
        this.quantity = quantity
    }
}