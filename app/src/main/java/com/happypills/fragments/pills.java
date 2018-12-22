package com.happypills.fragments;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.happypills.adapters.pillAdapter;
import com.happypills.R;
import com.happypills.db.objects.pill;
import com.happypills.db.appDatabase;
import java.util.ArrayList;
import java.util.List;

public class pills extends Fragment {

    RecyclerView recyclerView;
    List<pill> pillsList = new ArrayList<>();
    GridLayoutManager layoutManager;
    pillAdapter pillAdapter;

    public pills() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pills, container, false);
        getActivity().findViewById(R.id.fab).setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.recyclerView);
        pillAdapter = new pillAdapter(pillsList, getContext());
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pillAdapter);
        updateUI();

        getActivity().findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("pills")
                        .setCustomAnimations(R.anim.to_right_start, R.anim.to_right_end, R.anim.to_left_start, R.anim.to_left_end)
                        .replace(R.id.content_main, new addPill())
                        .commit();
            }
        });

        return view;
    }

    void updateUI(){
        appDatabase.getAppDatabase(getContext()).pillsDao().getAll().observe(this, new Observer<List<pill>>() {
            @Override
            public void onChanged(@Nullable List<pill> pills) {
                pillsList.clear();
                pillsList.addAll(pills);
                pillAdapter.notifyDataSetChanged();
            }
        });
    }
}
