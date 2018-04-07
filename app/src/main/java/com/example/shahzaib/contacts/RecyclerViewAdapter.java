package com.example.shahzaib.contacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Cursor cursor;


    public RecyclerViewAdapter(Cursor cursor)
    {
        this.cursor = cursor;
    }



    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setItemData(holder,position);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    private void setItemData(RecyclerViewAdapter.ViewHolder holder, int position)
    {
        cursor.moveToPosition(position);
        holder.nameTV.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.ContractContactsDB.COLUMN_CONTACT_NAME)));
        holder.numberTv.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.ContractContactsDB.COLUMN_CONTACT_NUMBER)));

        // following will help to delete item on Swipe
        long id = cursor.getLong(cursor.getColumnIndex(DatabaseContract.ContractContactsDB._ID));
        holder.itemView.setTag(id);

    }








    /********   ViewHolder class *******/
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameTV, numberTv;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTV);
            numberTv = itemView.findViewById(R.id.numberTV);
        }
    }

    public void setCursor(Cursor cursor)
    {
        this.cursor = cursor;
        notifyDataSetChanged();
    }



}
