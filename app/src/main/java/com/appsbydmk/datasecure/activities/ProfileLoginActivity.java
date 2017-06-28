package com.appsbydmk.datasecure.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.HelperConstants;
import com.appsbydmk.datasecure.helpers.UserDetailsFileHelper;

import java.util.ArrayList;

public class ProfileLoginActivity extends AppCompatActivity {
    private EditText etPassword;
    private Button btnUserLogin;
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
                userDetails = UserDetailsFileHelper.getUserDetails(ProfileLoginActivity.this);
                if (userDetails.get(1).equals(etPassword.getText().toString())) {
                    profileIntent = new Intent(ProfileLoginActivity.this, ProfileActivity.class);
                    ProfileLoginActivity.this.startActivityForResult(profileIntent,
                            HelperConstants.PROFILE_LOGOUT_CODE);
                } else {
                    Toast.makeText(ProfileLoginActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bindViews() {
        etPassword = (EditText) this.findViewById(R.id.et_pwd_login);
        btnUserLogin = (Button) this.findViewById(R.id.btn_login);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HelperConstants.PROFILE_LOGOUT_CODE) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            this.startActivity(mainIntent);
            this.finish();
        }
    }
}
