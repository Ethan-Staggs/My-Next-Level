package com.hfad.mynextlevel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyMedication extends AppCompatActivity {
    ImageView logo;
    MedsDBHelper myDB;
    RecyclerView recyclerView;
    FloatingActionButton addMed;
    AddMedsAdapter medsAdapter;
    ArrayList med_id, med_name, med_mg, med_sched, my_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medication);

        getSupportActionBar().setTitle("My Medication List");

        logo = (ImageView) findViewById(R.id.logo);
        recyclerView = findViewById(R.id.medRecyclerView);
        addMed = findViewById(R.id.addMedButton);


        //creating new arraylists for each reminder input
        myDB = new MedsDBHelper(MyMedication.this);
        med_id = new ArrayList();
        med_name = new ArrayList();
        med_mg = new ArrayList();
        med_sched = new ArrayList();

        storeDataInArray();

        //Creating AddMedsAdapter object and passing the arrays as parameters
        medsAdapter = new AddMedsAdapter(MyMedication.this, this, med_id, med_name, med_mg, med_sched);
        recyclerView.setAdapter(medsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyMedication.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

        }
    }

    void storeDataInArray() {
        Cursor cursor = myDB.readData();
        //if no data present, display "no data" toast
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            //else, add arraylist at specified index
        } else {
            while (cursor.moveToNext()) {
                med_id.add(cursor.getString(0));
                med_name.add(cursor.getString(1));
                med_mg.add(cursor.getString(2));
                med_sched.add(cursor.getString(3));

            }
        }
    }

    public void onLogoClick(View view) {
        Intent intent = new Intent(MyMedication.this, MainActivity.class);
        startActivity(intent);
    }

    public void onAddMedClick(View view) {
        Intent intent = new Intent(MyMedication.this, AddMedication.class);
        startActivity(intent);
    }
}
