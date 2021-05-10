package com.project.clicktoeat;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

class LoadingDialog {

 private Activity activity;
 private AlertDialog dialog;

    LoadingDialog(Activity myActivity){
        activity =myActivity;
    }

    void starttLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(true);

        dialog =builder.create();
        dialog.show();

    }
    void dismissDialog(){
        dialog.dismiss();

    }
 }