package com.appsbydmk.datasecure.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.EncryptDecryptUtility;
import com.appsbydmk.datasecure.helpers.HelperConstants;
import com.appsbydmk.datasecure.helpers.UserInformationHelper;

import java.util.ArrayList;
import java.util.List;

public class InsuranceDetailsDialog extends Dialog implements View.OnClickListener {

    private Context myContext;
    private Button btnSave, btnCancel;
    private EditText etInsurancePolicyName, etpolicyProvider, etPolicyExpiryDate, etInsurancePremium;

    public InsuranceDetailsDialog(Context context) {
        super(context);
        this.myContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_insurance_details);
        etInsurancePolicyName = (EditText) this.findViewById(R.id.et_insurance_name);
        etpolicyProvider = (EditText) this.findViewById(R.id.et_insurance_provider);
        etPolicyExpiryDate = (EditText) this.findViewById(R.id.et_insurance_expiry_date);
        etInsurancePremium = (EditText) this.findViewById(R.id.et_insurance_premium_amt);
        btnSave = (Button) this.findViewById(R.id.btn_save);
        btnCancel = (Button) this.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                this.writeInsuranceDetails();
                EncryptDecryptUtility.encrypt(myContext, HelperConstants.INSURANCE_DETAILS_FILE);
                Toast.makeText(myContext, "Insurance Details Saved!", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }

    private void writeInsuranceDetails() {
        ArrayList<String> insuranceDetails = new ArrayList<>();
        String policyName = etInsurancePolicyName.getText().toString();
        String policyProvider = etpolicyProvider.getText().toString();
        String policyExpiryDate = etPolicyExpiryDate.getText().toString();
        String policyPremiumAmount = etInsurancePremium.getText().toString();
        insuranceDetails.add("Insurance Policy Name: " + policyName);
        insuranceDetails.add("Insurance Policy Provider: " + policyProvider);
        insuranceDetails.add("Insurance Policy Expiry Date: " + policyExpiryDate);
        insuranceDetails.add("Insurance Policy Premium Amount: " + policyPremiumAmount);
        UserInformationHelper.writeInsuranceDetails(myContext, insuranceDetails);
    }
}
