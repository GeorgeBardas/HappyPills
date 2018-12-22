package com.happypills.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.happypills.R;
import com.happypills.db.objects.doctor;

import java.util.List;

public class doctorAdapter extends RecyclerView.Adapter {

    List<doctor> doctors;
    Context context;

    public doctorAdapter(List<doctor> doctors, Context context) {
        this.doctors = doctors;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        TextView name = viewHolder.itemView.findViewById(R.id.doctor_name_tv);
        TextView phone = viewHolder.itemView.findViewById(R.id.doctor_phone_tv);
        TextView spectialization = viewHolder.itemView.findViewById(R.id.doctor_specialization_tv);
        ImageView call = viewHolder.itemView.findViewById(R.id.doctor_call_iv);
        ImageView msg = viewHolder.itemView.findViewById(R.id.doctor_msg_iv);

        name.setText(doctors.get(i).getName());
        phone.setText(doctors.get(i).getNumber());
        spectialization.setText(doctors.get(i).getSpecialization());

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + doctors.get(i).getNumber().trim()));
                context.startActivity(call);
            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msg = new Intent(Intent.ACTION_VIEW);
                msg.setData(Uri.parse("sms:" + doctors.get(i).getNumber().trim()));
                context.startActivity(msg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_doctor, viewGroup, false);
        return new doctorViewHolder(view);
    }

    public class doctorViewHolder extends RecyclerView.ViewHolder{

        TextView name, specialization, phone;
        ImageView call, msg;

        public doctorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.doctor_name_tv);
            phone = itemView.findViewById(R.id.doctor_phone_tv);
            specialization = itemView.findViewById(R.id.doctor_specialization_tv);
            call = itemView.findViewById(R.id.doctor_call_iv);
            msg = itemView.findViewById(R.id.doctor_msg_iv);
        }
    }
}
