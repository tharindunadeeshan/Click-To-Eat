package com.project.clicktoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.project.clicktoeat.ADP.AdminHome;

public class Login extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;
    Switch active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        //hooks
        callSignUp = findViewById(R.id.signup_screen);
        image = findViewById(R.id.logoImage);
        logoText = findViewById(R.id.logoname);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.Login_btn);
        active = findViewById(R.id.active);

        LoadingDialog loadingDialog = new LoadingDialog(Login.this);
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
                pairs[3] = new Pair<View, String>(username, "username_tran");
                pairs[4] = new Pair<View, String>(password, "Password_tran");
                pairs[5] = new Pair<View, String>(login_btn, "button_tran");
                pairs[6] = new Pair<View, String>(callSignUp, "login_signup_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle());

                }
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUserName() | !validatePassword()) {
                    return;
                }
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String input1 = username.getEditText().getText().toString();
                        String input2 = password.getEditText().getText().toString();


                        if (snapshot.child(input1).exists()) {

                            if (snapshot.child(input1).child("password").getValue(String.class).equals(input2)) {

                                if (active.isChecked()) {
                                    if (snapshot.child(input1).child("as").equals("admin")) {

                                        preferences.setDataLogin(Login.this, true);
                                        preferences.setDataAs(Login.this, "admin");

                                        startActivity(new Intent(Login.this, Admin_Home.class));

                                    } else if (snapshot.child(input1).child("as").getValue(String.class).equals("user")) {
                                        preferences.setDataLogin(Login.this, true);
                                        preferences.setDataAs(Login.this, "user");
                                        startActivity(new Intent(Login.this, HomePage.class));
                                        loadingDialog.starttLoadingDialog();

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                loadingDialog.dismissDialog();
                                            }
                                        }, 5000);

                                        pushNotification("Succsess", "Welcome Click TO Eat");


                                    }
                                } else {
                                    if (snapshot.child(input1).child("as").getValue(String.class).equals("admin")) {

                                        preferences.setDataLogin(Login.this, false);
                                        startActivity(new Intent(Login.this, Admin_Home.class));

                                    } else if (snapshot.child(input1).child("as").getValue(String.class).equals("user")) {
                                        preferences.setDataLogin(Login.this, false);
                                        startActivity(new Intent(Login.this, HomePage.class));


                                        loadingDialog.starttLoadingDialog();

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                loadingDialog.dismissDialog();
                                            }
                                        }, 5000);

                                        pushNotification("succsess", "Welcome Click TO Eat");


                                    }

                                }


                            } else {
                                Toast.makeText(Login.this, "data send 1 slah", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Login.this, "Data belum terdaftar", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataAs(this).equals("admin")) {
                startActivity(new Intent(Login.this, Admin_Home.class));
                finish();
            } else if (preferences.getDataAs(this).equals("user")) {
                startActivity(new Intent(Login.this, HomePage.class));

                finish();
            }
        }
    }

    private Boolean validateUserName() {
        String val = username.getEditText().getText().toString();

        if (val.isEmpty()) {
            username.setError("Filed cannot be Empty ");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if (val.isEmpty()) {
            password.setError("Filed cannot be Empty ");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private void pushNotification(String title, String body) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.clicktoeat)
                .build();

        notificationManager.notify(0, notification);
    }


}
