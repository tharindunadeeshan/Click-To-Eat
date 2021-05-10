package com.project.clicktoeat;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    ViewData_FireStore listActivity;
    List<ModelD> modelDList;
    Context context;

    public CustomAdapter(ViewData_FireStore listActivity, List<ModelD> modelDList) {
        this.listActivity = listActivity;
        this.modelDList = modelDList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_layout,viewGroup,false);


        ViewHolder  viewHolder= new ViewHolder(itemView);
        //handle item click here
       viewHolder.setOnclickListener(new ViewHolder.ClickListener() {
           @Override
           public Void onfoodIdClick(View view, int position) {

              //this will be called when user click item
               return null;
           }

           @Override
           public Void onfoodIdLongClick(View view, int position) {
               //this will be called when user longclick item

               //show data  toast onclicking

               String foodId = modelDList.get(position).getFoodId();
               String foodname = modelDList.get(position).getFoodName();
               String phonenumber = modelDList.get(position).getPhoneNumber();
               String qty = modelDList.get(position).getQuantity();
               String totalprice = modelDList.get(position).getTotalPrice();
               String address = modelDList.get(position).getAddress();

               Toast.makeText(listActivity, foodId+"\n"+foodname, Toast.LENGTH_SHORT).show();
               return null;
           }
       });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        //bind views / set data
        viewHolder.foodIdTV.setText(modelDList.get(i).getFoodId());
        viewHolder.foodNameTV.setText(modelDList.get(i).getFoodName());
        viewHolder.phoneNumberTV.setText(modelDList.get(i).getPhoneNumber());
        viewHolder.quantityTV.setText(modelDList.get(i).getQuantity());
        viewHolder.totalPriceTV.setText(modelDList.get(i).getTotalPrice());
        viewHolder.addressTV.setText(modelDList.get(i).getAddress());

    }

    @Override
    public int getItemCount() {
        return modelDList.size();
    }
}
