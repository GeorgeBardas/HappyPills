package com.happypills.ui.stores.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.objects.Store
import kotlinx.android.synthetic.main.store_list_element_view.view.*

class StoreRecyclerViewAdapter(private val list: List<Store>) : RecyclerView.Adapter<StoreRecyclerViewAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.store_list_element_view, parent, false)
        return CustomViewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = list.get(position)
        holder.itemView.store_title_tv.text = item.name
    }

    class CustomViewHolder(rowView: View) : RecyclerView.ViewHolder(rowView) {

        init {
//            rowView.setOnClickListener {
//                //println(item)
//            }
        }

    }
}