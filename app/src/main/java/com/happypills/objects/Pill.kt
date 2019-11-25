package com.happypills.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pillsTable")
class Pill {

    @PrimaryKey
    @ColumnInfo
    var title: String
    @ColumnInfo
    var quantity: Int
    @ColumnInfo
    var comment: String
    @ColumnInfo
    var expiryDate: String

    constructor(title: String, quantity: Int, comment: String, expiryDate: String) {
        this.title = title
        this.quantity = quantity
        this.comment = comment
        this.expiryDate = expiryDate
    }

}