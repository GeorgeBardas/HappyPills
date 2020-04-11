package com.happypills.ui.settings


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.happypills.R
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {

    private lateinit var settingViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDeletePills()
        setupDeleteDoctors()
        setupFeedback()
    }

    private fun setupDeletePills() {
        deletePillsView?.setOnClickListener {
            GlobalScope
                .launch { settingViewModel.deleteAllPills() }
        }
    }

    private fun setupDeleteDoctors() {
        deleteDoctorsView?.setOnClickListener {
            GlobalScope
                .launch { settingViewModel.deleteAllDoctors() }
        }
    }

    private fun setupFeedback() {
        sendFeedbackView?.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            startActivity(Intent.createChooser(intent, "Send Email"))
        }
    }
}
