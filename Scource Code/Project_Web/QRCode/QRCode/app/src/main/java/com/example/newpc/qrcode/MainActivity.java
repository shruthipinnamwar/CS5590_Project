package com.example.newpc.qrcode;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button gen, scan, list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gen = (Button) findViewById(R.id.gen);
        scan = (Button) findViewById(R.id.scan);
        list = (Button) findViewById(R.id.list);
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gIntent = new Intent(MainActivity.this, GeneratorActivity.class);
                startActivity(gIntent);
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rIntent = new Intent(MainActivity.this, ReaderActivity.class);
                startActivity(rIntent);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lIntent = new Intent(MainActivity.this, GroceryListActivity.class);
                startActivity(lIntent);

            }
        });


    }
}
