package com.example.shahzaib.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE_QUERY = "CREATE TABLE "+ DatabaseContract.ContractContactsDB.TABLE_NAME +
                "(" +
                DatabaseContract.ContractContactsDB._ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.ContractContactsDB.COLUMN_CONTACT_NAME+" TEXT NOT NULL," +
                DatabaseContract.ContractContactsDB.COLUMN_CONTACT_NUMBER+" TEXT NOT NULL" +
                ");";

        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.ContractContactsDB.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
