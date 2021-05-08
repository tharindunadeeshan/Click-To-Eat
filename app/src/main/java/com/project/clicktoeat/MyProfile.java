package com.project.clicktoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MyProfile extends AppCompatActivity {

    TextInputLayout fullName, email,password,phoneNo;
    TextView fullNameLabel,usernameLablel;
    Button btn_update;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_my_profile);

        //Hooks
        fullName = findViewById(R.id.fullName_Profile);
        email = findViewById(R.id.email_profile);
        password= findViewById(R.id.password_profile);
        phoneNo = findViewById(R.id.phoneNO_profile);
        fullNameLabel = findViewById(R.id.full_name_profile);
        usernameLablel = findViewById(R.id.full_name_profile);
        btn_update  = findViewById(R.id.update_profile);

        //Show all Data
        showAlldata();

    }
    private  void  showAlldata(){
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");



        fullNameLabel.setText(user_name);
        usernameLablel.setText(user_name);
        fullName.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phoneNo);
        password.getEditText().setText(user_password);


    }

}