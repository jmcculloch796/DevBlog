package com.example.jamie.dissertation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class showAllContacts extends AppCompatActivity {

    private TextView mTextMessage;
    ListView alv;
   static ArrayList<String> appcontacts = new ArrayList<String>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_call:
                    mTextMessage.setText(R.string.show_contacts);
                    return true;
                case R.id.navigation_location:

                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.settings);
                    return true;
                case R.id.navigation_map:
                    mTextMessage.setText(R.string.map);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_contacts);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent intent = getIntent();
        appcontacts = intent.getStringArrayListExtra("savedContacts");
        if (appcontacts == null)
        {
            System.out.println("No added contacts yet");

        } else {
            for (int i =0; i < appcontacts.size(); i++){
                printToScreen();
            }

        }
        System.out.println(appcontacts);

    }





    public void printToScreen(){
        System.out.println("Test");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                appcontacts);


        for (int i = 0; i < appcontacts.size(); i++) {

            System.out.println("This is a test");
            System.out.println(appcontacts);
            alv = (ListView) findViewById(R.id.showcontactsView);
            alv.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();

        }
    }

    public static ArrayList<String> getContacts(){
        return appcontacts;
    }
}



