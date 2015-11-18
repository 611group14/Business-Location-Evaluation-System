package com.uwin.bles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }

    // Call for MainMap Activity
    public void launchMainMap(View view) {
        Intent intent = new Intent(this, MainMapActivity.class);
        startActivity(intent);
    }
}
