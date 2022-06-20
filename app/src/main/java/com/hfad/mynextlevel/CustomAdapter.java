package com.hfad.mynextlevel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList reminder_id, reminder_title, reminder_location, reminder_medication;
    ReminderDBHelper mydb;
    Button reminderDelete;


    public CustomAdapter(Context context,
                         ArrayList reminder_id,
                         ArrayList reminder_title,
                         ArrayList reminder_location,
                         ArrayList reminder_medication) {
        this.context = context;
        this.reminder_id = reminder_id;
        this.reminder_title = reminder_title;
        this.reminder_location = reminder_location;
        this.reminder_medication = reminder_medication;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.reminder_id.setText(String.valueOf(reminder_id.get(position)));
        holder.reminder_title.setText(String.valueOf(reminder_title.get(position)));
        holder.reminder_location.setText(String.valueOf(reminder_location.get(position)));
        holder.reminder_medication.setText(String.valueOf(reminder_medication.get(position)));
        holder.reminderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb = new ReminderDBHelper(context);


                mydb.deleteData((String) reminder_id.get(holder.getAdapterPosition()));
                reminder_id.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getLayoutPosition());
                reminder_id.equals(0);
            }
        });


    }

    @Override
    public int getItemCount() {
        return reminder_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView reminder_id, reminder_title, reminder_location, reminder_medication;
        Button reminderDelete;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reminderDelete = itemView.findViewById(R.id.reminder_delete);
            reminder_id = itemView.findViewById(R.id.reminder_id);
            reminder_title = itemView.findViewById(R.id.reminder_title);
            reminder_location = itemView.findViewById(R.id.reminder_location);
            reminder_medication = itemView.findViewById(R.id.reminder_medication);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

    }
}
