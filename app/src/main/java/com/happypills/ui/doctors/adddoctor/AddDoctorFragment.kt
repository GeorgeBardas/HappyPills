package com.happypills.ui.doctors.adddoctor


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.happypills.R
import kotlinx.android.synthetic.main.fragment_add_pill.view.*
import kotlinx.android.synthetic.main.fragment_doctors.view.*

class AddDoctorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_doctor, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
    }

    private fun setup() {
        view?.cancel_button?.setOnClickListener {
            view?.clearFocus()
            findNavController().popBackStack()
        }

        view?.add_doctor_button?.setOnClickListener {
            view?.clearFocus()
        }
    }

}
