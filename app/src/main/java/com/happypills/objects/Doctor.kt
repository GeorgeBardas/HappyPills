package com.happypills.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctorsTable")
class Doctor {

    @PrimaryKey
    @ColumnInfo
    var name: String
    @ColumnInfo
    var specialization: String
    @ColumnInfo
    var phone: String

    constructor(name: String, specialization: String, phone: String) {
        this.name = name
        this.specialization = specialization
        this.phone = phone
    }
}