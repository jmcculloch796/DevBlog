package com.example.jamie.dissertation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.jamie.dissertation.showAllContacts.appcontacts;


public class RequestLocation extends Activity {

    // Assume thisActivity is the current activity


    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters

    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000000000; // in Milliseconds


    protected LocationManager locationManager;


    protected Button retrieveLocationButton;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openHome();
                    return true;
                case R.id.navigation_call:
                    openSavedContacts();
                    return true;
                case R.id.navigation_location:
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
    @Override

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_location);

        Intent intent = getIntent();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        retrieveLocationButton = (Button) findViewById(R.id.retrieve_location_button);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new myLocationListener()
        );


        retrieveLocationButton.setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                showCurrentLocation();

            }

        });

    }
    public void openHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openSavedContacts() {
        Intent intent = new Intent(this, emergContacts.class);
        startActivity(intent);
    }



    public void openMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void openSettings(){
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }



    protected void showCurrentLocation() {


        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        if (location != null) {
            String message = "You can track my location here: http://maps.google.com/maps?q=loc:" + String.format("%f,%f", location.getLatitude(), location.getLongitude()); //String.format(

            //    "Current Location \n Longitude: %1$s \n Latitude: %2$s",

            //  location.getLongitude(), location.getLatitude()

            // );
            Uri sms_uri = Uri.parse("smsto:" + appcontacts);
            Intent intent = new Intent(android.content.Intent.ACTION_SENDTO, sms_uri);
            //intent.setType("vnd.android-dir/mms-sms");
            intent.putExtra("sms_body", message);
            startActivity(intent);


        }
    }
}