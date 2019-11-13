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
import androidx.navigation.fragment.findNavController
import com.happypills.MainActivity
import com.happypills.R
import com.happypills.util.Util
import kotlinx.android.synthetic.main.fragment_add_pill.view.*
import java.util.*
import java.util.regex.Pattern

class AddPillFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_pill, container, false)

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
            view?.clearFocus()
        }

        initDatePicker()
        view?.pill_name?.editText?.addTextChangedListener(Util().getTextWatcher(view?.pill_name))
        view?.pill_quantity?.editText?.addTextChangedListener(Util().getNumberyWatcher(view?.pill_quantity))
        view?.pill_commentary?.editText?.addTextChangedListener(Util().getTextWatcher(view?.pill_name))
    }

    private fun initDatePicker() {
        val calendar = Calendar.getInstance()

        val date = DatePickerDialog.OnDateSetListener { picker, i1, i2, i3 ->
            calendar.set(Calendar.YEAR, i1)
            calendar.set(Calendar.MONTH, i2)
            calendar.set(Calendar.DAY_OF_MONTH, i3)
            view?.pill_expire_date?.editText?.setText(i3.toString() + '.' + i2 + '.' + i1)
            if (calendar.before(Calendar.getInstance())) view?.pill_expire_date?.setError("Invalid date...")
            else view?.pill_expire_date?.setError(null)
        }

        view?.pill_expire_date?.editText?.setOnClickListener {
            DatePickerDialog(
                context!!,
                date,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

}
