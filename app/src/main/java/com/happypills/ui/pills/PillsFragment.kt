package com.happypills.ui.pills

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.happypills.R
import com.happypills.objects.Pill
import com.happypills.ui.pills.utils.PillInfoDialog
import com.happypills.ui.pills.utils.PillListGridRecyclerViewAdapter
import com.happypills.ui.pills.utils.PillListGridRecyclerViewAdapter.*
import kotlinx.android.synthetic.main.fragment_doctors.*
import kotlinx.android.synthetic.main.fragment_pills.*
import kotlinx.android.synthetic.main.fragment_pills.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PillsFragment : Fragment() {

    private lateinit var pillsViewModel: PillsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: PillListGridRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pillsViewModel =
            ViewModelProviders.of(this).get(PillsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_pills, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupView()
    }

    private fun setupRecyclerView() {
        view?.let { recyclerView = it.pills_recycler_view }
        recyclerViewAdapter = PillListGridRecyclerViewAdapter(object : OnItemClickedListener {
            override fun plusButtonClicked(pill: Pill) {
                pill.quantity++
                GlobalScope.launch { pillsViewModel.updatePill(pill) }
            }

            override fun minusButtonClicked(pill: Pill) {
                when (pill.quantity) {
                    1 -> GlobalScope.launch { pillsViewModel.deletePill(pill) }
                    else -> {
                        pill.quantity--
                        GlobalScope.launch { pillsViewModel.updatePill(pill) }
                    }
                }
            }

            override fun infoButtonClicked(pill: Pill) {
                fragmentManager?.let { manager ->
                    PillInfoDialog(
                        pill
                    ).show(manager, "PillDialog")
                }
            }
        })
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = recyclerViewAdapter
        }
        pillsViewModel.pillsList.observeForever {
            if (it.isEmpty()) {
                pills_empty_state?.visibility = View.VISIBLE
                pills_recycler_view?.visibility = View.GONE
            }
            else {
                pills_empty_state?.visibility = View.GONE
                pills_recycler_view?.visibility = View.VISIBLE
                recyclerViewAdapter.setPillsList(it)
            }
        }
    }

    private fun setupView() {
        view?.add_pill_button?.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_pills_to_addPillFragment)
        }
    }

}