package com.hfad.mynextlevel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * inflating menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Sign user out and display toast if "sign out" menu item is clicked.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(task -> {
                Toast.makeText(MainActivity.this, "Signed Out Successfully", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
        return true;

    }

    public void onMyDrClick(View view) {
        AlertDialog.Builder myDia = new AlertDialog.Builder(MainActivity.this);
        myDia.setTitle("Enter Medical Condition to Find a Doctor in Your Area");


        final EditText zipCode = new EditText(MainActivity.this);
        zipCode.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        myDia.setView(zipCode);

        myDia.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com/search?q=" + zipCode.getText() + " doctor in my area")));

            }
        });
        myDia.show();
    }

    public void onSetReminderClick(View view) {
        Intent intent = new Intent(this, SetReminders.class);
        startActivity(intent);
    }

    public void onHealthInfoClick(View view) {
        Intent intent = new Intent(MainActivity.this, HealthTips.class);
        startActivity(intent);
    }

    public void onMyRemindersClick(View view) {
        Intent intent = new Intent(MainActivity.this, MyReminders.class);
        startActivity(intent);
    }

    public void onMyMedicationClick(View view) {
        Intent intent = new Intent(this, MyMedication.class);
        startActivity(intent);
    }

    public void onOtherResourcesClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.cdc.gov/"));
        startActivity(intent);
    }

    public void onFaceBookClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://facebook.com"));
        startActivity(intent);
    }

    public void onInstagramClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://instagram.com"));
        startActivity(intent);
    }

    public void onTwitterClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://twitter.com"));
        startActivity(intent);
    }
}