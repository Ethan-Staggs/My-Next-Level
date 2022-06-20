package com.hfad.mynextlevel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyReminders extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    ReminderDBHelper myDB;
    ArrayList<String> reminder_id, reminder_medication, reminder_location, reminder_title, deleteReminderBtn;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reminders);

        getSupportActionBar().setTitle("My Reminders");

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyReminders.this, SetReminders.class);
                startActivity(intent);
            }
        });

        //creating new arraylists for each reminder input
        myDB = new ReminderDBHelper(MyReminders.this);
        reminder_id = new ArrayList<>();
        reminder_medication = new ArrayList<>();
        reminder_location = new ArrayList<>();
        reminder_title = new ArrayList<>();
        deleteReminderBtn = new ArrayList<>();

        storeDataInArray();

        //Creating customAdapter object and passing the arrays as parameters
        customAdapter = new CustomAdapter(MyReminders.this, reminder_id, reminder_medication,
                reminder_location, reminder_title);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyReminders.this));
    }

    void storeDataInArray() {
        Cursor cursor = myDB.readData();

        //if no data present, display "no data" toast
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();

            //else, add arraylist at specified index
        } else {
            while (cursor.moveToNext()) {
                reminder_id.add(cursor.getString(0));
                reminder_title.add(cursor.getString(1));
                reminder_location.add(cursor.getString(2));
                reminder_medication.add(cursor.getString(3));

            }
        }
    }
}