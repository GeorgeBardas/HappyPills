package com.happypills.ui.pills.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.happypills.R
import com.happypills.objects.Pill
import kotlinx.android.synthetic.main.pill_dialog_view.view.*

class PillInfoDialog(
    private var pill: Pill
    ) : DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setCanceledOnTouchOutside(true)
        return inflater.inflate(
            R.layout.pill_dialog_view,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            pill_title.text = pill.title
            pill_quantity.text = context.getString(R.string.pill_view_quantity_text, pill.quantity).toLowerCase()
            pill_mentions.text = pill.comment
        }

//        rightButton.setOnClickListener {
//            rightButtonListener?.onRightClick()
//            dismiss() }
//        leftButton.setOnClickListener {
//            leftButtonListener?.onLeftClick()
//            dismiss()
//        }
    }

    interface DialogOnRightButtonListener {
        fun onRightClick()
    }

    interface DialogOnLeftButtonListener {
        fun onLeftClick()
    }

}