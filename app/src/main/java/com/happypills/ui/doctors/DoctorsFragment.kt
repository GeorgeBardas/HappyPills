package com.happypills.ui.doctors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.ui.doctors.utils.DoctorListRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_doctors.view.*

class DoctorsFragment : Fragment() {

    private lateinit var doctorsViewModel: DoctorsViewModel
    private lateinit var root: View
    private var recyclerViewAdapter = DoctorListRecyclerViewAdapter()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        doctorsViewModel =
            ViewModelProviders.of(this).get(DoctorsViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_doctors, container, false)

        setupRecyclerView()

        return root
    }

    private fun setupRecyclerView() {
        recyclerView = root.doctors_recycler_view
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
        doctorsViewModel.doctorsList.value?.let {
            recyclerViewAdapter.setDoctorsList(doctorsViewModel.doctorsList.value!!)
        }
    }

}