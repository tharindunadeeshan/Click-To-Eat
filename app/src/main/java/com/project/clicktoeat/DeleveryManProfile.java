package com.project.clicktoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleveryManProfile extends AppCompatActivity {

    TextView name, address, phonenumber;
    Button deleteDetail;
    DatabaseReference dbref;

    Orders cr32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delevery_man_profile);

        name = findViewById(R.id.custName);
        address = findViewById(R.id.address);
        phonenumber = findViewById(R.id.phone);
        deleteDetail = findViewById(R.id.deletebutton);


        cr32 = new Orders();


        dbref = FirebaseDatabase.getInstance().getReference().child("Orders/o2");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {

                    address.setText(snapshot.child("adress").getValue().toString());
                    name.setText(snapshot.child("ccustomerName").getValue().toString());
                    phonenumber.setText(snapshot.child("phoneNumber").getValue().toString());


                } else {
                    Toast.makeText(DeleveryManProfile.this, "canot find", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LoadingDialog loadingDialog = new LoadingDialog(DeleveryManProfile.this);
        deleteDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbref = FirebaseDatabase.getInstance().getReference().child("Orders").child("o2");
                dbref.removeValue();
                Toast.makeText(DeleveryManProfile.this, "Delete Succssfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DeleveryManProfile.this, DeleveryHomePage.class);
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