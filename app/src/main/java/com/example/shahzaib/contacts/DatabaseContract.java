package com.example.shahzaib.contacts;

import android.provider.BaseColumns;

public class DatabaseContract {


    // constructor is private because we not want to make object of this class
    private DatabaseContract(){}

    public class ContractContactsDB implements BaseColumns
    {
        // no need to add _id column because it automatically come from BaseColumn interface
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_CONTACT_NAME = "Name";
        public static final String COLUMN_CONTACT_NUMBER = "Number";
    }


}
