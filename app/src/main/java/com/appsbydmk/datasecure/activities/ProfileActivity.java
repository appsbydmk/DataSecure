package com.appsbydmk.datasecure.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.EncryptDecryptUtility;
import com.appsbydmk.datasecure.helpers.HelperConstants;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvProfileInfo;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvProfileInfo = (TextView) this.findViewById(R.id.tv_profile_info);
        btnLogout = (Button) this.findViewById(R.id.btn_logout);
        String personalDetails = EncryptDecryptUtility.decrypt(this, "encrypted_" + HelperConstants.PERSONAL_DETAILS_FILE);
        String financialDetails = EncryptDecryptUtility.decrypt(this, "encrypted_" + HelperConstants.FINANCIAL_DETAILS_FILE);
        String educationalDetails = EncryptDecryptUtility.decrypt(this, "encrypted_" + HelperConstants.EDUCATION_DETAILS_FILE);
        String insuranceDetails = EncryptDecryptUtility.decrypt(this, "encrypted_" + HelperConstants.INSURANCE_DETAILS_FILE);
        String webDetails = EncryptDecryptUtility.decrypt(this, "encrypted_" + HelperConstants.WEB_DETAILS_FILE);
        String finalString = personalDetails + "\n"
                + financialDetails + "\n"
                + educationalDetails + "\n"
                + insuranceDetails + "\n"
                + webDetails;

        tvProfileInfo.setText(finalString);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(HelperConstants.PROFILE_LOGOUT_CODE);
                ProfileActivity.this.finish();
            }
        });
    }
}
