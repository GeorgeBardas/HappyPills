package com.happypills.ui.pills.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.objects.Pill
import kotlinx.android.synthetic.main.pill_item_view.view.*

class PillListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(context: Context, pill: Pill) {
        itemView.pill_item_view_title.text = pill.title
        itemView.pill_item_view_quantity.text = context.getString(R.string.pill_view_quantity_text, pill.quantity)
    }

}