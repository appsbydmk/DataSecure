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


public class EducationalDetailsDialog extends Dialog implements View.OnClickListener {

    private Context myContext;
    private Button btnSave, btnCancel;
    private EditText etSchoolName, et10thMarks, etJuniorCollegeName,
            et12thDiplomaMarks, etGradCollegeName, etGraduationMarks;

    public EducationalDetailsDialog(Context context) {
        super(context);
        this.myContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_educational_details);
        etSchoolName = (EditText) this.findViewById(R.id.et_school_name);
        et10thMarks = (EditText) this.findViewById(R.id.et_10th_marks);
        etJuniorCollegeName = (EditText) this.findViewById(R.id.et_junior_college);
        et12thDiplomaMarks = (EditText) this.findViewById(R.id.et_12th_diploma_marks);
        etGradCollegeName = (EditText) this.findViewById(R.id.et_grad_college);
        etGraduationMarks = (EditText) this.findViewById(R.id.et_grad_marks);
        btnSave = (Button) this.findViewById(R.id.btn_save);
        btnCancel = (Button) this.findViewById(R.id.btn_cancel);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                this.writeEducationDetails();
                EncryptDecryptUtility.encrypt(myContext, HelperConstants.EDUCATION_DETAILS_FILE);
                Toast.makeText(myContext, "Education Details Saved!", Toast.LENGTH_SHORT).show();
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

    private void writeEducationDetails() {
        ArrayList<String> educationDetails = new ArrayList<>();
        String schoolName = etSchoolName.getText().toString();
        String tenthMarks = et10thMarks.getText().toString();
        String juniorCollegeName = etJuniorCollegeName.getText().toString();
        String diploma12thMarks = et12thDiplomaMarks.getText().toString();
        String gradCollege = etGradCollegeName.getText().toString();
        String graduationMarks = etGraduationMarks.getText().toString();
        if (!schoolName.equals("") && !schoolName.isEmpty()) {
            educationDetails.add("School Name: " + schoolName);
        }
        if (!tenthMarks.equals("") && !tenthMarks.isEmpty()) {
            educationDetails.add("10th Marks: " + tenthMarks);
        }
        if (!juniorCollegeName.equals("") && !juniorCollegeName.isEmpty()) {
            educationDetails.add("Junior College Name: " + juniorCollegeName);
        }
        if (!diploma12thMarks.equals("") && !diploma12thMarks.isEmpty()) {
            educationDetails.add("12th or Diploma Marks: " + diploma12thMarks);
        }
        if (!gradCollege.equals("") && !gradCollege.isEmpty()) {
            educationDetails.add("Graduation College:" + gradCollege);
        }
        if (!graduationMarks.equals("") && !graduationMarks.isEmpty()) {
            educationDetails.add("Graduation Marks: " + graduationMarks);
        }
        UserInformationHelper.writeEducationalDetails(myContext, educationDetails);
    }
}
