package com.happypills.ui.doctors.adddoctor


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.happypills.R
import com.happypills.objects.Doctor
import com.happypills.util.Util
import kotlinx.android.synthetic.main.fragment_add_doctor.*

class AddDoctorFragment : Fragment() {

    private lateinit var addDoctorViewModel: AddDoctorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_doctor, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDoctorViewModel = ViewModelProviders.of(this).get(AddDoctorViewModel::class.java)
        setup()
    }

    private fun setup() {
        cancel_button?.setOnClickListener {
            view?.clearFocus()
            findNavController().popBackStack()
        }

        add_doctor_button?.setOnClickListener {
            if (doctor_name?.editText?.text?.isBlank() == false)
                addDoctorViewModel.inserDoctor(
                    Doctor(
                        doctor_name?.editText?.text.toString(),
                        doctor_specialization?.editText?.text.toString(),
                        doctor_phone?.editText?.text.toString()
                    )
                )
            else
                Toast.makeText(context, "Some fields aren't filled...", Toast.LENGTH_SHORT).show()
            view?.clearFocus()
            findNavController().popBackStack()
        }

        doctor_name?.editText?.addTextChangedListener(Util().getTextWatcher(doctor_name))
        doctor_specialization?.editText?.addTextChangedListener(
            Util().getTextWatcher(
                doctor_specialization
            )
        )
        doctor_phone?.editText?.addTextChangedListener(Util().getTextWatcher(doctor_phone))

    }

}
