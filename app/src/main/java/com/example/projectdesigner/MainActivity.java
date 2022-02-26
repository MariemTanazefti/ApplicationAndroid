package com.example.projectdesigner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    TextView newAcc;
    EditText edtMail,edtPass;
    ApiHandler apiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newAcc=findViewById(R.id.txt_register);
        newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        apiHandler= ApiClient.getApiClient().create(ApiHandler.class);
        edtMail=findViewById(R.id.edt_mail);
        edtPass=findViewById(R.id.edt_pass);
        SharedPreferences prefs= getPreferences(Context.MODE_PRIVATE);

        String emailsp=prefs.getString("email","");
        String passwordsp=prefs.getString("password","");
        if(emailsp.equals("")&& passwordsp.equals(""))
        {
            Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_LONG).show();
        }
        else {
            Intent homeIntent=new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(homeIntent);
        }

        Button btnLogin =findViewById(R.id.btn_signin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });

    }
    public  void login(){
        String email=edtMail.getText().toString().trim();
        String password=edtPass.getText().toString().trim();
        if (email.isEmpty()) {
            edtMail.setError("Email is required");
            edtMail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            edtPass.setError("Password required");
            edtPass.requestFocus();
            return;
        }
        Call<User> LoginUser=apiHandler.LoginUser(email,password);
        LoginUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if(response.body().getSuccess().equals("1")){
                    SharedPreferences prefs =getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=prefs.edit();
                    editor.putString("username",edtMail.getText().toString());
                    editor.putString("password",edtPass.getText().toString());
                    editor.commit();
                    Intent homeIntent=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(homeIntent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"failed"+ t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("email",edtMail.getText().toString());
        outState.putString("pwd",edtPass.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        String email=savedInstanceState.getString("email");
        String password=savedInstanceState.getString("pwd");
        edtMail.setText(email);
        edtPass.setText(password);
    }

    }
