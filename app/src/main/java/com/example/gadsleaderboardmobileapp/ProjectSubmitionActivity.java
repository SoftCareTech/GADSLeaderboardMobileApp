package com.example.gadsleaderboardmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gadsleaderboardmobileapp.Model.Ut;
import com.example.gadsleaderboardmobileapp.retro.APIService;
import com.example.gadsleaderboardmobileapp.retro.ApiUtils;
import com.example.gadsleaderboardmobileapp.retro.NetworkSubmit;
import com.example.gadsleaderboardmobileapp.retro.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmitionActivity extends AppCompatActivity implements View.OnClickListener {
    private APIService mAPIService;
    EditText email;
    EditText fistName;
    EditText lastName;
    EditText githubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submition);

        mAPIService = ApiUtils.getAPIService();
        fistName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email_address);
        githubLink = findViewById(R.id.git_link);
        if (Ut.validateEditText(0, fistName, lastName, email, githubLink)) {
            Toast.makeText(this, "okk", Toast.LENGTH_LONG).show();
        }

        findViewById(R.id.btn_submit).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
    }



    private void submit() {
        final Dialog confirmDialog = new Dialog(ProjectSubmitionActivity.this);

        confirmDialog.setCancelable(true);


        if ( Ut.validateEditText(1,email,fistName,lastName,githubLink)  ) {

            if (Ut.isEmail(email.getText().toString()) ) {
             Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://docs.google.com/forms/u/0/d/e/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService apiService = retrofit.create(APIService.class);
                mAPIService.savePost(email.getText().toString(),  fistName.getText().toString() ,
                        lastName.getText().toString(),   githubLink.getText().toString()  ) .
                        enqueue(new Callback<NetworkSubmit>() {


                            @Override
                            public void onResponse(Call<NetworkSubmit> call, Response<NetworkSubmit> response) {

                                if (response.isSuccessful()) {
                                Log.i(Ut.TAG, "post submitted to API." + response.body().toString());
                                    confirmDialog.setContentView(R.layout.pop_sucess);
                                } else {
                                    Log.e(Ut.TAG, " ohh Unable to submit post to API.");
                                    Log.e(Ut.TAG, response.message());
                                    Log.e(Ut.TAG,  call.toString());
                                    Log.e(Ut.TAG,  response.errorBody().toString());
                                    Toast.makeText(ProjectSubmitionActivity.this, "ohh error occcured",
                                            Toast.LENGTH_LONG).show();
                                }
                                   // confirmDialog.setContentView(R.layout.pop_warning);

                            }

                            @Override
                            public void onFailure(Call<NetworkSubmit> call, Throwable t) {
                                Log.e(Ut.TAG, "Unable to submit post to API." + t.getMessage());
                                confirmDialog.setContentView(R.layout.pop_warning);
                            }
                        });
            } else {
                confirmDialog.setContentView(R.layout.pop_warning);
                Toast.makeText(this, "Put a valid email address", Toast.LENGTH_LONG).show();
            }

        } else {
            confirmDialog.setContentView(R.layout.pop_warning);
            Toast.makeText(this, "All field are required", Toast.LENGTH_LONG).show();
        }


        confirmDialog.show();
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back: {
                onBackPressed();
                break;
            }
            case R.id.btn_submit: {
                final Dialog confirmDialog = new Dialog(ProjectSubmitionActivity.this);
                confirmDialog.setContentView(R.layout.pop_confirm);
                confirmDialog.setCancelable(true);

                confirmDialog.show();
                confirmDialog.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmDialog.dismiss();
                        submit();

                    }
                });
                break;
            }
        }
    }

}