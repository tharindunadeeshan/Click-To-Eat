package com.project.clicktoeat;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MyProfile extends AppCompatActivity {
  TextView Username,emnail,phonenumber;
    Button editp,deletep;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_my_profile);


        Username = findViewById(R.id.etUserNameEP);
        emnail = findViewById(R.id.etUserNameEP);
        phonenumber = findViewById(R.id.etUserNameEP);
        Username = findViewById(R.id.etUserNameEP);
        editp = findViewById(R.id.update_profile);
        deletep = findViewById(R.id.DeleteAccountBtn);

        LoadingDialog loadingDialog = new LoadingDialog(MyProfile.this);
        editp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MyProfile.this,EditMyProfile.class);
                startActivity(intent);
            }
        });

        deletep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbref = FirebaseDatabase.getInstance().getReference().child("User").child("1");
                dbref.removeValue();
                Toast.makeText(MyProfile.this, "Delete Succssfully", Toast.LENGTH_SHORT).show();

                loadingDialog.starttLoadingDialog();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 5000);
            }
        });

    }
    public void logout(View view){
        startActivity(new Intent(MyProfile.this,Login.class));

        preferences.clearData(this);

        finish();
    }
}