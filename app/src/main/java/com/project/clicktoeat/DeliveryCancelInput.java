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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeliveryCancelInput extends AppCompatActivity {

    EditText reason;
    Button btncancelo;
    DatabaseReference dbref;

    OrdercancelReason or1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delivery_cancel_input);

        reason = findViewById(R.id.ordercancelReason);
        btncancelo = findViewById(R.id.btncncelbtn);

        LoadingDialog loadingDialog = new LoadingDialog(DeliveryCancelInput.this);
        or1 = new OrdercancelReason();
        btncancelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dbref = FirebaseDatabase.getInstance().getReference().child("CancelOrder");

                    if (TextUtils.isEmpty(reason.getText().toString())) {

                        Toast.makeText(DeliveryCancelInput.this, "empoty Reason", Toast.LENGTH_SHORT).show();
                    } else {

                        or1.setReason(reason.getText(), toString().trim());
                        dbref.child("reason1").setValue(or1);


                        Toast.makeText(DeliveryCancelInput.this, "Successfull Cancel", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(DeliveryCancelInput.this, DeleveryHomePage.class);
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
                } catch (NumberFormatException nfe) {

                    Toast.makeText(DeliveryCancelInput.this, "Invalid Insert", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }


}