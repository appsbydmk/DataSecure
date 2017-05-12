package com.appsbydmk.datasecure.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.appsbydmk.datasecure.R;
import com.appsbydmk.datasecure.helpers.EncryptDecryptUtility;
import com.appsbydmk.datasecure.helpers.HelperConstants;
import com.appsbydmk.datasecure.helpers.UserInformationHelper;

import java.util.ArrayList;
import java.util.List;

public class PersonalDetailsDialog extends Dialog implements View.OnClickListener {
    private Context myContext;
    private Button btnOk, btnCancel;
    private EditText etFullName, etDob, etAadharNo, etPrimaryEmail;
    private RadioGroup rgGender;
    private String gender;

    public PersonalDetailsDialog(Context context) {
        super(context);
        this.myContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_personal_details);
        btnOk = (Button) this.findViewById(R.id.btn_save);
        btnCancel = (Button) this.findViewById(R.id.btn_cancel);
        etFullName = (EditText) this.findViewById(R.id.et_full_name);
        etDob = (EditText) this.findViewById(R.id.et_dob);
        etAadharNo = (EditText) this.findViewById(R.id.et_aadhar_no);
        etPrimaryEmail = (EditText) this.findViewById(R.id.et_primary_email_id);
        rgGender = (RadioGroup) this.findViewById(R.id.rg_gender);
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                gender = radioButton.getText().toString();
            }
        });
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                this.setPersonalDetails();
                EncryptDecryptUtility.encrypt(myContext, HelperConstants.PERSONAL_DETAILS_FILE);
                Toast.makeText(myContext, "Personal Details Saved!", Toast.LENGTH_SHORT).show();
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

    private void setPersonalDetails() {
        ArrayList<String> personalDetails = new ArrayList<>();
        String fullName = etFullName.getText().toString();
        String dob = etDob.getText().toString();
        String aadhar = etAadharNo.getText().toString();
        String pEmail = etPrimaryEmail.getText().toString();

        if (fullName.equals("") || fullName.isEmpty())
            personalDetails.add("");
        else
            personalDetails.add("Full name: " + etFullName.getText().toString());

        if (dob.equals("") || dob.isEmpty())
            personalDetails.add("");
        else
            personalDetails.add("Date of Birth: " + etDob.getText().toString());

        if (gender == null || gender.equals("") || gender.isEmpty())
            personalDetails.add("");
        else
            personalDetails.add("Gender: " + gender);

        if (aadhar.equals("") || aadhar.isEmpty())
            personalDetails.add("");
        else
            personalDetails.add("Aadhar Card No: " + etAadharNo.getText().toString());

        if (pEmail.equals("") || pEmail.isEmpty())
            personalDetails.add("");
        else
            personalDetails.add("Primary Email Address: " + etPrimaryEmail.getText().toString());
        UserInformationHelper.writePersonalDetails(myContext, personalDetails);
    }
}
