package com.project.clicktoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_food);
    }

    public void logout(View view){
        startActivity(new Intent(HomeFood.this,Login.class));

        preferences.clearData(this);

        finish();
    }

}