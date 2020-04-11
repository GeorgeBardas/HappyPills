package com.happypills.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.fragment_add_pill.view.*
import java.util.regex.Pattern

class Util {

    companion object {
        const val BASIC_REGEX = "^[a-zA-Z0-9 ()*#!]*\$"
        const val MAX_QUANTITY = 1000
    }

    fun getTextWatcher(view: View?) : TextWatcher{
        return object : TextWatcher{
            val pattern = Pattern.compile("^[a-zA-Z0-9 ()*#!]*\$")

            override fun afterTextChanged(p0: Editable?) {
                if(pattern.matcher(p0).matches()) view?.pill_name?.error = null
                else view?.pill_name?.error = "Invalid characters..."
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        }
    }

    fun getNumberyWatcher(view: View?) : TextWatcher {
        return object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                p0.toString().toIntOrNull()?.let {
                    if (it.compareTo(MAX_QUANTITY) > 0) view?.pill_quantity?.setError("Too many pills...")
                    else view?.pill_quantity?.setError(null)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        }
    }

}