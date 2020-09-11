package com.example.gad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText fname, lname, email, link;
    private Button  submitButton;
    private ConfirmDialog confirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fname=findViewById(R.id.editText_firstname);
        lname = findViewById(R.id.editText_lastName);
        link = findViewById(R.id.editText_link);
        email = findViewById(R.id.editTextTextEmailAddress);
        submitButton = findViewById(R.id.button);

        submitButton.setOnClickListener(view -> {
            confirmDialog = new ConfirmDialog();
            confirmDialog.showDialog(SubmitActivity.this);

        });

        confirmDialog.dialog.findViewById(R.id.confirmButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitWork();
            }
        });



    }

    public void submitWork(){
        CallService callService = CallBuilder.buildService(CallService.class);
        Call<Void> submit = callService.submit(email.getText().toString(), fname.getText().toString(), lname.getText().toString(), link.getText().toString());

        submit.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Successfully submitted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                if (t instanceof IOException){
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                }else{
                    Log.e("FAILED", "Error from server");
                }
            }
        });
    }
}