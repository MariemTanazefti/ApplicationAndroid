package com.example.projectdesigner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView=(BottomNavigationView) findViewById(R.id.botton_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(naviglistener);
    }


    public BottomNavigationView.OnNavigationItemSelectedListener naviglistener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment unFrgment=null;
            switch (item.getItemId()){
                case R.id.item_home:
                    unFrgment=new HomeFragment();
                    break;
                case R.id.item_person:
                    unFrgment=new AccountFragment();
                    break;

            }
            String URL="http://192.168.8.101:80/";
            Bundle bundle=new Bundle();
            bundle.putString("url",URL);
            unFrgment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplaceholder,unFrgment).commit();
            return true;


        }
    };
}
