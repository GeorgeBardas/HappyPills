package com.happypills.ui.pills.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.objects.Pill
import com.happypills.ui.pills.utils.PillListGridRecyclerViewAdapter.*
import kotlinx.android.synthetic.main.pill_item_view.view.*

class PillListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(context: Context, pill: Pill, listener: OnItemClickedListener) {

        fun setQuantity() {
            itemView.pill_item_view_quantity?.apply {
                text = context.getString(R.string.pill_view_quantity_text, pill.quantity)
                when (pill.quantity) {
                    in 1..3 -> setTextColor(context.getColor(R.color.red))
                    in 3..5 -> setTextColor(context.getColor(R.color.orange))
                    else -> setTextColor(context.getColor(R.color.grey))
                }
            }
        }

        itemView.apply {
            pill_item_view_title?.text = pill.title
            setQuantity()
            add_pill_button?.setOnClickListener { listener.plusButtonClicked(pill) }
            minus_pill_button?.setOnClickListener { listener.minusButtonClicked(pill) }
            info_pill_button?.setOnClickListener { listener.infoButtonClicked(pill) }
        }

    }

}