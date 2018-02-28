package com.example.jamie.dissertation;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.provider.ContactsContract;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.jamie.dissertation.showAllContacts.appcontacts;

public class emergContacts extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    protected LocationManager locationManager;
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters

    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000000000;
    private TextView mTextMessage;
    List<String> names = new ArrayList<String>();
    List<String> numbers = new ArrayList<String>();
    ArrayList<String> appcontacts = new ArrayList<String>();

    ListView lv;
    ListView alv;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openHome();
                    return true;
                case R.id.navigation_call:
                    return true;
                case R.id.navigation_location:
                    showLocation();
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


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emerg_contacts);
        Intent intent = getIntent();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //  appcontacts =
        // intent.getStringArrayListExtra("savedContacts");s
        readContacts(); locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new myLocationListener());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    public void openSavedContacts() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openLocationUpdate() {

    }

    public void openMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openSettings() {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }

    public void addContact() {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        startActivity(intent);
    }

    public void showSavedContacts(View view) {
        Intent intent = new Intent(this, showContacts.class);
        intent.putStringArrayListExtra("savedContacts", appcontacts);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void showLocation() {
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {

            String message = "You can track my location here: http://maps.google.com/maps?q=loc:" + String.format("%f,%f", location.getLatitude(), location.getLongitude()); //String.format(

            //    "Current Location \n Longitude: %1$s \n Latitude: %2$s",

            //  location.getLongitude(), location.getLatitude()

            // );


            Toast.makeText(emergContacts.this, message,

                    Toast.LENGTH_LONG).show();
            if(appcontacts.size() > 1) {
                for(int i = 0; i < appcontacts.size(); i++){
                    Uri sms_uri = Uri.parse("smsto:" + appcontacts.get(i));

                    Intent intent = new Intent(android.content.Intent.ACTION_SENDTO, sms_uri);
                    //intent.setType("vnd.android-dir/mms-sms");
                    intent.putExtra("sms_body", message);
                    startActivity(intent);
                }
            }
                Uri sms_uri = Uri.parse("smsto:" + appcontacts);

            Intent intent = new Intent(android.content.Intent.ACTION_SENDTO, sms_uri);
            //intent.setType("vnd.android-dir/mms-sms");
            intent.putExtra("sms_body", message);
            startActivity(intent);


        }
    }

    public void readContacts() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    // get the phone number
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phone = pCur.getString(
                                pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        //  System.out.println("phone" + phone);
                        names.add(name);
                        numbers.add(phone);
                    }


                    pCur.close();

                }

            }
            int length = names.size();
            if (length != numbers.size()) { // Too many names, or too many numbers
                // Fail
            }
            final ArrayList<String> contacts = new ArrayList<String>(length); // Make a new list
            for (int i = 0; i < length; i++) { // Loop through every name/phone number combo
                contacts.add(names.get(i) + " " + numbers.get(i)); // Concat the two, and add it
            }
            // System.out.println(contacts);
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    contacts);
            for (int i = 0; i < contacts.size(); i++) {

                lv = (ListView) findViewById(R.id.contactsView);


                lv.setAdapter(arrayAdapter);
            }
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(com.example.jamie.dissertation.emergContacts.this);
                    adb.setTitle("Add contact?");
                    adb.setMessage("Are you sure you want to add " + contacts.get(position) + " to emergency contacts?");
                    final int positionToAdd = position;
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            appcontacts.add(contacts.get(position));
                            arrayAdapter.notifyDataSetChanged();
                        }
                    });
                    adb.show();
                    //System.out.println(appcontacts);
                }
            });

        }


    }
    // textout.append(contacts.toString());
    // textout.append("\n");

}