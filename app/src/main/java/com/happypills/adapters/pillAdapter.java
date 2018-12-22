package com.happypills.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.happypills.R;
import com.happypills.db.objects.pill;
import com.happypills.db.appDatabase;

import java.util.List;

public class pillAdapter extends RecyclerView.Adapter {

    List<pill> pills;
    Context context;

    public pillAdapter(List<pill> pills, Context context) {
        this.pills = pills;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        TextView name = viewHolder.itemView.findViewById(R.id.name);
        TextView quantity = viewHolder.itemView.findViewById(R.id.quantity);
        RelativeLayout plus = viewHolder.itemView.findViewById(R.id.plus);
        RelativeLayout minus = viewHolder.itemView.findViewById(R.id.minus);
        RelativeLayout info = viewHolder.itemView.findViewById(R.id.info);

        name.setText(pills.get(i).getName());
        quantity.setText(pills.get(i).getQuantity() + " PILLS LEFT");

        Resources resources = viewHolder.itemView.getResources();
        int q = pills.get(i).getQuantity();
        if (q == 0) quantity.setTextColor(resources.getColor(R.color.no_pills_color));
        else if (q <= 4) quantity.setTextColor(resources.getColor(R.color.under_4));
            else quantity.setTextColor(resources.getColor(R.color.over_4));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pills.get(i).setQuantity(pills.get(i).getQuantity() + 1);
                appDatabase.getAppDatabase(viewHolder.itemView.getContext()).pillsDao().updatePill(pills.get(i));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pills.get(i).setQuantity(pills.get(i).getQuantity() - 1);
                appDatabase.getAppDatabase(viewHolder.itemView.getContext()).pillsDao().updatePill(pills.get(i));
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View info = layoutInflater.inflate(R.layout.modal_info_pill, null);
                builder.setView(info);
                final AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView title = info.findViewById(R.id.title);
                TextView quantity = info.findViewById(R.id.quantity);
                TextView comment = info.findViewById(R.id.comment);
                TextView expiration = info.findViewById(R.id.expiration);

                title.setText(pills.get(i).getName());
                quantity.setText(pills.get(i).getQuantity() + " pills left.");
                if (pills.get(i).getExpiration() != 0) expiration.setText(pills.get(i).getExpiration() + "");
                else expiration.setVisibility(View.GONE);
                comment.setText(pills.get(i).getComment());

                RelativeLayout close = info.findViewById(R.id.close);
                RelativeLayout delete = info.findViewById(R.id.delete);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        appDatabase.getAppDatabase(context).pillsDao().deletePill(pills.get(i));
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_pill, viewGroup, false);
        return new pillViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return pills.size();
    }

    public class pillViewHolder extends RecyclerView.ViewHolder{

        TextView name, quantity;
        RelativeLayout plus, minus, info;

        public pillViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            quantity = itemView.findViewById(R.id.quantity);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            info = itemView.findViewById(R.id.info);
        }
    }
}
