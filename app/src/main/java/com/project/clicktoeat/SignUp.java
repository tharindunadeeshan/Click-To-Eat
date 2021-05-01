package com.project.clicktoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Vairaible
    TextInputLayout  regName,regEmail,regPassword,regRepassword;
    Button regBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

    }

    //Save data In FireBase on Button Click
    public void registerUser(View view){
/*
        // get All the Value in String
        String etUserNameSU =regName.getEditText().getText().toString();
        String etEmailSU =regEmail.getEditText().getText().toString();
        String etPasswordSU =regPassword.getEditText().getText().toString();
        String etRepassSU =regRepassword.getEditText().getText().toString();

        UserHelperClass helperClass =New UserHelperClass (etUserNameSU,etEmailSU,etPasswordSU,etRepassSU);
        reference.child(username).setValue(helperClass);
*/
    }




}