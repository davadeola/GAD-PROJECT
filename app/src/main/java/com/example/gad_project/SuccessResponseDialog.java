package com.example.gad_project;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SuccessResponseDialog {

    public Dialog dialog;
    public void showDialog(Activity activity){
        dialog= new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.success_layout);




        dialog.show();

    }
}
