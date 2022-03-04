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
    EditText edtMail, edtPass;
    //ApiHandler apiHandler;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newAcc = findViewById(R.id.txt_register);
        newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        //apiHandler= ApiClient.getApiClient().create(ApiHandler.class);
        edtMail = findViewById(R.id.edt_mail);
        edtPass = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btn_signin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtMail.getText().toString().equals("elaa")
                        && (edtPass.getText().toString().equals("elaa"))) {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(MainActivity.this, "Email ou Password incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
