package com.appsbydmk.datasecure.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.UserDetailsFileHelper;

import java.util.ArrayList;

public class ProfileLoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnUserLogin;
    private UserDetailsFileHelper userDetailsFileHelper;
    private ArrayList<String> userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_login);
        this.bindViews();
        userDetails = new ArrayList<>();
        btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent;
                userDetailsFileHelper = new UserDetailsFileHelper(ProfileLoginActivity.this);
                userDetails = userDetailsFileHelper.getUserDetails();
                if (userDetails.get(0).toString().equals(etUsername.getText().toString())
                        && userDetails.get(1).toString().equals(etPassword.getText().toString())) {
                    profileIntent = new Intent(ProfileLoginActivity.this, ProfileActivity.class);
                    ProfileLoginActivity.this.startActivity(profileIntent);
                }
            }
        });
    }

    private void bindViews() {
        etUsername = (EditText) this.findViewById(R.id.et_uname_login);
        etPassword = (EditText) this.findViewById(R.id.et_pwd_login);
        btnUserLogin = (Button) this.findViewById(R.id.btn_login);
    }
}
