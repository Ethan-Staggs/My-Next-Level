package com.hfad.mynextlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText mMedTitle, MmedMg, MmedSchedule;

    String mId, mTitle, Mmg, Mschedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mMedTitle = findViewById(R.id.medicationTitle2);
        MmedMg = findViewById(R.id.medicationMG2);
        MmedSchedule = findViewById(R.id.medSchedule2);

        getData();

    }

    //getting data from editTexts using explicit intents
    void getData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("mg") && getIntent().hasExtra("schedule")) {

            mId = getIntent().getStringExtra("id");
            mTitle = getIntent().getStringExtra("title");
            Mmg = getIntent().getStringExtra("mg");
            Mschedule = getIntent().getStringExtra("schedule");


            mMedTitle.setText(mTitle);
            MmedMg.setText(Mmg);
            MmedSchedule.setText(Mschedule);

        }
    }

    public void onUpdateClick(View view) {
        MedsDBHelper medsDB = new MedsDBHelper(UpdateActivity.this);
        mTitle = mMedTitle.getText().toString().trim();
        Mmg = MmedMg.getText().toString().trim();
        Mschedule = MmedSchedule.getText().toString().trim();

        medsDB.updateData(mId, mTitle, Mmg, Mschedule);
        recreate();
    }
}