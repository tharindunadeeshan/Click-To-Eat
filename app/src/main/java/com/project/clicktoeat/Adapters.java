package com.project.clicktoeat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapters extends BaseAdapter {

    Context context;
    List list = new ArrayList();
    LayoutInflater inflater;

    public Adapters(Context context, List list) {
        this.context = context;
        this.list = list;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;

        view1 = inflater.inflate(R.layout.item_fooditem, null);
        ImageView ivfooditem = view1.findViewById(R.id.iv_item_food_icon);
        TextView tvfooditemtitle = view1.findViewById(R.id.tv_item_food_title);
        TextView tvfooditemprice = view1.findViewById(R.id.tv_item_food_price);
        Button btnView = view1.findViewById(R.id.btn_item_view);

        List<ModelFood> r_list = list;

        String id = r_list.get(i).getF_id();
        String title = r_list.get(i).getF_title();
        final String IMAGE_URL = r_list.get(i).getF_image();
        int price = r_list.get(i).getF_price();

        Glide.with(context)
                .load(IMAGE_URL)
                .into(ivfooditem);

        tvfooditemtitle.setText(title);
        tvfooditemprice.setText("LKR " + String.valueOf(price) + ".00");

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getApplicationContext(), AdminFood_View.class);
                Bundle bundle = new Bundle();
                bundle.putString("id_key", id);
                bundle.putString("title_key", title);
                bundle.putInt("price_key", price);
                bundle.putString("image_URL_key", IMAGE_URL);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });

        return view1;
    }
}
