package com.project.clicktoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFood extends AppCompatActivity {

    /*private FirebaseUser user;
    private DatabaseReference reference;

    private  String userId;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_food);

/*

        user =FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        final TextView fullNameTextView =(TextView) findViewById(R.id.fullname);
        final TextView emailTextView =(TextView) findViewById(R.id.emailAddress);
        final TextView passwordTextView =(TextView) findViewById(R.id.password);


        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users  userProfile = snapshot.getValue(Users.class);

                if (userProfile !=null){
                    String fullname = userProfile.getName();
                    String email = userProfile.getEmail();
                    String password = userProfile.getPassword();


                    fullNameTextView.setText(fullname);
                    emailTextView.setText(email);
                    passwordTextView.setText(password);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(HomeFood.this,"somthing wrong",Toast.LENGTH_LONG).show();

            }
        });*/


    }



    public void logout(View view){
        startActivity(new Intent(HomeFood.this,Login.class));

        preferences.clearData(this);

        finish();
    }





}