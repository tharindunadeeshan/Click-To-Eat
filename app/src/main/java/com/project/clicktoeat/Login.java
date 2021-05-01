package com.project.clicktoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    Button callSignUp,login_btn;
    ImageView image;
    TextView logoText,sloganText;
    TextInputLayout username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        //hooks
        callSignUp =findViewById(R.id.signup_screen);
        image =findViewById(R.id.logoImage);
        logoText=findViewById(R.id.logoname);
        sloganText=findViewById(R.id.slogan_name);
        username =findViewById(R.id.username);
        password =findViewById(R.id.password);
        login_btn =findViewById(R.id.Login_btn);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,SignUp.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View ,String>(image,"logo_image");
                pairs[1] = new Pair<View ,String>(logoText,"logo_text");
                pairs[2] = new Pair<View ,String>(sloganText,"logo_desc");
                pairs[3] = new Pair<View ,String>(username,"username_tran");
                pairs[4] = new Pair<View ,String>(password,"Password_tran");
                pairs[5] = new Pair<View ,String>(login_btn,"button_tran");
                pairs[6] = new Pair<View ,String>(callSignUp,"login_signup_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }
}