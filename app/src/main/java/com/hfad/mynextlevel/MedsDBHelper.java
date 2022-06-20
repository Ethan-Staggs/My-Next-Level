package com.hfad.mynextlevel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MedsDBHelper extends SQLiteOpenHelper {

    Context context;

    private static final String DATABASE_NAME = "medsList.db";
    private static final String COLUMN_ID = "_ID";
    private static final String TABLE_NAME = "My_Medications";
    private static final String COLUMN_TITLE = "Medication_Name";
    private static final String COLUMN_MG = "Medication_MG";
    private static final String COLUMN_SCHEDULE = "Medication_Schedule";

    public MedsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        String queryo = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_MG + " TEXT, " +
                COLUMN_SCHEDULE + " TEXT);";
        myDB.execSQL(queryo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists " + TABLE_NAME);
        onCreate(myDB);
    }

    public void addMedication(String title, String medsMG, String sched) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_MG, medsMG);
        cv.put(COLUMN_SCHEDULE, sched);
        long result = myDB.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor cursor = null;
        if (myDB != null) {
            cursor = myDB.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteMedsData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, " Medication_Name = ?", new String[]{id});
        db.close();
    }

    void updateData(String rowId, String title, String mg, String schedule) {
        SQLiteDatabase medsDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_MG, mg);
        cv.put(COLUMN_SCHEDULE, schedule);

        long result = medsDB.update(TABLE_NAME, cv, " _ID = ?", new String[]{rowId});
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show();
        }
    }
}
