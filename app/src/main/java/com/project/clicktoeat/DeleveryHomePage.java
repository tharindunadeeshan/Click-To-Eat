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

public class DeleveryHomePage extends AppCompatActivity {

    TextView haddress, hphoneNumber, hcustomername;
    Button show_morebtn;
    DatabaseReference dbref;


    Orders or2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delevery_home_page);

        hcustomername = findViewById(R.id.homeCustomername);
        haddress = findViewById(R.id.homeaddress);
        hphoneNumber = findViewById(R.id.homePhoneNumber);
        show_morebtn = findViewById(R.id.btnshowMore);


        or2 = new Orders();


        LoadingDialog loadingDialog = new LoadingDialog(DeleveryHomePage.this);

        dbref = FirebaseDatabase.getInstance().getReference().child("Orders/o1");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {

                    haddress.setText(snapshot.child("adress").getValue().toString());
                    hcustomername.setText(snapshot.child("ccustomerName").getValue().toString());
                    hphoneNumber.setText(snapshot.child("phoneNumber").getValue().toString());


                } else {
                    Toast.makeText(DeleveryHomePage.this, "canot find", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        show_morebtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DeleveryHomePage.this, DeliveryMyOrders.class);
                startActivity(intent);

                loadingDialog.starttLoadingDialog();

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