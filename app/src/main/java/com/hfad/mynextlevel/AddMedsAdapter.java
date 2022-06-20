package com.hfad.mynextlevel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddMedsAdapter extends RecyclerView.Adapter<AddMedsAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    MedsDBHelper myDB;
    private ArrayList row_id, med_title, med_mg, med_sched;


    public AddMedsAdapter(Activity activity, Context context,
                          ArrayList row_id,
                          ArrayList med_title,
                          ArrayList med_mg,
                          ArrayList med_sched
    ) {
        this.activity = activity;
        this.context = context;
        this.row_id = row_id;
        this.med_title = med_title;
        this.med_mg = med_mg;
        this.med_sched = med_sched;

    }


    @NonNull
    @Override
    public AddMedsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_meds_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddMedsAdapter.MyViewHolder holder, int position) {

        holder.rowId.setText(String.valueOf(row_id.get(position)));
        holder.med_title.setText(String.valueOf(med_title.get(position)));
        holder.med_mg.setText(String.valueOf(med_mg.get(position)));
        holder.med_sched.setText(String.valueOf(med_sched.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(row_id.get(holder.getAdapterPosition())));
                intent.putExtra("title", String.valueOf(med_title.get(holder.getAdapterPosition())));
                intent.putExtra("mg", String.valueOf(med_mg.get(holder.getAdapterPosition())));
                intent.putExtra("schedule", String.valueOf(med_sched.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);
            }
        });
        holder.meds_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB = new MedsDBHelper(context);

                myDB.deleteMedsData((String) med_title.get(holder.getAdapterPosition()));
                med_title.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getLayoutPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return row_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rowId, med_title, med_mg, med_sched;
        Button meds_delete;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rowId = itemView.findViewById(R.id.row_id);
            med_title = itemView.findViewById(R.id.med_title);
            med_mg = itemView.findViewById(R.id.med_mg);
            med_sched = itemView.findViewById(R.id.med_schedule);
            meds_delete = itemView.findViewById(R.id.med_delete);
            mainLayout = itemView.findViewById(R.id.meds_main_layout);
        }
    }
}

