package com.example.newpc.qrcode;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_LIST = "lists";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_LIST + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT"
                 + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);

        // Create tables again
        onCreate(db);
    }

    // code to add the new item
    void addList(Lists list){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, list.getName()); // List Name

        db.insert(TABLE_LIST, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }

    // code to get the single list
    Lists getList(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LIST, new String[] { KEY_ID,
                        KEY_NAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Lists list = new Lists(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return list;
    }


    // code to get all items in a list view
    public List<Lists> getAllLists() {
        List<Lists> groceryList = new ArrayList<Lists>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LIST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Lists lists = new Lists();
                lists.setID(Integer.parseInt(cursor.getString(0)));
                lists.setName(cursor.getString(1));

                // Adding contact to list
                groceryList.add(lists);
            } while (cursor.moveToNext());
        }

        // return contact list
        return groceryList;
    }



    // code to update the single item
    public int updateContact(Lists lists) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, lists.getName());

        // updating row
        return db.update(TABLE_LIST, values, KEY_ID + " = ?",
                new String[] { String.valueOf(lists.getID()) });
    }

    //Deleting single List
    public void deleteList(Lists lists) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIST, KEY_ID + " = ?",
                new String[] { String.valueOf(lists.getID()) });
        db.close();
    }

    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
