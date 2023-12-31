package com.example.upgradeindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.upgradeindia.Fragment.HomeFragment;
import com.example.upgradeindia.Fragment.NotificationFragment;
import com.example.upgradeindia.Fragment.ProfileFragment;
import com.example.upgradeindia.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main2 );


        bottomNavigationView = findViewById ( R.id.bottom_navigation );

        bottomNavigationView.setOnNavigationItemSelectedListener ( navigationItemSelectedListener );

        Bundle intent = getIntent ().getExtras ();
        if(intent != null){
            String publisher = intent.getString ( "publisherid" );

            SharedPreferences.Editor editor = getSharedPreferences ( "PREFS", MODE_PRIVATE ).edit ();

            editor.putString ( "profileid", publisher );
            editor.apply ();

            getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,
                    new ProfileFragment ()).commit ();

        }else {

            getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,
                    new HomeFragment () ).commit ();
        }

        auth = FirebaseAuth.getInstance ();

    }


    private void Logout() {
        auth.signOut ();
        finish ();
        startActivity ( new Intent ( MainActivity2.this, LoginActivity.class ) );

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener () {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId ()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment ();
                            break;

                        case R.id.nav_search:
                            selectedFragment = new SearchFragment ();
                            break;

                        case R.id.nav_add:
                            selectedFragment = null;
                            startActivity ( new Intent (MainActivity2.this, PostActivity.class) );
                            break;

                        case R.id.nav_heart:
                            selectedFragment = new NotificationFragment ();

                            break;

                        case R.id.nav_profile:
                            SharedPreferences.Editor editor = getSharedPreferences ( "PREFS", MODE_PRIVATE ).edit ();
                            editor.putString ( "profileid", FirebaseAuth.getInstance ().getUid () );
                            editor.apply ();
                            selectedFragment = new ProfileFragment ();
                            break;
                    }

                    if (selectedFragment != null){
                        getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, selectedFragment).commit ();
                    }

                    return true;
                }
            };

}