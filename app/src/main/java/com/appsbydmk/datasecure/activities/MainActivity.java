package com.appsbydmk.datasecure.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.dialogs.AboutDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startProfileLoginScreen(View v) {
        Intent profileIntent = new Intent(this, ProfileLoginActivity.class);
        this.startActivity(profileIntent);
    }

    public void startInfoScreen(View v) {
        Intent infoIntent = new Intent(this, InfoActivity.class);
        this.startActivity(infoIntent);
    }

    public void displayAbout(View v) {
        AboutDialog aboutDialog = new AboutDialog(this);
        aboutDialog.show();
    }
}
