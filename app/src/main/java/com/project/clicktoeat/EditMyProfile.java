package com.project.clicktoeat;

import androidx.appcompat.app.AppCompatActivity;

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

public class EditMyProfile extends AppCompatActivity {
    EditText usernameEditP,emialEditP,pboneNumP;
    Button savebutn;
    DatabaseReference dbref;

    Users us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_my_profile);

        usernameEditP = findViewById(R.id.etUserNameEP);
        emialEditP = findViewById(R.id.etEmailEP);
        pboneNumP = findViewById(R.id.etPhoneEP);
        savebutn = findViewById(R.id.btnSaveEP);

        us = new Users();

        LoadingDialog loadingDialog = new LoadingDialog(EditMyProfile.this);
        savebutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref = FirebaseDatabase.getInstance().getReference();
                dbref.child("Users").child("1").setValue(usernameEditP.getText().toString().trim());
                dbref.child("User/1/username").setValue(usernameEditP.getText().toString().trim());
                dbref.child("Users").child("1").setValue(emialEditP.getText().toString().trim());
                dbref.child("User/1/email").setValue(emialEditP.getText().toString().trim());
                dbref.child("Users").child("1").setValue(pboneNumP.getText().toString().trim());
                dbref.child("User/1/phoneNo").setValue(pboneNumP.getText().toString().trim());

                Toast.makeText(EditMyProfile.this, "Successfully  update", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(EditMyProfile.this,MyProfile.class);
                startActivity(intent);


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

}