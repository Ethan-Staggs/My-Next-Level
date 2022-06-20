package com.hfad.mynextlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMedication extends AppCompatActivity {

    EditText medTitle;
    EditText medmg;
    EditText medSched;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);


        medTitle = findViewById(R.id.medicationTitle);
        medmg = findViewById(R.id.medicationMG);
        medSched = findViewById(R.id.medSchedule);


    }

    /**
     * Adding medication to the medication list
     */

    public void addMed(View view) {
        MedsDBHelper myDB = new MedsDBHelper(AddMedication.this);
        myDB.addMedication(medTitle.getText().toString(), medmg.getText().toString(), medSched.getText().toString());
        Intent intent = new Intent(AddMedication.this, MyMedication.class);
        startActivity(intent);
    }
}