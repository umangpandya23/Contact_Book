package com.example.contactbook;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager {
    private SQLiteDatabase db;

    public DataManager (Context context) {
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        db = helper.getWritableDatabase();

    }

    public Cursor selectAll() {

        Cursor cursor = null;
        String query = "select * from contact order by name";

        try {

            cursor = db.rawQuery(query, null);
            //Log.i ("info", "In Datamanager selectAll try statement");
        } catch (Exception e) {
            Log.i("info", "In DataManager selectAll method");
            Log.i("info", e.getMessage());
        }

        Log.i("info", "Loaded data " + cursor.getCount());
        return cursor;
    }

    public void insert(String name, String phone, String email, String address,
                       String city, String state, String zip, String contactType) {

        String query = "insert into contact" +
                "(name, phone, email, street_address, city, state, zip, contact_type) values " +
                "( '" + name + "', '" + phone + "', '" + email + "', '" + address + "','" + city +
                "', '" + state + "', '" + zip + "', '" + contactType + "' )";

        try {

            db.execSQL(query);
        } catch (SQLException e) {
            Log.i("info", "In DataManager insert method");
            Log.i("info", e.getMessage());
        }
        Log.i("info", "Added new contact " + name);
    }

    public void delete(String name, String phone) {

        String query = "delete from contact where name = '" + name + "' and phone = '" + phone + "'";

        try {

            db.execSQL(query);
        } catch (SQLException e) {
            Log.i("info", "In DataManager delete method");
            Log.i("info", e.getMessage());
        }
        Log.i("info", "deleted contact " + name);
    }

    public void update(String nameReplace, String phoneReplace, String name, String phone, String email, String address,
                       String city, String state, String zip, String contactType) {

        String query = "update contact set name = '" + name + "' , phone = '" + phone + "' , email = '" + email + "' , street_address = '" + address + "' , city = '" + city + "' , state = '" + state
                + "' , zip = '" + zip + "' , contact_type = '" + contactType + "' where name ='" + nameReplace + "' and phone = '" + phoneReplace + "'";

        try {

            db.execSQL(query);
        } catch (SQLException e) {
            Log.i("info", "In DataManager update method");
            Log.i("info", e.getMessage());
        }
        Log.i("info", "updated contact " + name);
    }

    private class MySQLiteOpenHelper extends SQLiteOpenHelper {
        public MySQLiteOpenHelper (Context context) {
            super(context, "contact_book", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String newTable = "create table contact ("
                    + "_id integer primary key autoincrement not null, "
                    + "name text not null, "
                    + "phone text, "
                    + "email text, "
                    + "street_address text, "
                    + "city text, "
                    + "state text, "
                    + "zip text, "
                    + "contact_type text)";


            try {
                db.execSQL(newTable);
            }
            catch (SQLException e) {
                Log.i ("info", "In MySQLiteOpenHelper class onCreate method");
                Log.i ("info", e.getMessage());
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //No code needed
        }
    }
}
