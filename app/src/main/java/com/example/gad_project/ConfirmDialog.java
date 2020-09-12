package com.example.gad_project;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmDialog {
    public Dialog dialog;
    public void showDialog(Activity activity){
         dialog= new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_confirm_dialog);



        ImageView closeBtn =  dialog.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        dialog.show();

    }

    public void submitConfirmed(Context context, Activity activity, String email, String  fname, String lname, String link){
        Button yesButton = dialog.findViewById(R.id.confirmButton);
                yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiUtil.submitWork(context, activity, email, fname, lname, link);
                dialog.dismiss();
            }
        });
    }

}
