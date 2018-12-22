package com.happypills.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.happypills.R;
import com.happypills.db.appDatabase;
import com.happypills.db.objects.doctor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class addDoctor extends Fragment {

    @BindView(R.id.doctor_name_tv) TextView doctor_name_tv;
    @BindView(R.id.doctor_specialization_tv) TextView doctor_spectialization_tv;
    @BindView(R.id.doctor_phone_tv) TextView doctor_phone_tv;


    public addDoctor() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_doctor, container, false);
        ButterKnife.bind(this,view);
        getActivity().findViewById(R.id.fab).setVisibility(View.GONE);

        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!doctor_name_tv.getText().toString().isEmpty() &&
                        !doctor_spectialization_tv.getText().toString().isEmpty() &&
                        !doctor_phone_tv.getText().toString().isEmpty()){

                    doctor doctor = new doctor();
                    doctor.setName(doctor_name_tv.getText().toString());
                    doctor.setSpecialization(doctor_spectialization_tv.getText().toString());
                    doctor.setNumber(doctor_phone_tv.getText().toString());
                    appDatabase.getAppDatabase(getContext()).pillsDao().insertDoctor(doctor);
                    getActivity().getSupportFragmentManager().popBackStack();
                } else if(doctor_name_tv.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Name field is empty!", Toast.LENGTH_SHORT).show();
                } else if(doctor_spectialization_tv.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Specialization field is empty!", Toast.LENGTH_SHORT).show();
                } else if(doctor_phone_tv.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Phone field is empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

}
