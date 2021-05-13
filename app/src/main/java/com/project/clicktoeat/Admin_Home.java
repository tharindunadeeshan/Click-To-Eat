package com.project.clicktoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Home extends AppCompatActivity {

    Button btnFood,Adminuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);

        btnFood = findViewById(R.id.btn_adminhome_food);
        Adminuser = findViewById(R.id.btn_adminhome_users);


        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Admin_Food.class);
                startActivity(i);
//                finish();
            }
        });

        Adminuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DeleveryHomePage.class);
                startActivity(i);
//                finish();
            }
        });
    }

}