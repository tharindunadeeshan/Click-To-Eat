/*package com.project.clicktoeat.ADMINISTRATOR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.clicktoeat.FirebaseConnect;
import com.project.clicktoeat.R;

public class AdminFoodView extends AppCompatActivity {
    Button edit, delete, goback;
    EditText id, title, price;
    ImageView image;
    Bundle bundle;
    String image_bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_food_view);

        edit = findViewById(R.id.btn_admin_view_edit);
        delete = findViewById(R.id.btn_admin_view_delete);
        goback = findViewById(R.id.btn_admin_view_goback);

        id = findViewById(R.id.et_admin_view_id);
        title = findViewById(R.id.et_admin_view_title);
        price = findViewById(R.id.et_admin_view_price);
        image = findViewById(R.id.iv_admin_view_image);

        bundle = getIntent().getExtras();
        String id_bundle = bundle.getString("id_key", "null");
        String title_bundle = bundle.getString("title_key", "null");
        int price_bundle = bundle.getInt("price_key", 0);
        image_bundle = bundle.getString("image_URL_key", "");
        if (image_bundle.equals("no image"))
            image_bundle = "https://static.wikia.nocookie.net/nopixel/images/b/b4/Not-found-image-15383864787lu.jpg/revision/latest/scale-to-width-down/310?cb=20200910062142";

        id.setText(id_bundle);
        title.setText(title_bundle);
        price.setText(String.valueOf(price_bundle));
        Glide.with(this)
                .load(image_bundle)
                .into(image);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit.getText().toString().equals("Edit")) {
//                    id.setEnabled(true);
//                    title.setEnabled(true);
//                    price.setEnabled(true);
                    edit.setText("Save");
                }
                else {
//                    id.setEnabled(false);
//                    title.setEnabled(false);
//                    price.setEnabled(false);
                    edit.setText("Edit");

                    String edittedid = id.getText().toString();
                    String edittedtitle = title.getText().toString();
                    int edittedprice = Integer.parseInt(price.getText().toString());

                    FirebaseConnect firebaseConnect = new FirebaseConnect("food_table");
                    ModelFood modelFood = new ModelFood(edittedid, image_bundle, edittedprice, edittedtitle);
                    firebaseConnect.getMyRef().child(id_bundle).setValue(modelFood);

                    Toast.makeText(AdminFoodView.this, "Updated Successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminFoodView.this, AdminFood.class);
                startActivity(i);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseConnect firebaseConnect = new FirebaseConnect("food_table");
                firebaseConnect.getMyRef().child(id_bundle).removeValue();

                Toast.makeText(AdminFoodView.this, "Deleted!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(AdminFoodView.this, AdminFood.class);
                startActivity(i);
                finish();

            }
        });

    }
}*/