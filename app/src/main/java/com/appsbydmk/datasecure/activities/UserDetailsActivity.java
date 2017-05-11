package com.appsbydmk.datasecure.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.HelperConstants;
import com.appsbydmk.datasecure.helpers.UserDetailsFileHelper;

public class UserDetailsActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etPasswordAgain;
    private Button btnSaveUserDetails;
    private String userName, password, passwordAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        this.bindViews();
        btnSaveUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetailsActivity.this.getUserDetails();
                if (validateUserDetails(userName, password, passwordAgain)) {
                    if (UserDetailsFileHelper.writeUserDetails(UserDetailsActivity.this, userName, password)) {
                        setResult(HelperConstants.USER_DETAILS_STATUS_CODE);
                        UserDetailsActivity.this.finish();
                    }
                }
            }
        });
    }

    private void bindViews() {
        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        etPasswordAgain = (EditText) this.findViewById(R.id.et_password_again);
        btnSaveUserDetails = (Button) this.findViewById(R.id.btn_save);
    }

    public boolean validateUserDetails(String userName, String password, String passwordAgain) {
        if ((userName != null && !userName.isEmpty()) || (password != null && !password.isEmpty())
                || (passwordAgain != null && !passwordAgain.isEmpty())) {
            if (password.equals(passwordAgain))
                return true;
            else return false;
        } else return false;
    }

    private void getUserDetails() {
        userName = etUsername.getText().toString();
        password = etPassword.getText().toString();
        passwordAgain = etPasswordAgain.getText().toString();
    }
}
