package com.project.clicktoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Admin_AddFood extends AppCompatActivity {
    Button save, goback;
    EditText id, title, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add_food);

        save = findViewById(R.id.btn_admin_addfood);
        goback = findViewById(R.id.btn_admin_goback);
        id = findViewById(R.id.et_admin_addfood_id);
        title = findViewById(R.id.et_admin_addfood_title);
        price = findViewById(R.id.et_admin_addfood_price);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseConnect firebaseConnect = new FirebaseConnect("food_table");

                String id_string = id.getText().toString();
                String title_string = title.getText().toString();
                int price_int = Integer.parseInt(price.getText().toString());

                ModelFood modelFood = new ModelFood(id_string, "no image", price_int, title_string);

                firebaseConnect.getMyRef().child(id_string).setValue(modelFood);
            }
        });

//        id.setText("bre");

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Admin_AddFood.this, Admin_Food.class);
                finish();
                startActivity(i);
            }
        });

    }
}