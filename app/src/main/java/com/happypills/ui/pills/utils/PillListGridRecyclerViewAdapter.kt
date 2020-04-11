package com.happypills.ui.pills.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.objects.Pill

class PillListGridRecyclerViewAdapter(
    private var clickListener: OnItemClickedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var pillsList: List<Pill> = listOf()
    interface OnItemClickedListener {
        fun plusButtonClicked(pill: Pill)
        fun minusButtonClicked(pill: Pill)
        fun infoButtonClicked(pill: Pill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PillListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pill_item_view, parent, false)
        )
    }

    override fun getItemCount(): Int = pillsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pillViewHolder = holder as PillListViewHolder
        pillViewHolder.bindView(
            context = holder.itemView.context,
            pill = pillsList[position],
            listener = clickListener
        )
    }

    fun setPillsList(pills: List<Pill>) {
        this.pillsList = pills
        notifyDataSetChanged()
    }

}