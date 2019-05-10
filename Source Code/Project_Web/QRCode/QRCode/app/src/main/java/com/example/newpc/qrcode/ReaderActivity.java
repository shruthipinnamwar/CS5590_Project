package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ReaderActivity extends AppCompatActivity {
    private Button scan_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();


                // remove this for real device
                DatabaseHandler db = new DatabaseHandler(this);
                Log.d("Insert: ", "Inserting ..");

                //  result.getContents()
//                db.addList(new Lists("Banana"));
//                db.addList(new Lists("Applae"));

            }
            else {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();


                DatabaseHandler db = new DatabaseHandler(this);
                Log.d("Insert: ", "Inserting ..");

                //  result.getContents()
                db.addList(new Lists(result.getContents()));
               // db.addList(new Lists("Apple"));



//     // SQL Lite insertion
//                SQLiteDatabase mydatabase = openOrCreateDatabase("your database name",MODE_PRIVATE,null);
//     // create table
//                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Grocery(Item VARCHAR,ID VARCHAR);");
//     // insertion into Grocery List
//                mydatabase.execSQL("INSERT INTO Grocery VALUES('banana','1');");
//    //Retrieve data from sqllite
//                 Cursor resultSet = mydatabase.rawQuery("Select * from Grocery",null);
//                resultSet.moveToFirst();
//                String item = resultSet.getString(0);
//                String ID = resultSet.getString(1);
//
//                Toast.makeText(this, item, Toast.LENGTH_LONG).show();

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
