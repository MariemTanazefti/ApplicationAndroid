package com.example.projectdesigner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail,etPass,etCpass;
    ApiHandler apiHandler;
    TextView txtSignin;
    Button btnsinup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtSignin=findViewById(R.id.txt_signin);
        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        apiHandler= ApiClient.getApiClient().create(ApiHandler.class);
        etEmail= (EditText)findViewById(R.id.edt_smail);
        etPass = (EditText)findViewById(R.id.edt_spass);
        etCpass = (EditText)findViewById(R.id.edt_scpass);
        btnsinup = (Button)findViewById(R.id.btn_signup);
        btnsinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }
    private void addUser() {
        String email=etEmail.getText().toString().trim();
        String password=etPass.getText().toString().trim();
        String confirmPassword=etCpass.getText().toString().trim();
        Call<User> insertUser=apiHandler.insertUser(email,password,confirmPassword);
        insertUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                Toast.makeText(getApplicationContext(),"user added",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"failed " + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}