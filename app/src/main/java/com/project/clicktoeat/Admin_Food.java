package com.project.clicktoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_Food extends AppCompatActivity {
    List<ModelFood> list = new ArrayList();
    ListView lvfood;
    Button btnAddFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__food);

        lvfood = findViewById(R.id.lv_allfooditems);
        btnAddFood = findViewById(R.id.btn_adminhome_food);

        list = new ArrayList<>();
        FirebaseConnect fb = new FirebaseConnect("food_table");

        // Read from the database
        fb.getMyRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.removeAll(list);
                list.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ModelFood food = postSnapshot.getValue(ModelFood.class);
                    list.add(food);
                }
                lvfood.setAdapter((ListAdapter) new Adapters(Admin_Food.this, list));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("not nice", "Failed to read value.", error.toException());
            }
        });

        //go to add new food item activity
        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Admin_AddFood.class);
                startActivity(i);
            }
        });


    }
}