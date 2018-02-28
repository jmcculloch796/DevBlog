package com.example.jamie.dissertation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.jamie.dissertation.showAllContacts.appcontacts;


public class RequestLocation extends MainActivity {

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters

    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000000000; // in Milliseconds


    protected LocationManager locationManager;

    protected Button retrieveLocationButton;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        LinearLayout dynamicContent, bottonNavBar;

        super.onCreate(savedInstanceState);

       // setContentView(R.layout.activity_request_location);

        dynamicContent = (LinearLayout) findViewById(R.id.dynamicContent);
        bottonNavBar = (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_request_location, null);

        dynamicContent.addView(wizard);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.requestLocation);
        rb.setTextColor(Color.parseColor("#3F51B5"));

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        retrieveLocationButton = (Button) findViewById(R.id.retrieve_location_button);

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