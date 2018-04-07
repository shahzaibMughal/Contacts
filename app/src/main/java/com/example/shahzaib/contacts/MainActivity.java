package com.example.shahzaib.contacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    DatabaseHelper databaseHelper;

    EditText nameET, numberET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this);
        adapter = new RecyclerViewAdapter(getContacts());

        nameET = findViewById(R.id.nameET);
        numberET = findViewById(R.id.numberET);







        /***** Setting up recycler view*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        /******* Delete Item on Swipe */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // do nothing, we only care about onSwipe
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                deleteContact(id);
            }
        }).attachToRecyclerView(recyclerView);


    }


    public void createContact(View view)
    { // get the data from both edit texts and create a contact
        String name = nameET.getText().toString();
        String number = numberET.getText().toString();
        if(name.length()>0 && number.length()>0)
        {
            addContact(name,number);
            nameET.setText("");
            numberET.setText("");
        }
    }


    private void  updateAdapterCursor()
    {
        adapter.setCursor(getContacts());
    }






    /**********  database related functions */
    private void addContact(String name, String number)
    {
        // adding row of new contact in database
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ContractContactsDB.COLUMN_CONTACT_NAME,name);
        values.put(DatabaseContract.ContractContactsDB.COLUMN_CONTACT_NUMBER,number);

        db.insert(DatabaseContract.ContractContactsDB.TABLE_NAME,null,values);
        updateAdapterCursor();
    }

    private Cursor getContacts()
    {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        return db.query(DatabaseContract.ContractContactsDB.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.ContractContactsDB._ID);
    }

    private void deleteContact(long id)
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int result = db.delete(DatabaseContract.ContractContactsDB.TABLE_NAME, DatabaseContract.ContractContactsDB._ID+"="+id,null);
        if( result> 0)
        {
            updateAdapterCursor();
            adapter.notifyItemChanged((int)id);
            Toast.makeText(this, "Item Deleted Successfull", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Item Not Deleted", Toast.LENGTH_SHORT).show();
    }

}
