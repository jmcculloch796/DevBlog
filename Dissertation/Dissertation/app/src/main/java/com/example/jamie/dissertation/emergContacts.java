package com.example.jamie.dissertation;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class emergContacts extends MainActivity {

    ArrayList<String> appcontacts = new ArrayList<String>();


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        LinearLayout dynamicContent, bottonNavBar;

        super.onCreate(savedInstanceState);
    //   setContentView(R.layout.activity_emerg_contacts);

        dynamicContent = (LinearLayout) findViewById(R.id.dynamicContent);
        bottonNavBar = (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_emerg_contacts, null);

        dynamicContent.addView(wizard);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.emergContacts);
        rb.setTextColor(Color.parseColor("#3F51B5"));



    }


    public void showSavedContacts(View view) {
        Intent intent = new Intent(this, showAllContacts.class);
        intent.putStringArrayListExtra("savedContacts", appcontacts);
        startActivity(intent);
    }



   }