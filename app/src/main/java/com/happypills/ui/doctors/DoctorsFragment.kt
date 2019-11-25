package com.happypills.ui.doctors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.ui.doctors.utils.DoctorListRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_doctors.*
import kotlinx.android.synthetic.main.fragment_doctors.view.*

class DoctorsFragment : Fragment() {

    private lateinit var doctorsViewModel: DoctorsViewModel
    private var recyclerViewAdapter = DoctorListRecyclerViewAdapter()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        doctorsViewModel = ViewModelProviders.of(this).get(DoctorsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_doctors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        view?.let { recyclerView = it.doctors_recycler_view }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
        doctorsViewModel.doctorsList.observeForever {
            if (it.isEmpty())
                doctors_empty_state?.visibility = View.VISIBLE
            else {
                doctors_empty_state?.visibility = View.GONE
                recyclerViewAdapter.setDoctorsList(it)
            }
        }
    }

    private fun setup(){
        view?.add_doctor_button?.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_doctors_to_addDoctorFragment)
        }
    }

}