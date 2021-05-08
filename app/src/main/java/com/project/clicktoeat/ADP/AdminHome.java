package com.project.clicktoeat.ADP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.clicktoeat.R;

public class AdminHome extends AppCompatActivity {
    Button btnFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        btnFood = findViewById(R.id.btn_adminhome_food);

        /*btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AdminFood.class);
                startActivity(i);
//                finish();
            }
        });*/
    }
}