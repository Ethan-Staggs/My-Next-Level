package com.hfad.mynextlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SetReminders extends AppCompatActivity {

    EditText mMedication;
    EditText mTitle;
    EditText mLocation;
    Button mAddEvent;
    ImageView mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminders);

        getSupportActionBar().setTitle("Set a Reminder");

        mLogo = findViewById(R.id.logo);
        mMedication = findViewById(R.id.Medication);
        mTitle = findViewById(R.id.Title);
        mLocation = findViewById(R.id.Location);
        mAddEvent = findViewById(R.id.AddEvent);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(SetReminders.this, MainActivity.class);
        startActivity(intent);
    }

    public void onLogoClick(View view) {
        Intent intent = new Intent(SetReminders.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Setting reminder using calendar
     */
    public void onAddReminderClick(View view) {

        //creating ReminderDBHelper object and passing string values as parameters
        ReminderDBHelper myDB = new ReminderDBHelper(SetReminders.this);
        myDB.addReminder(mTitle.getText().toString(),
                mLocation.getText().toString(),
                mMedication.getText().toString());

        //if all text fields are not empty, pass string values to calendar contract
        if (!mTitle.getText().toString().isEmpty() && !mLocation.getText().toString().isEmpty() &&
                !mMedication.getText().toString().isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE, mMedication.getText().toString());
            intent.putExtra(CalendarContract.Events.DESCRIPTION, mTitle.getText().toString());
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, mLocation.getText().toString());
            intent.putExtra(CalendarContract.Events.ALL_DAY, false);


            //if there is an app to handle request, start activity. Else, display toast
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(SetReminders.this, "Cannot support this action", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(SetReminders.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

        }
    }
}