package com.example.newpc.qrcode;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import static android.icu.util.TimeUnit.values;


public class GroceryListActivity extends AppCompatActivity {
    private Button list_btn;

    // String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
       //     "Blackberry", "WebOS", "Ubuntu" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocerylist);
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<Integer> values1 = new ArrayList<Integer>();
        ListView listView1 = (ListView) findViewById(R.id.listview);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, values);
//        listView1.setAdapter(adapter);


        DatabaseHandler db = new DatabaseHandler(this);
        // Read all List
        Log.d("Reading: ", "Reading all contacts..");
        List<Lists> contacts = db.getAllLists();


        for (Lists cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() ;
            // Writing Contacts to log
            Log.d("Name: ", log);
          //  values = new String[]{cn.getName()};
            values.add(cn.getName());
            values.add(Integer.toString(cn.getID()));

            //  Toast.makeText(this, log, Toast.LENGTH_LONG).show();


        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        listView1.setAdapter(adapter);




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
                // SQL Lite insertion
                SQLiteDatabase mydatabase = openOrCreateDatabase("QRCode",MODE_PRIVATE,null);
                // create table
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Grocery(Item VARCHAR,ID VARCHAR);");
                // insertion into Grocery List
                mydatabase.execSQL("INSERT INTO Grocery VALUES('banana','1');");
                //Retrieve data from sqllite
                Cursor resultSet = mydatabase.rawQuery("Select * from Grocery",null);
                resultSet.moveToFirst();
                String item = resultSet.getString(0);
                String ID = resultSet.getString(1);




                Toast.makeText(this, item, Toast.LENGTH_LONG).show();

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}



























//                // SQL Lite insertion
//                SQLiteDatabase mydatabase = openOrCreateDatabase("QRCode",MODE_PRIVATE,null);
//                // create table
//                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Grocery(Item VARCHAR,ID VARCHAR);");
//                // insertion into Grocery List
//                mydatabase.execSQL("INSERT INTO Grocery VALUES('banana','1');");
//                //Retrieve data from sqllite
//                Cursor resultSet = mydatabase.rawQuery("Select * from Grocery",null);
//                resultSet.moveToFirst();
//                String item = resultSet.getString(0);
//                String ID = resultSet.getString(1);
//
//                Toast.makeText(this, item+ID, Toast.LENGTH_LONG).show();

