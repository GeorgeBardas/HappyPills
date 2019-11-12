package com.happypills.ui.pills.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.objects.Pill

class PillListGridRecyclerView : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var pillsList = listOf<Pill>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PillListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pill_item_view, parent, false))
    }

    override fun getItemCount(): Int = pillsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pillViewHolder = holder as PillListViewHolder
        pillViewHolder.bindView(context = holder.itemView.context, pill = pillsList[position])
    }

    fun setPillsList(pills: List<Pill>){
        this.pillsList = pills
        notifyDataSetChanged()
    }

}