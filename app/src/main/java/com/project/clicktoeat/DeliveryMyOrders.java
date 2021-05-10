package com.project.clicktoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeliveryMyOrders extends AppCompatActivity {

 TextView address,price,customername,phonenumber,qty,foodname;
 Button show_btn,btncancel,btncom;
 DatabaseReference dbref;
 Orders or1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delivery_my_orders);

        customername = findViewById(R.id.customerNameO);
        price = findViewById(R.id.totalprice);
        address = findViewById(R.id.adressO);
        phonenumber = findViewById(R.id.phone_numberO);
        address = findViewById(R.id.adressO);
        qty = findViewById(R.id.foodQtyO);
        foodname = findViewById(R.id.foodNameO);
        show_btn = findViewById(R.id.show_btton);
        btncancel = findViewById(R.id.deleveryCancel);
        btncom = findViewById(R.id.deleveryComplte);

        LoadingDialog loadingDialog = new LoadingDialog(DeliveryMyOrders.this);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DeliveryMyOrders.this,DeliveryCancelInput.class);
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

        show_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent =new Intent(DeliveryMyOrders.this,DeleveryHomePage.class);
                startActivity(intent);

                pushNotification("Delevery Start", "Starting.......");

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


        or1 = new Orders();


        dbref = FirebaseDatabase.getInstance().getReference().child("Orders/o1");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {

                    address.setText(snapshot.child("adress").getValue().toString());
                    customername.setText(snapshot.child("ccustomerName").getValue().toString());
                    phonenumber.setText(snapshot.child("phoneNumber").getValue().toString());
                    qty.setText(snapshot.child("foodQuantity").getValue().toString());
                    foodname.setText(snapshot.child("foodName").getValue().toString());
                    price.setText(snapshot.child("totalPrice").getValue().toString());


                }
                else{
                    Toast.makeText(DeliveryMyOrders.this, "canot find", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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