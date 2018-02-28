package com.example.jamie.dissertation;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Camera;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    RadioGroup radiogroup1;
    Boolean state;
    RadioButton deals;
    String checkPanicSettings;
    SharedPreferences sharedPreferences;
    private static final int VIDEO_CAPTURE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        settings st = new settings();
        st.getPanicSettings();
        state = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radiogroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        deals = (RadioButton) findViewById(R.id.home);
      //  SharedPreferences states = getSharedPreferences(PREFS_NAME, 0);
      //  boolean state = states.edit().putBoolean("state", true).commit();
        //Button stateButton = (Button)findViewById(R.id.button);
        //boolean currentState = states.getBoolean("state", false);

//        stateButton.setOnClickListener(new View.OnClickListener(){
  //          SharedPreferences.Editor editor = sharedPreferences.edit();
    //        editor.putString("state" )

      //  });
        radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent in;
                switch (checkedId) {
                    case R.id.navigation_home:
                        in = new Intent(getBaseContext(), Home.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.emergContacts:
                        in = new Intent(getBaseContext(), emergContacts.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_camera:
                        //File mediaFile =
                          //      new File(context.getFilesDir(),"/myvideo.mp4");
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                       // Uri videoUri = Uri.fromFile(mediaFile);
                        //intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);
                        startActivityForResult(intent, VIDEO_CAPTURE);

                        
                        break;
                    case R.id.settings:
                        in = new Intent(getBaseContext(), settings.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.requestLocation:
                        in = new Intent(getBaseContext(), RequestLocation.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.maps:
                        in = new Intent(getBaseContext(), MapsActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                }
            }
        });

    }


    public void changeState(View view){
        if (state) {
            Button button = (Button) findViewById(R.id.stateButton);
            button.setText("Alert");
            state = false;
        }
        else{
            Button button = (Button)findViewById(R.id.stateButton);
            button.setText("Standard");

            state=true;
        }

    }
    public void openPanic(View view){
        switch(checkPanicSettings){
            case "phone":
                 Intent callIntent = new Intent(Intent.ACTION_CALL);
                 callIntent.setData(Uri.parse("tel:123456789"));
                 startActivity(callIntent);
                 break;
            case "sendLoc":
                 Intent in;
                 in = new Intent(getBaseContext(), RequestLocation.class);
                 startActivity(in);
                 break;
        }
    }
                                               }

