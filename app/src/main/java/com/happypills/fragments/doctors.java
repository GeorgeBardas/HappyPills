package com.happypills.fragments;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.happypills.R;
import com.happypills.db.appDatabase;
import com.happypills.db.objects.doctor;
import android.arch.lifecycle.LifecycleOwner;
import com.happypills.adapters.doctorAdapter;
import com.happypills.db.objects.doctor;

import java.util.ArrayList;
import java.util.List;

public class doctors extends Fragment {

    RecyclerView recyclerView;
    List<doctor> doctorsList;
    LinearLayoutManager layoutManager;
    doctorAdapter doctorAdapter;

    public doctors() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctors, container, false);

        doctorsList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.doctor_recycler_view);
        doctorAdapter = new doctorAdapter(doctorsList, getContext());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(doctorAdapter);

        getActivity().findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("doctors")
                        .setCustomAnimations(R.anim.to_right_start, R.anim.to_right_end, R.anim.to_left_start, R.anim.to_left_end)
                        .replace(R.id.content_main, new addDoctor())
                        .commit();
            }
        });

        appDatabase.getAppDatabase(getContext()).pillsDao().getAllDoctors().observe(this, new Observer<List<com.happypills.db.objects.doctor>>() {
            @Override
            public void onChanged(@Nullable List<com.happypills.db.objects.doctor> doctors) {
                doctorsList.clear();
                doctorsList.addAll(doctors);
                doctorAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

}
