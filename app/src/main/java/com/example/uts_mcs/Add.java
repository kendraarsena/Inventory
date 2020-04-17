package com.example.uts_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Add extends AppCompatActivity {
    EditText nama, desc, qty;
    Button submitBtn;
    DBHelper dbHelper;
    String name2, des2, qty2;
    private ArrayList<Product> Products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();

        nama = findViewById(R.id.nama);
        desc = findViewById(R.id.desc);
        qty = findViewById(R.id.qty);
        submitBtn = findViewById(R.id.submit);

        dbHelper = new DBHelper(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name2 = nama.getText().toString();
                des2 = desc.getText().toString();
                qty2 = qty.getText().toString();
                if(name2.equals("") || des2.equals("") || qty2.equals("")){
                    Toast.makeText(Add.this, "Fill blank space(s)", Toast.LENGTH_SHORT).show();
                }
                else{
                    AddData(name2, des2, Integer.parseInt(qty2));
                    nama.setText("");
                    desc.setText("");
                    qty.setText("");
                    Intent i = new Intent(Add.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    public void AddData(String nama, String des, int qty){
        boolean insert = dbHelper.add(name2, des2, Integer.parseInt(qty2));
        if(insert == true) Toast.makeText(this, "Submit Successful", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Submit Failed", Toast.LENGTH_SHORT).show();
    }
}
