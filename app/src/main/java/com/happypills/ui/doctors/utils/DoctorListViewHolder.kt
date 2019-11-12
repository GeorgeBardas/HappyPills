package com.happypills.ui.doctors.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.happypills.objects.Doctor
import kotlinx.android.synthetic.main.doctor_item_view.view.*

class DoctorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindView(doctor: Doctor) {
        itemView.doctor_name.text = doctor.name
        itemView.doctor_phone.text = doctor.phone
        itemView.doctor_specialization.text = doctor.specialization
    }

}