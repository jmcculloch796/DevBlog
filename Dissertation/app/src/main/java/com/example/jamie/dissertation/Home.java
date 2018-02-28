package com.example.jamie.dissertation;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import static android.provider.LiveFolders.INTENT;
import static com.example.jamie.dissertation.showAllContacts.appcontacts;

public class Home extends AppCompatActivity {

    private TextView mTextMessage;
    protected LocationManager locationManager;

  RequestLocation location = new RequestLocation();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_call:
                    openSavedContacts();
                    return true;
                case R.id.navigation_location:
                    openLocationUpdate();
                    return true;
                case R.id.navigation_settings:
                    openSettings();
                    return true;
                case R.id.navigation_map:
                    openMap();
                    return true;
            }
            return false;
        }
    };
  //  public void switchToFragment1() {
    //    FragmentManager manager = getSupportFragmentManager();
     //   manager.beginTransaction().replace(R.id., new Fragment1()).commit();
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage.setText("Hello! Welcome to KeepSafe - a personal safety application that will hopefully \nmake you feel safer when you're out and about! Select an option from the navigation bar below to start.");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


/*
        int permissionCheck = ContextCompat.checkSelfPermission(Home.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new myLocationListener());*/
                showAllContacts.getContacts();
    }

    public void openSavedContacts() {
        Intent intent = new Intent(this, emergContacts.class);
        startActivity(intent);
    }

    public void openLocationUpdate() { //get it to retrieve data without opening maps class
        Intent intent = new Intent(this, RequestLocation.class);
        startActivity(intent);
        // startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
        // SharedPreferences myPrefs = getSharedPreferences("MapsActivity", MODE_PRIVATE);
        //String link = myPrefs.getString("mapLoc", "");
        //System.out.println(link)/

    }


        public void openMap() {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
        public void openSettings(){
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
        }

    }
