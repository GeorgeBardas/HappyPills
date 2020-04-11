package com.happypills.ui.doctors.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.objects.Doctor

class DoctorListRecyclerViewAdapter(
    private var onClickListener: OnClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var doctorsList = listOf<Doctor>()

    interface OnClickListener {
        fun onCallPressed(doctor: Doctor)
        fun onMessagePressed(doctor: Doctor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoctorListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.doctor_item_view, parent, false)
        )
    }

    override fun getItemCount(): Int = doctorsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val doctorViewHolder = holder as DoctorListViewHolder
        doctorViewHolder.bindView(doctor = doctorsList[position], clickListener = onClickListener)
    }

    fun setDoctorsList(doctors: List<Doctor>) {
        this.doctorsList = doctors
        notifyDataSetChanged()
    }

}