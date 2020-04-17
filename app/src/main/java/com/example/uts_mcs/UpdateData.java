package com.example.uts_mcs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {
    private EditText nama, des, qty;
    private Button save;
    DBHelper dbHelper;

    private String selectedName, selectedDes, selectedQty;
    private int selectedID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        nama = findViewById(R.id.nama);
        des = findViewById(R.id.desc);
        qty = findViewById(R.id.qty);
        save = findViewById(R.id.save);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        selectedID = intent.getIntExtra("id", -1);
        selectedName = intent.getStringExtra("name");
        selectedDes = intent.getStringExtra("des");
        selectedQty = intent.getStringExtra("qty");

        nama.setText(selectedName);
        des.setText(selectedDes);
        qty.setText(selectedQty);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = nama.getText().toString();
                String newDes = des.getText().toString();
                String newQty = qty.getText().toString();
                if (newName.equals("") || newDes.equals("") || newQty.equals("")) {
                    Toast.makeText(UpdateData.this, "Fill blank space(s)", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbHelper.updateName(newName, newDes, newQty, selectedID, selectedName);
                    Intent intent = new Intent(UpdateData.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
