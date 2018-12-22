package com.happypills.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.happypills.R;
import com.happypills.db.objects.pill;
import com.happypills.db.appDatabase;

public class addPill extends Fragment {

    EditText name, quantity, comment;

    public addPill() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pill, container, false);
        getActivity().findViewById(R.id.fab).setVisibility(View.GONE);

        name = view.findViewById(R.id.name_tv);
        quantity = view.findViewById(R.id.quantity_tv);
        comment = view.findViewById(R.id.comment_quantity);

        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty() && !quantity.getText().toString().isEmpty()){
                    pill pill = new pill();
                    pill.setName(name.getText().toString());
                    pill.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    pill.setComment(comment.getText().toString());

                    appDatabase.getAppDatabase(getContext()).pillsDao().insert(pill); //new thread?

                    getActivity().getSupportFragmentManager().popBackStack();
                }else if (name.getText().toString().isEmpty() && !quantity.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Name field is empty!", Toast.LENGTH_SHORT).show();
                }else if(!name.getText().toString().isEmpty() && quantity.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Number of pills field is empty!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Name & number of pills fields are empty!", Toast.LENGTH_SHORT).show();
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
