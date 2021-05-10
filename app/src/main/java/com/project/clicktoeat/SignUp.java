package com.project.clicktoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    TextInputLayout regName, regUsername, regEmail, regphoneNO, regPassword, regRepassword;
    Button regBtn, regloginbtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

        //Hooks  to all  element In Activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regphoneNO = findViewById(R.id.reg_phonenum);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regloginbtn = findViewById(R.id.reg_login_btn);
        //

        LoadingDialog loadingDialog = new LoadingDialog(SignUp.this);

        //

        //Save data In FireBase on Button Click
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validateUserName() | !validateEmail() | !validatePhoneNo() | !validatePassword()) {
                    return;
                }
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");


                // get All the Value in String
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNO = regphoneNO.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelper helperClass = new UserHelper(name, username, email, phoneNO, password);
                reference.child(phoneNO).setValue(helperClass);


                Intent intent = new Intent(SignUp.this, Login.class);
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


    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Filed cannot be Empty ");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUserName() {
        String val = regUsername.getEditText().getText().toString();
        String noWhitwSpase = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Filed cannot be Empty ");
            return false;
        } else if (val.length() > 15) {
            regUsername.setError("username too long ");
            return false;
        } else if (!val.matches(noWhitwSpase)) {
            regUsername.setError("white spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailpatten = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Filed cannot be Empty ");
            return false;
        } else if (!val.matches(emailpatten)) {
            regEmail.setError("invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regphoneNO.getEditText().getText().toString();

        if (val.isEmpty()) {
            regphoneNO.setError("Filed cannot be Empty ");
            return false;
        } else {
            regphoneNO.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$";


        if (val.isEmpty()) {
            regPassword.setError("Filed cannot be Empty ");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("password is to weak");
            return false;
        } else {
            regPassword.setError(null);
            return true;
        }
    }


}


