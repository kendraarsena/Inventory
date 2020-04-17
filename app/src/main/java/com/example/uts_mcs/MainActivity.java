package com.example.uts_mcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import android.util.Log;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnNoteListener {
    private static final String TAG = "MainActivity";
    DBHelper dbHelper;

    ArrayList<Product> Products = new ArrayList<Product>();
    RecyclerView rvItems;
    RecyclerView.Adapter rvAdapter;
    RecyclerView.LayoutManager layoutManager;

    void init(){
        rvItems = findViewById(R.id.rvItem);
        rvAdapter = new MyAdapter(Products, this, this);
        layoutManager = new LinearLayoutManager(this);

        rvItems.setLayoutManager(layoutManager);
        rvItems.setAdapter(rvAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        dbHelper = new DBHelper(this);
        Cursor data = dbHelper.getListContents();
        if (data.getCount() == 0) Toast.makeText(this, "Zonk", Toast.LENGTH_SHORT).show();
        else {
            while (data.moveToNext()) {
                Products.add(new Product(data.getString(1), data.getString(2), data.getInt(3), data.getInt(0)));
            }
        }
        init();
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                String name = adapterView.getItemAtPosition(i).toString();
////                String des = adapterView.getItemAtPosition(i).toString();
////                String qty = adapterView.getItemAtPosition(i).toString();
//                String name = Products.get(i).getName();
//                String des = Products.get(i).getDesc();
//                int qty = Products.get(i).getQty();
//                Log.d(TAG, "onItemClick: You clicked on " + name);
//
//                Cursor data = dbHelper.getItemID(name); //get the id associated with that name
//                int itemID = -1;
//                while(data.moveToNext()){
//                    itemID = data.getInt(0);
//                }
//                if(itemID > -1){
//                    Log.d(TAG, "onItemClick: The id is " + itemID);
//                    Intent intent = new Intent(MainActivity.this, UpdateData.class);
//                    intent.putExtra("id", itemID);
//                    intent.putExtra("name", name);
//                    intent.putExtra("des", des);
//                    intent.putExtra("qty", qty);
//                    startActivity(intent);
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "error" + itemID, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addButton:{
                Intent i = new Intent(MainActivity.this, Add.class);
                startActivity(i);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onNoteClick(int position) {
        String name = Products.get(position).getName();
        String des = Products.get(position).getDesc();
        String qty = Products.get(position).getQty()+"";

        Cursor data = dbHelper.getItemID(name);
        int itemID = -1;
        while(data.moveToNext()){
            itemID = data.getInt(0);
        }
        if(itemID > -1){
            Intent intent = new Intent(MainActivity.this, UpdateData.class);
            intent.putExtra("id", itemID);
            intent.putExtra("name", name);
            intent.putExtra("des", des);
            intent.putExtra("qty", qty);
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "error" + itemID, Toast.LENGTH_SHORT).show();
        }
    }
}
