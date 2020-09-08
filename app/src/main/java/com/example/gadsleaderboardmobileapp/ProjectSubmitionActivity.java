package com.example.gadsleaderboardmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
        // set references
        mAPIService = ApiUtils.getAPIService();
        fistName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email_address);
        githubLink = findViewById(R.id.git_link);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
    }



    private void submit() {
        final Dialog confirmDialog = new Dialog(ProjectSubmitionActivity.this);
        confirmDialog.setCancelable(true); // cancel dialog if click outside
        if ( Ut.validateEditText(1,email,fistName,lastName,githubLink)  ) { // check if inputs are not empty
            if (Ut.isEmail(email.getText().toString()) ) { // check if it an email
                mAPIService.savePost(email.getText().toString(),  fistName.getText().toString() ,
                        lastName.getText().toString(),   githubLink.getText().toString()  ) .
                        enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Log.i(Ut.TAG, "Response code" + response.code());
                                if (response.isSuccessful()) {
                                    confirmDialog.setContentView(R.layout.pop_sucess);
                                } if(response.code()==200) {
                                    confirmDialog.setContentView(R.layout.pop_sucess);
                                }else {
                                    confirmDialog.setContentView(R.layout.pop_warning);
                                    Toast.makeText(ProjectSubmitionActivity.this, "ohh error occured",
                                            Toast.LENGTH_LONG).show();
                                }


                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
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
                confirmDialog.setCancelable(false);
        confirmDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                confirmDialog.show();
                confirmDialog.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmDialog.dismiss();
                        submit();

                    }
                });
                confirmDialog.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmDialog.dismiss();
                       confirmDialog.dismiss();

                    }
                });
                break;
            }
        }
    }

}