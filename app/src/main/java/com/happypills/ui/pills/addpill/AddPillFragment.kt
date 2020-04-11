package com.happypills.ui.pills.addpill


import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.happypills.MainActivity
import com.happypills.R
import com.happypills.objects.Pill
import com.happypills.ui.pills.PillsViewModel
import com.happypills.util.Util
import kotlinx.android.synthetic.main.fragment_add_pill.view.*
import java.util.*
import java.util.regex.Pattern

class AddPillFragment : Fragment() {

    private lateinit var addPillViewModel: AddPillViewModel

    val pill : Pill = Pill("", 0, "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addPillViewModel = ViewModelProviders.of(this).get(AddPillViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_pill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        view?.cancel_button?.setOnClickListener {
            view?.clearFocus()
            findNavController().popBackStack()
        }

        view?.add_pill_button?.setOnClickListener {
            if (isFormValid()) {
                addPillViewModel.insertPill(getDataFromForm())
                view?.clearFocus()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Some fields aren't filled...", Toast.LENGTH_SHORT).show()
            }
        }

        initDatePicker()
        fieldValidation()
    }

    private fun fieldValidation() {
        view?.apply {
            pill_name?.editText?.addTextChangedListener(Util().getTextWatcher(view?.pill_name))
            pill_quantity?.editText?.addTextChangedListener(Util().getNumberyWatcher(view?.pill_quantity))
            pill_commentary?.editText?.addTextChangedListener(Util().getTextWatcher(view?.pill_name))
            pill_name?.editText?.setOnFocusChangeListener { _, b ->
                if (!b) {
                    pill_name?.editText?.text?.isEmpty()?.let {
                        if (it) pill_name?.editText?.error = "Invalid characters..."
                        else pill_name?.editText?.error = null
                    } ?: kotlin.run { pill_name?.editText?.error = "Invalid characters..." }
                }
            }
            pill_quantity?.editText?.setOnFocusChangeListener { _, b ->
                if (!b) {
                    pill_quantity?.editText?.text?.isEmpty()?.let {
                        if (it) pill_quantity?.editText?.error = "Invalid characters..."
                        else pill_quantity?.editText?.error = null
                    } ?: kotlin.run { pill_quantity?.editText?.error = "Invalid characters..." }
                }
            }
        }
    }

    private fun isFormValid(): Boolean {
        view?.apply {
            val isTitleEmpty = pill_name?.editText?.text?.isNotBlank()
            val isQuantityValid = pill_quantity?.editText?.text?.isNotBlank()
            return isTitleEmpty ?: false && isQuantityValid ?: false
        }
        return false
    }

    private fun initDatePicker() {
        val calendar = Calendar.getInstance()
        var dateToString: String

        val date = DatePickerDialog.OnDateSetListener { _, i1, i2, i3 ->
            calendar.set(Calendar.YEAR, i1)
            calendar.set(Calendar.MONTH, i2)
            calendar.set(Calendar.DAY_OF_MONTH, i3)
            dateToString = getString(R.string.pill_date_format, i3, i2, i1)
            view?.pill_expire_date?.editText?.setText(dateToString)
            pill.expiryDate = dateToString
            if (calendar.before(Calendar.getInstance())) view?.pill_expire_date?.setError("Invalid date...")
            else view?.pill_expire_date?.setError(null)
        }

        context?.let { ctx ->
            view?.pill_expire_date?.editText?.setOnClickListener {
                DatePickerDialog(
                    ctx,
                    date,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }

    private fun getDataFromForm(): Pill {
        view?.pill_name?.editText?.text?.toString()?.let {
            pill.title = it
        }
        view?.pill_quantity?.editText?.text?.toString()?.toIntOrNull()?.let {
            pill.quantity = it
        }
        view?.pill_commentary?.editText?.text?.toString()?.let {
            pill.comment = it
        }

        return pill
    }

}
