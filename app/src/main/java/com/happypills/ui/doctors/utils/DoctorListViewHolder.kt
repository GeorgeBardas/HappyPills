package com.happypills.ui.doctors.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.happypills.objects.Doctor
import com.happypills.ui.doctors.utils.DoctorListRecyclerViewAdapter.*
import kotlinx.android.synthetic.main.doctor_item_view.view.*

class DoctorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindView(doctor: Doctor, clickListener: OnClickListener) {
        itemView.apply {
            doctor_name?.text = doctor.name
            doctor_phone?.text = doctor.phone
            doctor_specialization?.text = doctor.specialization
            call_button?.setOnClickListener { clickListener.onCallPressed(doctor) }
            message_button?.setOnClickListener { clickListener.onMessagePressed(doctor) }
        }
    }

}